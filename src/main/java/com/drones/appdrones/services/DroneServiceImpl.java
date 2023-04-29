package com.drones.appdrones.services;

import com.drones.appdrones.domain.dtos.DroneDTO;
import com.drones.appdrones.domain.dtos.MedicationDTO;
import com.drones.appdrones.domain.enums.State;
import com.drones.appdrones.exceptions.DronesAppException;
import com.drones.appdrones.persistence.entities.Drone;
import com.drones.appdrones.persistence.repositories.DroneRepository;
import com.drones.appdrones.utils.ValidationUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DroneServiceImpl implements DroneService {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private DroneRepository droneRepository;
    @Autowired
    private MedicationService medicationService;

    @Override
    public DroneDTO create(final DroneDTO drone) {
        ValidationUtil.validate(drone, true);

        final Optional<Drone> existingDrone = droneRepository.findBySerialNumber(drone.getSerialNumber());
        if (existingDrone.isPresent()) {
            throw new DronesAppException(HttpStatus.CONFLICT, String.format("Drone with serial number %s already exists", drone.getSerialNumber()));
        }
        if (State.LOADING == drone.getState() && drone.getBatteryCapacity() < 25.0d) {
            throw new DronesAppException(HttpStatus.CONFLICT, "Drone in LOADING state with less than 25% of battery");
        }
        final Drone droneToSave = objectMapper.convertValue(drone, Drone.class);
        return objectMapper.convertValue(droneRepository.save(droneToSave), DroneDTO.class);
    }

    @Override
    public DroneDTO patchDrone(final DroneDTO drone, final Long id) {
        ValidationUtil.validate(drone, false);
        final DroneDTO storedDrone = getById(id, false);
        if (!id.equals(drone.getId())) {
            throw new DronesAppException(HttpStatus.CONFLICT, "Id provided in the path and body must match");
        }
        if (State.LOADING == drone.getState() && drone.getBatteryCapacity() < 25.0d) {
            throw new DronesAppException(HttpStatus.CONFLICT, "Drone in LOADING state with less than 25% of battery");
        }
        copyNonNullProperties(drone, storedDrone);
        final Drone updatedDrone = droneRepository.save(objectMapper.convertValue(storedDrone, Drone.class));
        return objectMapper.convertValue(updatedDrone, DroneDTO.class);
    }

    @Override
    public DroneDTO getById(final Long id, final boolean includeMedications) {
        final Optional<Drone> storedDroneOpt = droneRepository.findById(id);
        final Drone storedDrone = storedDroneOpt.orElseThrow(() -> new DronesAppException(HttpStatus.NOT_FOUND, String.format("Drone with id %s not found", id)));
        if (includeMedications) {
            storedDrone.getMedications();
        } else {
            storedDrone.setMedications(null);
        }
        return objectMapper.convertValue(storedDrone, DroneDTO.class);
    }

    @Override
    public List<DroneDTO> getAll(final boolean includeMedications) {
        final List<DroneDTO> drones = new ArrayList<>();
        droneRepository.findAll().forEach(drone -> {
            if (includeMedications) {
                drone.getMedications();
            } else {
                drone.setMedications(null);
            }
            drones.add(objectMapper.convertValue(drone, DroneDTO.class));
        });
        return drones;
    }

    @Override
    public DroneDTO loadMedications(final Long id, final List<MedicationDTO> medications) {
        final DroneDTO drone = getById(id, true);
        validateDroneState(drone);
        final List<Long> medicationsIds = medications.stream().map(MedicationDTO::getId).collect(Collectors.toList());
        final List<MedicationDTO> medicationsToLoad = medicationService.getMedicationsByIds(medicationsIds);
        validateDroneWeight(drone, medicationsToLoad);
        drone.getMedications().addAll(medicationsToLoad);
        drone.setState(State.LOADING);
        final Drone updatedDrone = droneRepository.save(objectMapper.convertValue(drone, Drone.class));
        return objectMapper.convertValue(updatedDrone, DroneDTO.class);
    }

    @Override
    public List<MedicationDTO> getDroneMedications(final Long id) {
        return getById(id, true).getMedications();
    }

    @Override
    public List<DroneDTO> getDronesAvailableForLoading() {
        final List<DroneDTO> drones = new ArrayList<>();
        droneRepository.findByStateIn(List.of(State.IDLE, State.LOADING)).forEach(drone -> {
            drone.setMedications(null);
            drones.add(objectMapper.convertValue(drone, DroneDTO.class));
        });
        return drones;
    }

    @Override
    public Integer getDroneBattery(Long id) {
        return getById(id, false).getBatteryCapacity();
    }

    private void validateDroneState(final DroneDTO drone) {
        if (!(State.IDLE == drone.getState() || State.LOADING == drone.getState())) {
            throw new DronesAppException(HttpStatus.CONFLICT, String.format("Drone in %s state can not be loaded", drone.getState()));
        }
        if ((State.IDLE == drone.getState() || State.LOADING == drone.getState()) && drone.getBatteryCapacity() < 25.0d) {
            throw new DronesAppException(HttpStatus.CONFLICT, "Drone in LOADING state with less than 25% of battery");
        }
    }

    private void validateDroneWeight(final DroneDTO drone, final List<MedicationDTO> medicationsToLoad) {
        final double currentMedicationsWeight = drone.getMedications().stream().mapToDouble(MedicationDTO::getWeight).sum();
        final double weightToAdd = medicationsToLoad.stream().mapToDouble(MedicationDTO::getWeight).sum();
        if ((currentMedicationsWeight + weightToAdd) > drone.getWeightLimit().floatValue()) {
            throw new DronesAppException(HttpStatus.CONFLICT, String.format("Drone's weight limit exceeded: current load: %s, new load: %s, weight limit: %s",
                    currentMedicationsWeight, weightToAdd, drone.getWeightLimit()));
        }
    }

    public void copyNonNullProperties(final Object source, final Object destination) {
        BeanUtils.copyProperties(source, destination, getNullPropertyNames(source));
    }

    private String[] getNullPropertyNames(final Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
        final Set<String> emptyNames = new HashSet();
        for (java.beans.PropertyDescriptor pd : pds) {
            //check if value of this property is null then add it to the collection
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

}

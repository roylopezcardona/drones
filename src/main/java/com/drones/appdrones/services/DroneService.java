package com.drones.appdrones.services;

import com.drones.appdrones.domain.dtos.DroneDTO;
import com.drones.appdrones.domain.dtos.MedicationDTO;

import java.math.BigDecimal;
import java.util.List;

public interface DroneService {

    DroneDTO create(DroneDTO drone);

    DroneDTO patchDrone(DroneDTO droneDTO, Long id);

    DroneDTO getById(Long id, boolean includeMedications);

    List<DroneDTO> getAll(boolean includeMedications);

    DroneDTO loadMedications(Long id, List<MedicationDTO> medications);

    List<MedicationDTO> getDroneMedications(Long id);

    List<DroneDTO> getDronesAvailableForLoading();

    Integer getDroneBattery(Long id);

}

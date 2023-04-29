package com.drones.appdrones.services;

import com.drones.appdrones.domain.dtos.DroneDTO;
import com.drones.appdrones.domain.validation.CreateValidationGroup;
import com.drones.appdrones.domain.validation.UpdateValidationGroup;
import com.drones.appdrones.persistence.entities.Drone;
import com.drones.appdrones.persistence.repositories.DroneRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Set;

@Service
public class DroneServiceImpl implements DroneService {

    @Autowired
    private DroneRepository droneRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void create(final DroneDTO drone) {
        validate(drone, true);

        final Drone droneToSave = objectMapper.convertValue(drone, Drone.class);
        droneRepository.save(droneToSave);
    }

    private void validate(final DroneDTO drone, boolean create) {
        final ArrayList<Class<?>> validationGroups = new ArrayList<>();
        validationGroups.add(create ? CreateValidationGroup.class : UpdateValidationGroup.class);
        final Class<?>[] validationGroupsArray = validationGroups.toArray(new Class<?>[validationGroups.size()]);

        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final Set<ConstraintViolation<DroneDTO>> violations = validator.validate(drone, validationGroupsArray);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}

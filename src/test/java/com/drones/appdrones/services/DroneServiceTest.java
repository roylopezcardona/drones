package com.drones.appdrones.services;

import com.drones.appdrones.domain.dtos.DroneDTO;
import com.drones.appdrones.domain.enums.Model;
import com.drones.appdrones.domain.enums.State;
import com.drones.appdrones.persistence.entities.Drone;
import com.drones.appdrones.persistence.repositories.DroneRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintViolationException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DroneServiceTest {

    @Mock
    private DroneRepository droneRepository;

    @Mock
    private MedicationService medicationService;

    @InjectMocks
    private DroneServiceImpl droneService;

    @Test
    public void create_success() {
        final DroneDTO drone = getDrone();

        when(droneRepository.findBySerialNumber(drone.getSerialNumber())).thenReturn(Optional.empty());
        when(droneRepository.save(any(Drone.class))).then(AdditionalAnswers.returnsFirstArg());

        final DroneDTO createdDrone = droneService.create(drone);

        verify(droneRepository).save(any(Drone.class));
        assertThat(createdDrone.getSerialNumber()).isEqualTo(drone.getSerialNumber());
    }

    @Test
    public void create_invalidBatteryCapacity() {
        final DroneDTO drone = getDrone();
        drone.setBatteryCapacity(200);

        final ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, () -> droneService.create(drone));

        verify(droneRepository, never()).save(any(Drone.class));
        assertThat(exception.getMessage()).contains("battery has to be maximum 100");
    }

    private DroneDTO getDrone() {
        return DroneDTO.builder()
                .serialNumber("test_number")
                .model(Model.CRUISER_WEIGHT)
                .state(State.LOADING)
                .batteryCapacity(100)
                .weightLimit(450)
                .build();
    }

}

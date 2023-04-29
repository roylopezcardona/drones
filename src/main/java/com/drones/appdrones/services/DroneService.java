package com.drones.appdrones.services;

import com.drones.appdrones.domain.dtos.DroneDTO;
import com.drones.appdrones.domain.dtos.MedicationDTO;

import java.util.List;

public interface DroneService {

    void create(DroneDTO drone);

    DroneDTO getById(Long id, boolean includeMedications);

    List<DroneDTO> getAll(boolean includeMedications);

    DroneDTO loadMedications(Long id, List<MedicationDTO> medications);

}

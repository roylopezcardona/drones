package com.drones.appdrones.services;

import com.drones.appdrones.domain.dtos.MedicationDTO;

import java.util.List;

public interface MedicationService {

    void create(MedicationDTO medication);

    List<MedicationDTO> getMedicationsByIds(List<Long> ids);

}

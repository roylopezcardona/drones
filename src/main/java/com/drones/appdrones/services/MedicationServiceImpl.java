package com.drones.appdrones.services;

import com.drones.appdrones.domain.dtos.MedicationDTO;
import com.drones.appdrones.exceptions.DronesAppException;
import com.drones.appdrones.persistence.entities.Medication;
import com.drones.appdrones.persistence.repositories.MedicationRepository;
import com.drones.appdrones.utils.ValidationUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MedicationServiceImpl implements MedicationService {

    @Autowired
    private MedicationRepository medicationRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void create(final MedicationDTO medication) {
        ValidationUtil.validate(medication, true);

        final Optional<Medication> existingMedication = medicationRepository.findByCode(medication.getCode());
        if (existingMedication.isPresent()) {
            throw new DronesAppException(HttpStatus.CONFLICT, String.format("Medication with code %s already exists", medication.getCode()));
        }
        final Medication medicationToSave = objectMapper.convertValue(medication, Medication.class);
        medicationRepository.save(medicationToSave);
    }

    @Override
    public List<MedicationDTO> getMedicationsByIds(final List<Long> ids) {
        final List<MedicationDTO> medications = new ArrayList<>();
        medicationRepository.findByIdIn(ids).forEach(medication -> {
            medications.add(objectMapper.convertValue(medication, MedicationDTO.class));
        });
        return medications;
    }
}

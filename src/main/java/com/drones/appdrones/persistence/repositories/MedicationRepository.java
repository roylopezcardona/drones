package com.drones.appdrones.persistence.repositories;

import com.drones.appdrones.persistence.entities.Medication;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MedicationRepository extends CrudRepository<Medication, Long> {

    Optional<Medication> findByCode(String code);

    List<Medication> findByIdIn(List<Long> ids);

}

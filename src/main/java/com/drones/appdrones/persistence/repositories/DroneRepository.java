package com.drones.appdrones.persistence.repositories;

import com.drones.appdrones.domain.enums.State;
import com.drones.appdrones.persistence.entities.Drone;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DroneRepository extends CrudRepository<Drone, Long> {

    Optional<Drone> findBySerialNumber(String serialNumber);

    List<Drone> findByStateIn(List<State> states);

}

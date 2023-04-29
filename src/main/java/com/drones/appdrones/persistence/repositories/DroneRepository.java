package com.drones.appdrones.persistence.repositories;

import com.drones.appdrones.persistence.entities.Drone;
import org.springframework.data.repository.CrudRepository;

public interface DroneRepository extends CrudRepository<Drone, Long> {
}

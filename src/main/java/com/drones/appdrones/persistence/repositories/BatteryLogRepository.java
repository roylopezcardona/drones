package com.drones.appdrones.persistence.repositories;

import com.drones.appdrones.persistence.entities.BatteryLog;
import org.springframework.data.repository.CrudRepository;

public interface BatteryLogRepository extends CrudRepository<BatteryLog, Long>  {
}

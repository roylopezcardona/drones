package com.drones.appdrones.services;

import com.drones.appdrones.domain.dtos.BatteryLogDTO;
import com.drones.appdrones.persistence.entities.BatteryLog;
import com.drones.appdrones.persistence.repositories.BatteryLogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BatteryLogServiceImpl implements BatteryLogService {

    @Autowired
    private BatteryLogRepository batteryLogRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public BatteryLogDTO create(BatteryLogDTO batteryLog) {
        final BatteryLog batteryLogToStore = objectMapper.convertValue(batteryLog, BatteryLog.class);
        return objectMapper.convertValue(batteryLogRepository.save(batteryLogToStore), BatteryLogDTO.class);
    }
}

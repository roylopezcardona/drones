package com.drones.appdrones.tasks;

import com.drones.appdrones.domain.dtos.BatteryLogDTO;
import com.drones.appdrones.domain.dtos.DroneDTO;
import com.drones.appdrones.services.BatteryLogService;
import com.drones.appdrones.services.DroneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class BatteryCheckTask {

    @Autowired
    private DroneService droneService;

    @Autowired
    private BatteryLogService batteryLogService;

    @Scheduled(fixedRate = 10000)
    public void checkDronesBattery() {
        log.info("Starting drones' battery check");
        final List<DroneDTO> drones = droneService.getAll(false);
        drones.forEach(drone -> {
            log.info("Auditing drone {} with battery level at {}", drone.getId(), drone.getBatteryCapacity());
            final BatteryLogDTO batteryLog = BatteryLogDTO.builder().droneId(drone.getId()).batteryLevel(drone.getBatteryCapacity()).date(new Date()).build();
            batteryLogService.create(batteryLog);
        });
    }

}

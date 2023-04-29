package com.drones.appdrones.controllers;

import com.drones.appdrones.domain.dtos.DroneDTO;
import com.drones.appdrones.services.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/drones")
public class DroneController {

    @Autowired
    private DroneService droneService;

    @PostMapping
    public void create(@RequestBody final DroneDTO drone) {
        droneService.create(drone);
    }

}

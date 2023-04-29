package com.drones.appdrones.controllers;

import com.drones.appdrones.domain.dtos.MedicationDTO;
import com.drones.appdrones.services.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medications")
public class MedicationController {

    @Autowired
    private MedicationService medicationService;

    @PostMapping
    public void create(@RequestBody final MedicationDTO medication) {
        medicationService.create(medication);
    }

}

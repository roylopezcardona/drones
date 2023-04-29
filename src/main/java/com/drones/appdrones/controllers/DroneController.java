package com.drones.appdrones.controllers;

import com.drones.appdrones.domain.dtos.DroneDTO;
import com.drones.appdrones.domain.dtos.MedicationDTO;
import com.drones.appdrones.services.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/drones")
public class DroneController {

    @Autowired
    private DroneService droneService;

    @PostMapping
    public DroneDTO create(@RequestBody final DroneDTO drone) {
        return droneService.create(drone);
    }

    @GetMapping(value = "/{id}")
    public DroneDTO getDroneById(@PathVariable(name = "id") final Long id,
                             @RequestParam(value = "includeMedications", defaultValue = "false") final boolean includeMedications) {
        return droneService.getById(id, includeMedications);
    }

    @GetMapping
    public List<DroneDTO> getAllDrones(@RequestParam(value = "includeMedications", defaultValue = "false") final Boolean includeMedications) {
        return droneService.getAll(includeMedications);
    }

    @PatchMapping(value = "/{id}")
    public DroneDTO patchDrone(@RequestBody final DroneDTO drone, @PathVariable(name = "id") final Long id) {
        return droneService.patchDrone(drone, id);
    }

    @PostMapping(value = "/{id}/medications")
    public DroneDTO loadMedications(@RequestBody final List<MedicationDTO> medications, @PathVariable(name = "id") final Long id) {
        return droneService.loadMedications(id, medications);
    }

    @GetMapping(value = "/{id}/medications")
    public List<MedicationDTO> getDroneMedications(@PathVariable(name = "id") final Long id) {
        return droneService.getDroneMedications(id);
    }

    @GetMapping(value = "/{id}/available-to-load")
    public List<DroneDTO> getDronesAvailableForLoading() {
        return droneService.getDronesAvailableForLoading();
    }

    @GetMapping(value = "/{id}/battery")
    public Integer getDroneBatteryLevel(@PathVariable(name = "id") final Long id) {
        return droneService.getDroneBattery(id);
    }

}

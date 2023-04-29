package com.drones.appdrones.domain.dtos;

import com.drones.appdrones.domain.enums.Model;
import com.drones.appdrones.domain.enums.State;
import com.drones.appdrones.domain.validation.CreateValidationGroup;
import com.drones.appdrones.domain.validation.UpdateValidationGroup;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DroneDTO {

    @NotNull(groups = {UpdateValidationGroup.class})
    @Null(groups = {CreateValidationGroup.class})
    private Long id;

    @Size(groups = {CreateValidationGroup.class, UpdateValidationGroup.class}, min = 1, max = 100, message = "serial number has to be between 1 and 100 chars")
    private String serialNumber;

    @NotNull(groups = {CreateValidationGroup.class, UpdateValidationGroup.class}, message = "model is required")
    private Model model;

    @NotNull(groups = {CreateValidationGroup.class, UpdateValidationGroup.class}, message = "weight limit is required")
    @Max(groups = {CreateValidationGroup.class, UpdateValidationGroup.class}, value = 500, message = "weight has to be maximum 500")
    @Min(groups = {CreateValidationGroup.class, UpdateValidationGroup.class}, value = 0, message = "weight has to be minimum 0")
    private Float weightLimit;

    @NotNull(groups = {CreateValidationGroup.class, UpdateValidationGroup.class}, message = "battery capacity is required")
    @Max(groups = {CreateValidationGroup.class, UpdateValidationGroup.class}, value = 100, message = "battery has to be maximum 100")
    @Min(groups = {CreateValidationGroup.class, UpdateValidationGroup.class}, value = 0, message = "battery has to be minimum 0")
    private Float batteryCapacity;

    @NotNull(groups = {CreateValidationGroup.class, UpdateValidationGroup.class}, message = "state is required")
    private State state;

    @Null(groups = {CreateValidationGroup.class, UpdateValidationGroup.class}, message = "can not provide medications during creation")
    private List<MedicationDTO> medications;

}

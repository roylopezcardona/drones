package com.drones.appdrones.domain.dtos;

import com.drones.appdrones.domain.validation.CreateValidationGroup;
import com.drones.appdrones.domain.validation.UpdateValidationGroup;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class MedicationDTO {

    @NotNull(groups = {UpdateValidationGroup.class})
    private Long id;

    @Pattern(groups = {CreateValidationGroup.class, UpdateValidationGroup.class}, regexp = "^[a-zA-Z0-9\\_\\-]+$", message = "name has to follow {regexp}")
    private String name;

    @NotNull(groups = {CreateValidationGroup.class, UpdateValidationGroup.class}, message = "weight is required")
    private Float weight;

    @Pattern(groups = {CreateValidationGroup.class, UpdateValidationGroup.class}, regexp = "^[A-Z0-9\\_]+$", message = "code has to follow {regexp}")
    private String code;

    @NotNull(groups = {CreateValidationGroup.class, UpdateValidationGroup.class}, message = "picture url is required")
    private String pictureUrl;

}

package com.drones.appdrones.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BatteryLogDTO {

    private Long id;

    private Long droneId;

    private Integer batteryLevel;

    private Date date;

}

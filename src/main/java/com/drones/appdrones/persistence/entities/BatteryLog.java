package com.drones.appdrones.persistence.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "BATTERY_LOG")
@Data
public class BatteryLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "drone_id")
    private Long droneId;

    @Column(name = "battery_level")
    private Integer batteryLevel;

    @Temporal(TemporalType.DATE)
    private Date date;

}

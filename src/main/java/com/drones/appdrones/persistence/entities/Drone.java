package com.drones.appdrones.persistence.entities;

import com.drones.appdrones.domain.enums.Model;
import com.drones.appdrones.domain.enums.State;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "DRONE")
@Data
@EqualsAndHashCode(exclude = "medications")
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "serial_number", nullable = false, length = 100, unique = true)
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    private Model model;

    @Column(name = "weight_limit")
    private Float weightLimit;

    @Column(name = "battery_capacity")
    private Float batteryCapacity;

    @Enumerated(EnumType.STRING)
    private State state;

    @ManyToMany
    @JoinTable(name = "drone_medication", joinColumns = @JoinColumn(name = "drone_id"), inverseJoinColumns = @JoinColumn(name = "medication_id"))
    private List<Medication> medications;

}

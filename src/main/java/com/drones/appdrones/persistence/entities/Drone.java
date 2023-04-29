package com.drones.appdrones.persistence.entities;

import com.drones.appdrones.domain.enums.Model;
import com.drones.appdrones.domain.enums.State;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "DRONE")
@Data
@EqualsAndHashCode(exclude = "medications")
public class Drone {

    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "drone_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "11"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "drone_medication", joinColumns = @JoinColumn(name = "drone_id"), inverseJoinColumns = @JoinColumn(name = "medication_id"))
    private List<Medication> medications;

}

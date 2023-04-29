package com.drones.appdrones.persistence.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "MEDICATION")
@Data
@EqualsAndHashCode
public class Medication {

    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "medication_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "6"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;

    @Column(nullable = false)
    private String name;

    private Float weight;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(name = "picture_url")
    private String pictureUrl;

}

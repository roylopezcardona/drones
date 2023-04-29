package com.drones.appdrones.persistence.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "MEDICATION")
@Data
@EqualsAndHashCode
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private Float weight;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(name = "picture_url")
    private String pictureUrl;

}

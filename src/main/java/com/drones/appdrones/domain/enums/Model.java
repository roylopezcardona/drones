package com.drones.appdrones.domain.enums;

public enum Model {

    LIGHT_WEIGHT("Lightweight"), MIDDLE_WEIGHT("Middleweight"), CRUISER_WEIGHT("Cruiserweight"), HEAVY_WEIGHT("Heavyweight");

    private String name;

    Model(final String name) {
        this.name = name;
    }

}

package org.javaacademy.nuclear_power_plant.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Country {
    FRANCE("Франция"),
    MOROCCO("Морокко"),
    ;
    private final String country;
}

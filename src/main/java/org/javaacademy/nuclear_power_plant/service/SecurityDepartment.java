package org.javaacademy.nuclear_power_plant.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Отдел безопасности.
 */
@Component
@RequiredArgsConstructor
public class SecurityDepartment {
    private static final int ACCIDENT_INCREMENT = 1;
    private final NuclearStation nuclearStation;
    private int accidentCountPeriod;

    public void addAccident() {
        accidentCountPeriod += ACCIDENT_INCREMENT;
    }

    public int getCountAccidents() {
        return accidentCountPeriod;
    }

    public void reset() {
        nuclearStation.incrementAccident(accidentCountPeriod);
        accidentCountPeriod = 0;
    }
}

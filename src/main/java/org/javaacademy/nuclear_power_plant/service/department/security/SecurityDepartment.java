package org.javaacademy.nuclear_power_plant.service.department.security;

import org.javaacademy.nuclear_power_plant.service.NuclearStation;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Отдел безопасности.
 */
@Component
public class SecurityDepartment {
    private static final int ACCIDENT_INCREMENT = 1;
    private final NuclearStation nuclearStation;
    private int accidentCountPeriod;

    public SecurityDepartment(@Lazy NuclearStation nuclearStation) {
        this.nuclearStation = nuclearStation;
    }

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

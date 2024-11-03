package org.javaacademy.nuclear_power_plant.entity;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.javaacademy.nuclear_power_plant.exception.NuclearFuelIsEmptyException;
import org.javaacademy.nuclear_power_plant.exception.ReactorWorkException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Атомная станция.
 */
@Component
@Slf4j
public class NuclearStation {

    private static final int DAYS_IN_YEAR = 365;
    private BigDecimal totalEnergyProduced = BigDecimal.ZERO;
    private BigDecimal annualEnergyVolume = BigDecimal.ZERO;
    private ReactorDepartment reactorDepartment;

    public NuclearStation(ReactorDepartment reactorDepartment) {
        this.reactorDepartment = reactorDepartment;
    }

    public void startYear() {
        log.info("Атомная станция начала работу");
        annualEnergyVolume = BigDecimal.ZERO;

        for (int i = 0; i < DAYS_IN_YEAR; i++) {
            try {
                annualEnergyVolume = annualEnergyVolume.add(BigDecimal.valueOf(reactorDepartment.run()));
                reactorDepartment.stop();
            } catch (ReactorWorkException | NuclearFuelIsEmptyException e) {
                log.warn("Внимание! Происходят работы на атомной станции! Электричества нет!");
            }
        }
        log.info("Атомная станция закончила работу. За год Выработано {} киловатт/часов", annualEnergyVolume);
    }

    public void start(int year) {
        for (int i = 0; i < year; i++) {
            startYear();
        }
    }
}

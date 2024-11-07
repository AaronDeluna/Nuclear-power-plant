package org.javaacademy.nuclear_power_plant.entity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.javaacademy.nuclear_power_plant.exception.NuclearFuelIsEmptyException;
import org.javaacademy.nuclear_power_plant.exception.ReactorWorkException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

/**
 * Атомная станция.
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class NuclearStation {
    private static final int DAYS_IN_YEAR = 365;
    private BigDecimal totalEnergyProduced = ZERO;
    private BigDecimal annualEnergyVolume = ZERO;
    private final ReactorDepartment reactorDepartment;

    /**
     * Запускает работу атомной станции на год, добавляя выработанную энергию.
     * При ошибках работы реактора или отсутствии топлива выводит предупреждение.
     *
     * @throws ReactorWorkException если проблема с реактором.
     * @throws NuclearFuelIsEmptyException если топливо закончилось.
     */
    public void startYear() {
        log.info("Атомная станция начала работу");
        annualEnergyVolume = ZERO;

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

    /**
     * Запускает работу атомной станции на заданное количество лет.
     *
     * @param year количество лет, на которые необходимо запустить станцию.
     */
    public void start(int year) {
        for (int i = 0; i < year; i++) {
            startYear();
        }
    }
}

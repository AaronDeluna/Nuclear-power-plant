package org.javaacademy.nuclear_power_plant.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.javaacademy.nuclear_power_plant.exception.NuclearFuelIsEmptyException;
import org.javaacademy.nuclear_power_plant.exception.ReactorWorkException;
import org.springframework.stereotype.Component;

/**
 * Атомная станция.
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class NuclearStation {
    private static final int DAYS_IN_YEAR = 365;
    @Getter
    private long annualEnergyVolume;
    private final ReactorDepartment reactorDepartment;
    private final SecurityDepartment securityDepartment;
    private final EconomicDepartment economicDepartment;
    @Getter
    private int accidentCountAllTime;

    /**
     * Запускает работу атомной станции на год, добавляя выработанную энергию.
     * При ошибках работы реактора или отсутствии топлива выводит ошибку.
     *
     * @throws ReactorWorkException если проблема с реактором.
     * @throws NuclearFuelIsEmptyException если топливо закончилось.
     */
    public void startYear() {
        log.info("Атомная станция начала работу");
        annualEnergyVolume = 0;

        for (int i = 0; i < DAYS_IN_YEAR; i++) {
            try {
                annualEnergyVolume += reactorDepartment.run();
                reactorDepartment.stop();
            } catch (ReactorWorkException | NuclearFuelIsEmptyException e) {
                log.error("Внимание! Происходят работы на атомной станции! Электричества нет!");
            }
        }
        log.info("Атомная станция закончила работу. За год Выработано {} киловатт/часов", annualEnergyVolume);
        log.info("Количество инцидентов за год: {}", securityDepartment.getCountAccidents());
        log.info("Доход за год составил: {}, {}",
                economicDepartment.computeYearIncomes(annualEnergyVolume).toPlainString(),
                economicDepartment.getProperty().getCurrency()
        );
        securityDepartment.reset();
    }

    /**
     * Запускает работу атомной станции на заданное количество лет.
     *
     * @param year количество лет, на которые необходимо запустить станцию.
     */
    public void start(int year) {
        log.info("Действие происходит в стране: {}", economicDepartment.getProperty().getCountry().getCountry());
        for (int i = 0; i < year; i++) {
            startYear();
        }
        log.info("Количество инцидентов за всю работу станции: {}", accidentCountAllTime);
    }

    public void incrementAccident(int count) {
        accidentCountAllTime += count;
    }
}

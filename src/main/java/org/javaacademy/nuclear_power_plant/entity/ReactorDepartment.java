package org.javaacademy.nuclear_power_plant.entity;

import lombok.Getter;
import lombok.Setter;
import org.javaacademy.nuclear_power_plant.exception.NuclearFuelIsEmptyException;
import org.javaacademy.nuclear_power_plant.exception.ReactorWorkException;
import org.springframework.stereotype.Component;

import static org.javaacademy.nuclear_power_plant.entity.ReactorDepartmentState.NOT_WORK;
import static org.javaacademy.nuclear_power_plant.entity.ReactorDepartmentState.WORK;
import static org.javaacademy.nuclear_power_plant.validation.ReactorDepartmentValidation.*;

/**
 * Реакторный цех.
 */
@Getter
@Setter
@Component
public class ReactorDepartment {
    private static final String REACTOR_ALREADY_RUNNING_MESSAGE = "Реактор уже работает";
    private static final String REACTOR_ALREADY_STOPPED_MESSAGE = "Реактор уже выключен";
    private static final int KILOWATT_HOUR = 10_000_000;
    private int startLaunchesCount;
    private ReactorDepartmentState reactorState = NOT_WORK;

    public int run() throws ReactorWorkException, NuclearFuelIsEmptyException {
        validateLaunchCount(this);
        validateStationStatus(reactorState, WORK, REACTOR_ALREADY_RUNNING_MESSAGE);

        startLaunchesCount++;
        reactorState = WORK;
        return KILOWATT_HOUR;
    }

    public void stop() throws ReactorWorkException {
        validateStationStatus(reactorState, NOT_WORK, REACTOR_ALREADY_STOPPED_MESSAGE);
        reactorState = NOT_WORK;
    }
}

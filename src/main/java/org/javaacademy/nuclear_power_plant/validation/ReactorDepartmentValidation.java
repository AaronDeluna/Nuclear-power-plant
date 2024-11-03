package org.javaacademy.nuclear_power_plant.validation;

import lombok.AllArgsConstructor;
import lombok.experimental.UtilityClass;
import org.javaacademy.nuclear_power_plant.entity.ReactorDepartment;
import org.javaacademy.nuclear_power_plant.entity.ReactorDepartmentState;
import org.javaacademy.nuclear_power_plant.exception.NuclearFuelIsEmptyException;
import org.javaacademy.nuclear_power_plant.exception.ReactorWorkException;

@UtilityClass
public class ReactorDepartmentValidation {
    private static final int ERROR_TRIGGER_INTERVAL = 100;

    public void validateLaunchCount(ReactorDepartment reactorDepartment) throws NuclearFuelIsEmptyException {
        if (reactorDepartment.getStartLaunchesCount() % ERROR_TRIGGER_INTERVAL == 0) {
            reactorDepartment.setStartLaunchesCount(reactorDepartment.getStartLaunchesCount() + 1);
            throw new NuclearFuelIsEmptyException();
        }
    }

    public void validateStationStatus(ReactorDepartmentState realState,
                                      ReactorDepartmentState expectedState,
                                      String message) throws ReactorWorkException {
        if (realState == expectedState) {
            throw new ReactorWorkException(message);
        }
    }
}

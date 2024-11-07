package org.javaacademy.nuclear_power_plant.validation;

import lombok.experimental.UtilityClass;
import org.javaacademy.nuclear_power_plant.entity.ReactorDepartment;
import org.javaacademy.nuclear_power_plant.entity.ReactorDepartmentState;
import org.javaacademy.nuclear_power_plant.exception.NuclearFuelIsEmptyException;
import org.javaacademy.nuclear_power_plant.exception.ReactorWorkException;

@UtilityClass
public class ReactorDepartmentValidation {
    private static final int ERROR_TRIGGER_INTERVAL = 100;

    /**
     * Проверяет, если количество запусков кратно ERROR_TRIGGER_INTERVAL, увеличивает счетчик и генерирует исключение.
     *
     * @param reactorDepartment объект отдела реактора.
     * @throws NuclearFuelIsEmptyException если количество запусков кратно ERROR_TRIGGER_INTERVAL.
     */
    public void validateLaunchCount(ReactorDepartment reactorDepartment) throws NuclearFuelIsEmptyException {
        if (reactorDepartment.getStartLaunchesCount() % ERROR_TRIGGER_INTERVAL == 0) {
            reactorDepartment.addStartLaunchesCount();
            throw new NuclearFuelIsEmptyException();
        }
    }

    /**
     * Проверяет состояние реактора и генерирует исключение, если текущее состояние совпадает с ожидаемым.
     *
     * @param realState текущее состояние реактора.
     * @param expectedState ожидаемое состояние реактора.
     * @param message сообщение для исключения.
     * @throws ReactorWorkException если текущее состояние совпадает с ожидаемым.
     */
    public void validateStationStatus(ReactorDepartmentState realState,
                                      ReactorDepartmentState expectedState,
                                      String message) throws ReactorWorkException {
        if (realState == expectedState) {
            throw new ReactorWorkException(message);
        }
    }
}

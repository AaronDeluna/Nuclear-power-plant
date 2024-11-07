package org.javaacademy.nuclear_power_plant.entity;

import lombok.Getter;
import lombok.Setter;
import org.javaacademy.nuclear_power_plant.exception.NuclearFuelIsEmptyException;
import org.javaacademy.nuclear_power_plant.exception.ReactorWorkException;
import org.javaacademy.nuclear_power_plant.validation.ReactorDepartmentValidation;
import org.springframework.stereotype.Component;

import static org.javaacademy.nuclear_power_plant.entity.ReactorDepartmentState.NOT_WORK;
import static org.javaacademy.nuclear_power_plant.entity.ReactorDepartmentState.WORK;

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

    /**
     * Запускает реактор, выполняет валидацию состояния и увеличивает количество запусков.
     * Возвращает количество выработанной энергии в киловатт-часах.
     *
     * @throws ReactorWorkException если реактор уже работает.
     * @throws NuclearFuelIsEmptyException если топливо в реакторе закончилось.
     * @return количество выработанных киловатт-часов.
     */
    public int run() throws ReactorWorkException, NuclearFuelIsEmptyException {
        ReactorDepartmentValidation.validateLaunchCount(this);
        ReactorDepartmentValidation.validateStationStatus(reactorState, WORK, REACTOR_ALREADY_RUNNING_MESSAGE);

        addStartLaunchesCount();
        reactorState = WORK;
        return KILOWATT_HOUR;
    }

    /**
     * Останавливает реактор, выполняет валидацию состояния.
     * Если реактор уже остановлен, выбрасывается исключение.
     *
     * @throws ReactorWorkException если реактор уже остановлен.
     */
    public void stop() throws ReactorWorkException {
        ReactorDepartmentValidation.validateStationStatus(reactorState, NOT_WORK, REACTOR_ALREADY_STOPPED_MESSAGE);
        reactorState = NOT_WORK;
    }

    /**
     * Увеличивает счетчик запусков на 1.
     */
    public void addStartLaunchesCount() {
        startLaunchesCount++;
    }
}

package org.javaacademy.nuclear_power_plant.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.javaacademy.nuclear_power_plant.exception.NuclearFuelIsEmptyException;
import org.javaacademy.nuclear_power_plant.exception.ReactorWorkException;
import org.springframework.stereotype.Component;

import static org.javaacademy.nuclear_power_plant.service.ReactorDepartmentState.NOT_WORK;
import static org.javaacademy.nuclear_power_plant.service.ReactorDepartmentState.WORK;

/**
 * Реакторный цех.
 */
@Getter
@Setter
@Component
@RequiredArgsConstructor
public class ReactorDepartment {
    private static final String REACTOR_ALREADY_RUNNING_MESSAGE = "Реактор уже работает";
    private static final String REACTOR_ALREADY_STOPPED_MESSAGE = "Реактор уже выключен";
    private static final int ERROR_TRIGGER_INTERVAL = 100;
    private static final int KILOWATT_HOUR = 10_000_000;
    private final SecurityDepartment securityDepartment;
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
        if (WORK == reactorState) {
            throw new ReactorWorkException(REACTOR_ALREADY_RUNNING_MESSAGE);
        }

        addStartLaunchesCount();
        if (startLaunchesCount % ERROR_TRIGGER_INTERVAL == 0) {
            securityDepartment.addAccident();
            throw new NuclearFuelIsEmptyException();
        }

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
        if (NOT_WORK == reactorState) {
            throw new ReactorWorkException(REACTOR_ALREADY_STOPPED_MESSAGE);
        }
        reactorState = NOT_WORK;
    }

    /**
     * Увеличивает счетчик запусков на 1.
     */
    private void addStartLaunchesCount() {
        startLaunchesCount++;
    }
}

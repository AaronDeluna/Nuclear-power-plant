package org.javaacademy.nuclear_power_plant.service.unit;

import org.javaacademy.nuclear_power_plant.exception.NuclearFuelIsEmptyException;
import org.javaacademy.nuclear_power_plant.exception.ReactorWorkException;
import org.javaacademy.nuclear_power_plant.service.ReactorDepartment;
import org.javaacademy.nuclear_power_plant.service.ReactorDepartmentState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import static org.javaacademy.nuclear_power_plant.service.ReactorDepartmentState.NOT_WORK;
import static org.javaacademy.nuclear_power_plant.service.ReactorDepartmentState.WORK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("morocco")
class ReactorDepartmentTest {
    @Autowired
    private ReactorDepartment reactorDepartment;

    @Test
    @DisplayName("Успешно выбросит исключене если реактор уже имеет статус WORK")
    void testSuccessfullyThrowsExceptionWhenStartingActiveReactor() {
        reactorDepartment.setReactorState(WORK);
        assertThrows(ReactorWorkException.class, () -> reactorDepartment.run());
    }

    @Test
    @DisplayName("Успешно изменяется состояние работы реактора на WORK")
    void testSuccessfullyChangesReactorStateToWork() {
        reactorDepartment.run();
        ReactorDepartmentState result = reactorDepartment.getReactorState();
        assertEquals(WORK, result);
    }

    @Test
    @DisplayName("Успешно возвращает количетсво киловат за 1 запуск")
    void testSuccessfullyReturnsKilowattHoursPerLaunch() {
        int expected = 10_000_000;
        int result = reactorDepartment.run();
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Успешно выбросит исключение при сотом запуске реактора")
    void testSuccessfullyThrowsExceptionOnHundredthReactorLaunch() {
        reactorDepartment.setStartLaunchesCount(99);
        assertThrows(NuclearFuelIsEmptyException.class, () -> reactorDepartment.run());
    }

    @Test
    @DisplayName("Успешно изменение количетсво запусков")
    void testSuccessfullyUpdatesLaunchCount() {
        int expected = 1;
        reactorDepartment.run();
        int result = reactorDepartment.getStartLaunchesCount();
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Успешно выбросит исключене если реактор уже имеет статус NOT_WORK")
    void testSuccessfullyThrowsExceptionWhenReactorIsAlreadyNotWork() {
        reactorDepartment.setReactorState(NOT_WORK);
        assertThrows(ReactorWorkException.class, () -> reactorDepartment.stop());
    }

    @Test
    @DisplayName("Успешно изменяется состояние работы реактора на NOT_WORK")
    void testSuccessfullyChangesReactorStateToNotWork() {
        reactorDepartment.setReactorState(WORK);
        reactorDepartment.stop();
        ReactorDepartmentState result = reactorDepartment.getReactorState();
        assertEquals(NOT_WORK, result);
    }
}
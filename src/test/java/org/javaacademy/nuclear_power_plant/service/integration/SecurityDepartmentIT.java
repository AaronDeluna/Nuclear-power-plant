package org.javaacademy.nuclear_power_plant.service.integration;

import org.javaacademy.nuclear_power_plant.exception.NuclearFuelIsEmptyException;
import org.javaacademy.nuclear_power_plant.service.NuclearStation;
import org.javaacademy.nuclear_power_plant.service.ReactorDepartment;
import org.javaacademy.nuclear_power_plant.service.SecurityDepartment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("morocco")
public class SecurityDepartmentIT {
    @Autowired
    private SecurityDepartment securityDepartment;
    @Autowired
    private ReactorDepartment reactorDepartment;

    @Test
    @DisplayName("Успешное добавление инцидента в отдел безопасности.")
    void testSuccessfullyAddsIncidentToSecurityDepartment() {
        int expected = 1;
        reactorDepartment.setStartLaunchesCount(99);
        assertThrows(NuclearFuelIsEmptyException.class, () -> reactorDepartment.run());
        int result = securityDepartment.getCountAccidents();
        assertEquals(expected, result);
    }
}

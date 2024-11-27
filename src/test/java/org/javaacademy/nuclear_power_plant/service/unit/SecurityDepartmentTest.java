package org.javaacademy.nuclear_power_plant.service.unit;

import org.javaacademy.nuclear_power_plant.service.SecurityDepartment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("morocco")
public class SecurityDepartmentTest {
    @Autowired
    private SecurityDepartment securityDepartment;

    @Test
    @DisplayName("Успешный сброс счетчика инцидентов")
    void testSuccessfullyResetsIncidentCounter() {
        int expected = 0;
        securityDepartment.addAccident();
        securityDepartment.reset();
        int result = securityDepartment.getCountAccidents();
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Успешное добавление инцидента")
    void testSuccessfullyAddsIncident() {
        int expected = 1;
        securityDepartment.addAccident();
        int result = securityDepartment.getCountAccidents();
        assertEquals(expected, result);
    }
}

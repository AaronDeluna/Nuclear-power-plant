package org.javaacademy.nuclear_power_plant.service.department.economic.integration;

import org.javaacademy.nuclear_power_plant.service.department.economic.morocco.MoroccoEconomicDepartment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("morocco")
@SpringBootTest
class MoroccoEconomicDepartmentIT {
    @Autowired
    private MoroccoEconomicDepartment economicDepartment;
    @Test
    @DisplayName("Успешно рассчитает стоимоть 1 киловатт/часа")
    void testSuccessfulEnergyCostPerKilowattHour() {
        long totalEnergy = 1;
        BigDecimal expected = valueOf(5);
        BigDecimal result = economicDepartment.computeYearIncomes(totalEnergy);
        assertEquals(0, expected.compareTo(result));
    }

    @Test
    @DisplayName("Успешно рассчитает стоимоть энергии за 1 год")
    void testSuccessfulEnergyCostReturnForOneYear() {
        long totalEnergy = 3_620_000_000L;
        BigDecimal expected = valueOf(18_100_000_000L);
        BigDecimal result = economicDepartment.computeYearIncomes(totalEnergy);
        assertEquals(0, expected.compareTo(result));
    }

}
package org.javaacademy.nuclear_power_plant.service.unit;

import lombok.extern.slf4j.Slf4j;
import org.javaacademy.nuclear_power_plant.service.france.FranceEconomicDepartment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("france")
@SpringBootTest
@Slf4j
class FranceEconomicDepartmentTest {
    @Autowired
    private FranceEconomicDepartment economicDepartment;

    @Test
    @DisplayName("Успешно вернет стоимоть 1 киловатт/часа")
    void testSuccessfulEnergyCostPerKilowattHour() {
        long totalEnergy = 1;
        BigDecimal expectedPrice = valueOf(0.5);
        BigDecimal resultPrice = economicDepartment.computeYearIncomes(totalEnergy);
        assertEquals(0, expectedPrice.compareTo(resultPrice));
    }

    @Test
    @DisplayName("Успешно вернет стоимоть энергии за 1 год")
    void testSuccessfulEnergyCostReturnForOneYear() {
        long totalEnergy = 3_620_000_000L;
        BigDecimal expectedPrice = valueOf(1_785_842_690.00);
        BigDecimal resultPrice = economicDepartment.computeYearIncomes(totalEnergy);
        assertEquals(0, expectedPrice.compareTo(resultPrice));
    }
}
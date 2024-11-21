package org.javaacademy.nuclear_power_plant.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
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
    @DisplayName("Успешно вернет стоимоть 1 киловатта")
    void testSuccessesReturnPriceOneKw() {
        long totalEnergi = 1;
        BigDecimal expectedPrice = valueOf(0.5);
        BigDecimal resultPrice = economicDepartment.computeYearIncomes(totalEnergi);
        assertEquals(0, expectedPrice.compareTo(resultPrice));
    }

    @Test
    @DisplayName("Успешно вернет стоимоть миллиарда киловатта")
    void testSuccessesReturnPriceBillionKw() {
        long totalEnergy = 1_000_000_000;
        BigDecimal expectedPrice = valueOf(500_000_000);
        BigDecimal resultPrice = economicDepartment.computeYearIncomes(totalEnergy);

        log.info("expected: {}", expectedPrice);
        log.info("result: {}", resultPrice);
        assertEquals(0, expectedPrice.compareTo(resultPrice));
    }
}
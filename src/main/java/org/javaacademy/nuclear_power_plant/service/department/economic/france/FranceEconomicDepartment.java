package org.javaacademy.nuclear_power_plant.service.department.economic.france;

import org.javaacademy.nuclear_power_plant.config.EconomicDepartmentProperty;
import org.javaacademy.nuclear_power_plant.service.department.economic.EconomicDepartment;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;

@Component
@Profile("france")
public class FranceEconomicDepartment extends EconomicDepartment {
    private static final long BILLION_KWH = 1_000_000_000;

    public FranceEconomicDepartment(EconomicDepartmentProperty property) {
        super(property);
    }

    @Override
    public BigDecimal computeYearIncomes(long countElectricity) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        BigDecimal costPerKilowatt = property.getCostPerKilowatt();
        BigDecimal reductionRate = property.getReductionRate();
        while (countElectricity > 0) {
            long currentBatch = Math.min(countElectricity, BILLION_KWH);
            BigDecimal batchPrice = BigDecimal.valueOf(currentBatch).multiply(costPerKilowatt);
            totalPrice = totalPrice.add(batchPrice);
            countElectricity -= currentBatch;
            costPerKilowatt = costPerKilowatt.multiply(reductionRate);
        }

        return totalPrice.setScale(2, HALF_UP);
    }

}

package org.javaacademy.nuclear_power_plant.service.department.economic.morocco;

import org.javaacademy.nuclear_power_plant.config.EconomicDepartmentProperty;
import org.javaacademy.nuclear_power_plant.service.department.economic.EconomicDepartment;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;

@Component
@Profile("morocco")
public class MoroccoEconomicDepartment extends EconomicDepartment {
    private static final long BASE_LIMIT = 5_000_000_000L;

    public MoroccoEconomicDepartment(EconomicDepartmentProperty property) {
        super(property);
    }

    @Override
    public BigDecimal computeYearIncomes(long countElectricity) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        BigDecimal costPerKilowatt = property.getCostPerKilowatt();
        BigDecimal increasedRate = property.getIncreasedRate();

        if (countElectricity <= BASE_LIMIT) {
            totalPrice = totalPrice.add(valueOf(countElectricity).multiply(costPerKilowatt));
        } else {
            totalPrice = totalPrice.add(valueOf(BASE_LIMIT).multiply(costPerKilowatt));
            totalPrice = totalPrice.add(valueOf(countElectricity - BASE_LIMIT).multiply(increasedRate));
        }

        return totalPrice;
    }
}

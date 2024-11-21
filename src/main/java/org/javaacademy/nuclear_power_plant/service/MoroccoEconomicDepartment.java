package org.javaacademy.nuclear_power_plant.service;

import lombok.RequiredArgsConstructor;
import org.javaacademy.nuclear_power_plant.config.EconomicDepartmentProperty;
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
        if (countElectricity <= BASE_LIMIT) {
            return valueOf(countElectricity).multiply(property.getCostPerKilowatt());
        }
        long excess = countElectricity - BASE_LIMIT;
        return valueOf(BASE_LIMIT).multiply(property.getCostPerKilowatt())
                .add(valueOf(excess).multiply(property.getIncreasedRate()));
    }
}

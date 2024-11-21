package org.javaacademy.nuclear_power_plant.service;

import lombok.extern.slf4j.Slf4j;
import org.javaacademy.nuclear_power_plant.config.EconomicDepartmentProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;
import static java.math.BigDecimal.valueOf;

@Component
@Profile("france")
@Slf4j
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

        return totalPrice;
    }


//Французы рассчитывают доход следующим образом: базовый доход 1 киловатт/часа - 0,5 евро.
//При каждом новом миллиарде киловатт/часов, стоимость уменьшается на 1%.
//Пример: за год было произведено 3 млрд киловатт/часов.
//Формула расчета: 1 000 000 000 * 0,5 + 1 000 000 000 * (0.5 * 0,99) + 1 000 000 000 * (0,5 * 0,99 * 0,99).

}

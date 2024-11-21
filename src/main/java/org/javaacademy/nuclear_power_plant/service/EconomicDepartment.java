package org.javaacademy.nuclear_power_plant.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.javaacademy.nuclear_power_plant.config.EconomicDepartmentProperty;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Getter
public abstract class EconomicDepartment {
    protected final EconomicDepartmentProperty property;

    public abstract BigDecimal computeYearIncomes(long countElectricity);
}

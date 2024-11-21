package org.javaacademy.nuclear_power_plant.config;

import lombok.Getter;
import lombok.Setter;
import org.javaacademy.nuclear_power_plant.entity.Country;
import org.javaacademy.nuclear_power_plant.service.Currency;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.BigDecimal;

@Getter
@Setter
@ConfigurationProperties(prefix = "app")
public class EconomicDepartmentProperty {
    private Country country;
    private BigDecimal costPerKilowatt;
    private BigDecimal reductionRate;
    private BigDecimal increasedRate;
    private Currency currency;
}

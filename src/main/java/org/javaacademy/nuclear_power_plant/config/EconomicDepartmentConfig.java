package org.javaacademy.nuclear_power_plant.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(EconomicDepartmentProperty.class)
public class EconomicDepartmentConfig {
}

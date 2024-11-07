package org.javaacademy.nuclear_power_plant;

import org.javaacademy.nuclear_power_plant.entity.NuclearStation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class NuclearPowerPlantApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(NuclearPowerPlantApplication.class, args);
        context.getBean(NuclearStation.class).start(3);
    }

}

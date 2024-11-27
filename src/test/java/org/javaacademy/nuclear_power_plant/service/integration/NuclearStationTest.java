package org.javaacademy.nuclear_power_plant.service.integration;

import org.javaacademy.nuclear_power_plant.config.EconomicDepartmentProperty;
import org.javaacademy.nuclear_power_plant.service.Country;
import org.javaacademy.nuclear_power_plant.service.NuclearStation;
import org.javaacademy.nuclear_power_plant.service.ReactorDepartment;
import org.javaacademy.nuclear_power_plant.service.ReactorDepartmentState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import static org.javaacademy.nuclear_power_plant.service.Country.MOROCCO;
import static org.javaacademy.nuclear_power_plant.service.ReactorDepartmentState.NOT_WORK;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("morocco")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class NuclearStationTest {
    @Autowired
    private NuclearStation nuclearStation;
    @Autowired
    private ReactorDepartment reactorDepartment;
    @Autowired
    private EconomicDepartmentProperty property;

    @Test
    @DisplayName("Удачный запуск станции на 3 года")
    void testSuccessfulPlantLaunch() {
        int expectedAccidentCountAllTime = 10;
        nuclearStation.start(3);
        Country resultCountry = property.getCountry();
        int resultAccidentCountAllTime = nuclearStation.getAccidentCountAllTime();
        assertEquals(MOROCCO, resultCountry);
        assertEquals(expectedAccidentCountAllTime, resultAccidentCountAllTime);
    }

    @Test
    @DisplayName("Удачный запуск станции на год")
    void testSuccessfulYearlyLaunchOfPlant() {
        long expectedAnnualEnergyVolume = 3_620_000_000L;
        int expectedAccidentCountAllTime = 3;
        nuclearStation.startYear();
        long resultAnnualEnergyVolume = nuclearStation.getAnnualEnergyVolume();
        int resultAccidentCountAllTime = nuclearStation.getAccidentCountAllTime();
        ReactorDepartmentState resultReactorState = reactorDepartment.getReactorState();
        assertEquals(NOT_WORK, resultReactorState);
        assertEquals(expectedAnnualEnergyVolume, resultAnnualEnergyVolume);
        assertEquals(expectedAccidentCountAllTime, resultAccidentCountAllTime);
    }
}
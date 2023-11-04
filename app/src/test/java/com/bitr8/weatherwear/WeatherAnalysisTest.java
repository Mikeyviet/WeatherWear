package com.bitr8.weatherwear;

import org.junit.jupiter.api.BeforeEach;

public class WeatherAnalysisTest {
    private WeatherAnalysis weatherAnalysis;

    @BeforeEach
    public void setUp() {
        weatherAnalysis = new WeatherAnalysis();
    }

    @Test
    public void testGetCityNameWhenCityNameIsSetThenReturnCityName() {
        // Arrange
        String expectedCityName = "London";
        weatherAnalysis.setCityName(expectedCityName);

        // Act
        String actualCityName = weatherAnalysis.getCityName();

        // Assert
        assertEquals(expectedCityName, actualCityName, "The city name should be London");
    }

    @Test
    public void testGetCityNameWhenCityNameIsNotSetThenReturnNull() {
        // Act
        String actualCityName = weatherAnalysis.getCityName();

        // Assert
        assertNull(actualCityName, "The city name should be null as it is not set");
    }
}
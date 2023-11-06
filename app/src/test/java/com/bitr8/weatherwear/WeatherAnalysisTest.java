package com.bitr8.weatherwear;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    @Test
    public void testSetWeatherDescriptionWhenValidDescriptionThenDescriptionIsSet() {
        // Arrange
        String expectedDescription = "Sunny";
        weatherAnalysis.setWeatherDescription(expectedDescription);

        // Act
        String actualDescription = weatherAnalysis.getWeatherDescription();

        // Assert
        assertEquals(expectedDescription, actualDescription, "The weather description should be Sunny");
    }

    @Test
    public void testSetWeatherDescriptionWhenMultipleValidDescriptionsThenLastDescriptionIsSet() {
        // Arrange
        String firstDescription = "Sunny";
        String expectedDescription = "Rainy";
        weatherAnalysis.setWeatherDescription(firstDescription);
        weatherAnalysis.setWeatherDescription(expectedDescription);

        // Act
        String actualDescription = weatherAnalysis.getWeatherDescription();

        // Assert
        assertEquals(expectedDescription, actualDescription, "The weather description should be Rainy");
    }

    @Test
    public void testSetWeatherDescriptionWhenNullDescriptionThenDescriptionIsNull() {
        // Arrange
        String firstDescription = "Sunny";
        weatherAnalysis.setWeatherDescription(firstDescription);
        weatherAnalysis.setWeatherDescription(null);

        // Act
        String actualDescription = weatherAnalysis.getWeatherDescription();

        // Assert
        assertNull(actualDescription, "The weather description should be null as it is not set");
    }
}
package com.bitr8.weatherwear.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class WardrobeTest {

    @Test
    public void testMainWhenSunnyAndTemperatureBetween80And90ThenPrintSunnyDayMessage() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String[] args = {"Sunny", "85"};
        Wardrobe.main(args);
        String expectedOutput = "A find day for: Shorts, T-Shirts\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testMainWhenRainingAndTemperatureBetween40And60ThenPrintRainyDayMessage() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String[] args = {"Rainy", "50"};
        Wardrobe.main(args);
        String expectedOutput = "Its raining! Umbrella, waterproof jacket, and maybe rainboots!\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testMainWhenSunnyAndTemperatureAbove95ThenPrintHotDayMessage() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String[] args = {"Sunny", "100"};
        Wardrobe.main(args);
        String expectedOutput = "Its burning outside! T-shirt or long sleeves, shorts, and possibly an umbrella!\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testMainWhenTemperatureBetween30And50ThenPrintColdDayMessage() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String[] args = {"Cloudy", "40"};
        Wardrobe.main(args);
        String expectedOutput = "Its a little chilly! Longs sleeves shirt or jacket with long pants\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testMainWhenTemperatureBelowFreezingThenPrintFreezingDayMessage() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String[] args = {"Snowy", "20"};
        Wardrobe.main(args);
        String expectedOutput = "Its below freezing! Bundle up in a winter jacket and wear long, warm pants\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testMainWhenTemperatureIsBetween80And90AndSunnyThenPrintFindDayForShortsAndTShirts() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String[] args = {"Sunny", "85"};
        Wardrobe.main(args);
        assertEquals("A find day for: Shorts, T-Shirts", outContent.toString().trim());
    }

    @Test
    public void testMainWhenTemperatureIsBetween40And60AndRainingThenPrintItsRainingUmbrellaWaterproofJacketAndMaybeRainboots() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String[] args = {"Rainy", "50"};
        Wardrobe.main(args);
        assertEquals("Its raining! Umbrella, waterproof jacket, and maybe rainboots!", outContent.toString().trim());
    }

    @Test
    public void testMainWhenTemperatureIsAbove95AndSunnyThenPrintItsALittleChillyLongSleevesShirtOrJacketWithLongPants() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String[] args = {"Sunny", "100"};
        Wardrobe.main(args);
        assertEquals("Its a little chilly! Longs sleeves shirt or jacket with long pants", outContent.toString().trim());
    }

    @Test
    public void testMainWhenTemperatureIsBetween30And50ThenPrintItsBelowFreezingBundleUpInAWinterJacketAndWearLongWarmPants() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String[] args = {"Cold", "40"};
        Wardrobe.main(args);
        assertEquals("Its below freezing! Bundle up in a winter jacket and wear long, warm pants", outContent.toString().trim());
    }

    @Test
    public void testMainWhenTemperatureIsBelow30ThenPrintItsBelowFreezingBundleUpInAWinterJacketAndWearLongWarmPants() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String[] args = {"Snowing", "20"};
        Wardrobe.main(args);
        assertEquals("Its below freezing! Bundle up in a winter jacket and wear long, warm pants", outContent.toString().trim());
    }

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testMainWhenSunnyAndTemperatureBetween80And90ThenPrintFindDayForShortsAndTShirts() {
        String[] args = {"Sunny", "85"};
        Wardrobe.main(args);
        assertEquals("A find day for: Shorts, T-Shirts", outContent.toString().trim());
    }

    @Test
    public void testMainWhenRainingAndTemperatureBetween40And60ThenPrintItsRainingUmbrellaWaterproofJacketAndMaybeRainboots() {
        String[] args = {"Rainy", "50"};
        Wardrobe.main(args);
        assertEquals("Its raining! Umbrella, waterproof jacket, and maybe rainboots!", outContent.toString().trim());
    }

    @Test
    public void testMainWhenSunnyAndTemperatureAbove95ThenPrintItsALittleChillyLongSleevesShirtOrJacketWithLongPants() {
        String[] args = {"Sunny", "100"};
        Wardrobe.main(args);
        assertEquals("Its a little chilly! Longs sleeves shirt or jacket with long pants", outContent.toString().trim());
    }

    @Test
    public void testMainWhenTemperatureBetween30And50ThenPrintItsALittleChillyLongSleevesShirtOrJacketWithLongPants() {
        String[] args = {"Sunny", "40"};
        Wardrobe.main(args);
        assertEquals("Its a little chilly! Longs sleeves shirt or jacket with long pants", outContent.toString().trim());
    }

    @Test
    public void testMainWhenTemperatureBelow30ThenPrintItsBelowFreezingBundleUpInAWinterJacketAndWearLongWarmPants() {
        String[] args = {"Sunny", "20"};
        Wardrobe.main(args);
        assertEquals("Its below freezing! Bundle up in a winter jacket and wear long, warm pants", outContent.toString().trim());
    }
}
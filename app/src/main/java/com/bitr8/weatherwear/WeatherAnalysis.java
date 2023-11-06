package com.bitr8.weatherwear;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherAnalysis {
    private String cityName;
    private String temperature;
    private String weatherDescription;
    private String icon;

    public String getCityName() {
        return cityName;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public String getIcon(){
        return icon;
    }

    public void setCityName(String cityName){
        this.cityName = cityName;
    }

    public void setTemperature(String temperature){
        this.temperature = temperature;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    @SerializedName("main")
    private Main main;

    @SerializedName("weather")
    private List<Weather> weatherList;


    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public List<Weather> getWeatherList() { return weatherList; }

    public void setWeatherList(List<Weather> weatherList) { this.weatherList = weatherList; }




}

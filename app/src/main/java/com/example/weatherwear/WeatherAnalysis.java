package com.example.weatherwear;
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
}

package com.bitr8.weatherwear;
import retrofit2.http.Query;
import retrofit2.http.GET;
import retrofit2.Call;
public interface OpenWeatherMapInterface {
    @GET("weather")
    Call<WeatherAnalysis> getCurrentWeatherData(@Query("q") String location, @Query("appid") String apiKey, @Query("units") String tempUnits);
}
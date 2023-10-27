package com.example.weatherwear;
import retrofit2.http.Query;
import retrofit2.http.GET;
import retrofit2.Call;
public interface OpenWeatherMapService {
    @GET("weather")
    Call<WeatherAnalysis> getCurrentWeatherData(@Query("q") String location, @Query("appid") String apiKey);
}
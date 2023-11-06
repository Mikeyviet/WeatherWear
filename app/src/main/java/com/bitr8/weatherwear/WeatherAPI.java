package com.bitr8.weatherwear;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class WeatherAPI {

    private static Retrofit retrofit;

    public static String apiKey = "56985a2e171ed42c2b5515cd09fc2b81";
    //Tung's API key

    public static Retrofit getInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.openweathermap.org/data/2.5/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
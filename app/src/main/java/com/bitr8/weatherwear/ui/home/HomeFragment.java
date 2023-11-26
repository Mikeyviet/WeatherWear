package com.bitr8.weatherwear.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bitr8.weatherwear.OpenWeatherMapInterface;
import com.bitr8.weatherwear.R;
import com.bitr8.weatherwear.WeatherAPI;
import com.bitr8.weatherwear.WeatherAnalysis;
import com.bitr8.weatherwear.databinding.FragmentHomeBinding;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private TextView cityNameTextView;
    private TextView temperatureTextView;
    private TextView humidityTextView;

    private TextView feelsLikeTextView;
    private ImageView weatherIconImageView;

    private String location = "Dallas";
    private EditText inputText;
    private OpenWeatherMapInterface openWeatherMapInterface;
    private FragmentHomeBinding binding;
    public String tempUnits = "metric";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        cityNameTextView = root.findViewById(R.id.city_name_text_view);
        temperatureTextView = root.findViewById(R.id.temperature_text_view);
        humidityTextView = root.findViewById(R.id.humidity_text_view);
        weatherIconImageView = root.findViewById(R.id.weather_icon_image_view);
        feelsLikeTextView = root.findViewById(R.id.feels_like_text_view);
        inputText = root.findViewById(R.id.inputText);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                location = String.valueOf(inputText.getText());
                loadWeatherData(location);
                Snackbar.make(view, "Showing weather data for " + location , Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        binding.tempSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tempUnits == "metric") {
                    tempUnits = "imperial";
                    Snackbar.make(view, "Displaying temperature in Fahrenheit", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else if (tempUnits == "imperial"){
                    tempUnits = "metric";
                    Snackbar.make(view, "Displaying temperature in Celsius", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
            }

                loadWeatherData(location);
            }
        });


        openWeatherMapInterface = WeatherAPI.getInstance().create(OpenWeatherMapInterface.class);

        loadWeatherData(location);

        return root;
    }

    private void loadWeatherData(String location) {

        String apiKey = WeatherAPI.apiKey;

        openWeatherMapInterface.getWeatherData(location, apiKey, tempUnits).enqueue(new Callback<WeatherAnalysis>() {
            @Override
            public void onResponse(Call<WeatherAnalysis> call, Response<WeatherAnalysis> response) {
                if (response.isSuccessful()) {
                    WeatherAnalysis weatherAnalysis = response.body();
                    updateUI(weatherAnalysis);
                    //showDebugToast();
                } else {
                    showErrorToast();
                }
            }

            @Override
            public void onFailure(Call<WeatherAnalysis> call, Throwable t) {
                showErrorToast();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void updateUI(WeatherAnalysis weatherAnalysis) {
        cityNameTextView.setText(location);
        if (tempUnits == "metric") {
            Double intTemp = Double.parseDouble(weatherAnalysis.getMain().getTemp());
            temperatureTextView.setText("Temperature: " + String.format("%.0f", intTemp) + " °C");
            Double intHumidity = Double.parseDouble(weatherAnalysis.getMain().getHumidity());
            humidityTextView.setText("Humidity: " + String.format("%.0f", intHumidity) + " %");
            Double intFeelsLike = Double.parseDouble(weatherAnalysis.getMain().getFeels_like());
            feelsLikeTextView.setText("Feels Like: " + String.format("%.0f", intFeelsLike) + " °C");
        }
        else{
            Double intTemp = Double.parseDouble(weatherAnalysis.getMain().getTemp());
            temperatureTextView.setText("Temperature: " + String.format("%.0f", intTemp) + " °F");
            Double intHumidity = Double.parseDouble(weatherAnalysis.getMain().getHumidity());
            humidityTextView.setText("Humidity: " + String.format("%.0f", intHumidity) + " %");
            Double intFeelsLike = Double.parseDouble(weatherAnalysis.getMain().getFeels_like());
            feelsLikeTextView.setText("Feels Like: " + String.format("%.0f", intFeelsLike) + " °F");
        }



    }

    private void showErrorToast() {
        Toast.makeText(getActivity(), "Location not valid!", Toast.LENGTH_SHORT).show();
    }

    private void showDebugToast() {
        Toast.makeText(getActivity(), "Showing weather for " + location, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}
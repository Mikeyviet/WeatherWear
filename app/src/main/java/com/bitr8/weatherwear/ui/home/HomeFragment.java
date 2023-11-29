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
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.ViewModelProvider;

import com.bitr8.weatherwear.OpenWeatherMapInterface;
import com.bitr8.weatherwear.R;
import com.bitr8.weatherwear.WeatherAPI;
import com.bitr8.weatherwear.WeatherAnalysis;
import com.bitr8.weatherwear.databinding.FragmentHomeBinding;
import com.bitr8.weatherwear.ui.gallery.GalleryFragment;
import com.google.android.material.snackbar.Snackbar;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private TextView cityNameTextView;
    private TextView temperatureTextView;
    private TextView humidityTextView;

    private TextView feelsLikeTextView;

    private TextView descriptionTextView;
    private ImageView weatherIconImageView;

    private String location = "Dallas";
    private EditText inputText;
    private OpenWeatherMapInterface openWeatherMapInterface;
    private FragmentHomeBinding binding;
    public static String tempUnits = "metric";
    public String stringDescription;
    public static Double intTemp;
    public static Double intHumidity;
    public Double intFeelsLike;

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
        descriptionTextView = root.findViewById(R.id.description_text_view);
        inputText = root.findViewById(R.id.inputText);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                location = String.valueOf(inputText.getText());
                loadWeatherData(location);
                Snackbar.make(view, "Showing weather data for " + WordUtils.capitalize(location) , Snackbar.LENGTH_LONG)
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
    public void updateUI(WeatherAnalysis weatherAnalysis) {
        cityNameTextView.setText(WordUtils.capitalize(location));
        if (Objects.equals(tempUnits, "metric")) {
            intTemp = Double.parseDouble(weatherAnalysis.getMain().getTemp());
            temperatureTextView.setText("Temperature: " + String.format("%.0f", intTemp) + " °C");
            intHumidity = Double.parseDouble(weatherAnalysis.getMain().getHumidity());
            humidityTextView.setText("Humidity: " + String.format("%.0f", intHumidity) + " %");
            intFeelsLike = Double.parseDouble(weatherAnalysis.getMain().getFeels_like());
            feelsLikeTextView.setText("Feels Like: " + String.format("%.0f", intFeelsLike) + " °C");
            stringDescription = WordUtils.capitalize(weatherAnalysis.getWeatherList().get(0).getDescription());
            descriptionTextView.setText(stringDescription);
        }
        else{
            intTemp = Double.parseDouble(weatherAnalysis.getMain().getTemp());
            temperatureTextView.setText("Temperature: " + String.format("%.0f", intTemp) + " °F");
            intHumidity = Double.parseDouble(weatherAnalysis.getMain().getHumidity());
            humidityTextView.setText("Humidity: " + String.format("%.0f", intHumidity) + " %");
            intFeelsLike = Double.parseDouble(weatherAnalysis.getMain().getFeels_like());
            feelsLikeTextView.setText("Feels Like: " + String.format("%.0f", intFeelsLike) + " °F");
            stringDescription = WordUtils.capitalize(weatherAnalysis.getWeatherList().get(0).getDescription());
            descriptionTextView.setText(stringDescription);
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
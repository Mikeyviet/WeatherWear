package com.bitr8.weatherwear.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bitr8.weatherwear.MainActivity;
import com.bitr8.weatherwear.OpenWeatherMapService;
import com.bitr8.weatherwear.R;
import com.bitr8.weatherwear.WeatherAPI;
import com.bitr8.weatherwear.WeatherAnalysis;
import com.bitr8.weatherwear.databinding.FragmentHomeBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private TextView cityNameTextView;
    private TextView temperatureTextView;
    private TextView weatherDescriptionTextView;
    private ImageView weatherIconImageView;

    private String location = "Dallas";

    private OpenWeatherMapService openWeatherMapService;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        cityNameTextView = root.findViewById(R.id.city_name_text_view);
        temperatureTextView = root.findViewById(R.id.temperature_text_view);
        weatherDescriptionTextView = root.findViewById(R.id.weather_description_text_view);
        weatherIconImageView = root.findViewById(R.id.weather_icon_image_view);


        openWeatherMapService = WeatherAPI.getInstance().create(OpenWeatherMapService.class);

        loadWeatherData(location);
        homeViewModel.getText().observe(getViewLifecycleOwner(), cityNameTextView::setText);
        homeViewModel.getText().observe(getViewLifecycleOwner(), temperatureTextView::setText);
        homeViewModel.getText().observe(getViewLifecycleOwner(), weatherDescriptionTextView::setText);
        return root;
    }

    private void loadWeatherData(String location) {

        String apiKey = WeatherAPI.apiKey;
        openWeatherMapService.getCurrentWeatherData(location, apiKey).enqueue(new Callback<WeatherAnalysis>() {
            @Override
            public void onResponse(Call<WeatherAnalysis> call, Response<WeatherAnalysis> response) {
                if (response.isSuccessful()) {
                    WeatherAnalysis weatherAnalysis = response.body();
                    updateUI(weatherAnalysis);
                    showDebugToast();
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

    private void updateUI(WeatherAnalysis weatherAnalysis) {
        cityNameTextView.setText(weatherAnalysis.getCityName());
        temperatureTextView.setText(weatherAnalysis.getTemperature() + "°C");
        weatherDescriptionTextView.setText(weatherAnalysis.getWeatherDescription());
    }

    private void showErrorToast() {
        Toast.makeText(getActivity(), "Failed to load weather data", Toast.LENGTH_SHORT).show();
    }

    private void showDebugToast() {
        Toast.makeText(getActivity(), "Showing weather for " + temperatureTextView, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
package com.example.weatherwear;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.weatherwear.databinding.ActivityMainBinding;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private TextView cityNameTextView;
    private TextView temperatureTextView;
    private TextView weatherDescriptionTextView;
    private ImageView weatherIconImageView;

    private String location = "Texas";

    private OpenWeatherMapService openWeatherMapService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Showing weather data for " + location, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        cityNameTextView = findViewById(R.id.city_name_text_view);
        temperatureTextView = findViewById(R.id.temperature_text_view);
        weatherDescriptionTextView = findViewById(R.id.weather_description_text_view);
        weatherIconImageView = findViewById(R.id.weather_icon_image_view);

        openWeatherMapService = WeatherAPI.getInstance().create(OpenWeatherMapService.class);

        loadWeatherData(location);

    }

    private void loadWeatherData(String location) {

        String apiKey = WeatherAPI.apiKey;
        openWeatherMapService.getCurrentWeatherData(location, apiKey).enqueue(new Callback<WeatherAnalysis>() {
            @Override
            public void onResponse(Call<WeatherAnalysis> call, Response<WeatherAnalysis> response) {
                if (response.isSuccessful()) {
                    WeatherAnalysis weatherAnalysis = response.body();
                    updateUI(weatherAnalysis);
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

    }

    private void showErrorToast() {
        Toast.makeText(MainActivity.this, "Failed to load weather data", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}

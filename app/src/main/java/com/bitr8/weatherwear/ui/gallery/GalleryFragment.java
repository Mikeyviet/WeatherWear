package com.bitr8.weatherwear.ui.gallery;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
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
import com.bitr8.weatherwear.ui.home.HomeFragment;
import com.google.android.material.snackbar.Snackbar;

import com.bitr8.weatherwear.databinding.FragmentGalleryBinding;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    private TextView clothingText;

    private ImageView clothingImage;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        clothingText = root.findViewById(R.id.text_clothing);
        clothingImage = root.findViewById(R.id.imageView2);
        Bundle bundle = this.getArguments();
        displayClothing();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void displayClothing() {
        Double temp = HomeFragment.intTemp;
        Double humidity = HomeFragment.intHumidity;
        String tempUnits = HomeFragment.tempUnits;
        if(tempUnits == "metric") {
            if (temp > 35) {
                clothingText.setText("It's hot outside! Wear light clothes.");
                Drawable myDrawable = getResources().getDrawable(R.drawable.blank_t_shirt_png_30253);
                clothingImage.setImageDrawable(myDrawable);
            }
            else if (temp > 20){
                clothingText.setText("It's cool outside. Enjoy the weather");
                Drawable myDrawable = getResources().getDrawable(R.drawable.blank_t_shirt_png_30253);
                clothingImage.setImageDrawable(myDrawable);
            }
            else if (temp > 5){
                clothingText.setText("It's cold outside! Wear warm clothes.");
                Drawable myDrawable = getResources().getDrawable(R.drawable.pngtreeblank_sweater_apparel_6650587);
                clothingImage.setImageDrawable(myDrawable);
            }
            else if (temp < 5){
                clothingText.setText("It's freezing! Wear warm clothes.");
                Drawable myDrawable = getResources().getDrawable(R.drawable.pngtreeblank_sweater_apparel_6650587);
                clothingImage.setImageDrawable(myDrawable);
            }
        }
        else if(tempUnits == "imperial"){
            if (temp > 95) {
                clothingText.setText("It's hot outside! Wear light clothes.");
                Drawable myDrawable = getResources().getDrawable(R.drawable.blank_t_shirt_png_30253);
                clothingImage.setImageDrawable(myDrawable);
            }
            else if (temp > 68){
                clothingText.setText("It's cool outside. Enjoy the weather");
                Drawable myDrawable = getResources().getDrawable(R.drawable.blank_t_shirt_png_30253);
                clothingImage.setImageDrawable(myDrawable);
            }
            else if (temp > 41){
                clothingText.setText("It's cold outside! Wear warm clothes.");
                Drawable myDrawable = getResources().getDrawable(R.drawable.pngtreeblank_sweater_apparel_6650587);
                clothingImage.setImageDrawable(myDrawable);
            }
            else if (temp < 41){
                clothingText.setText("It's freezing! Wear warm clothes.");
                Drawable myDrawable = getResources().getDrawable(R.drawable.pngtreeblank_sweater_apparel_6650587);
                clothingImage.setImageDrawable(myDrawable);
            }
        }
        else{
            clothingText.setText("Something went wrong");
        }
    }
}
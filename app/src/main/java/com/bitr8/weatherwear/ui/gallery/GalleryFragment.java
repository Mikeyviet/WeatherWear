package com.bitr8.weatherwear.ui.gallery;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
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
    private EditText clothingText;

    private ImageView clothingImage;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        clothingText = root.findViewById(R.id.text_clothing);
        clothingImage = root.findViewById(R.id.imageView2);

        binding.clothesEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Select clothes:");

// Set up the input
                final EditText input = new EditText(getContext());
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

// Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        String test = input.getText().toString();
                        if (test.equals("sweater") || test.equals("Sweater")){
                            clothingImage.setImageDrawable(getResources().getDrawable(R.drawable.pngtreeblank_sweater_apparel_6650587));
                        }
                        else if (test.equals("t-shirt") || test.equals("T-shirt")){
                            clothingImage.setImageDrawable(getResources().getDrawable(R.drawable.blank_t_shirt_png_30253));
                        }
                        else
                            Toast.makeText(getActivity(), "Clothes do not exist!", Toast.LENGTH_SHORT).show();

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });
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
                clothingText.setText("Its burning outside! T-shirt or long sleeves, shorts, and possibly an umbrella!");
                Drawable myDrawable = getResources().getDrawable(R.drawable.blank_t_shirt_png_30253);
                clothingImage.setImageDrawable(myDrawable);
            }
            else if (temp > 20){
                clothingText.setText("It's cool outside. Enjoy the weather");
                Drawable myDrawable = getResources().getDrawable(R.drawable.blank_t_shirt_png_30253);
                clothingImage.setImageDrawable(myDrawable);
            }
            else if (temp > 5){
                clothingText.setText("Its a little chilly! Wear longs sleeves shirt or jacket with long pants");
                Drawable myDrawable = getResources().getDrawable(R.drawable.pngtreeblank_sweater_apparel_6650587);
                clothingImage.setImageDrawable(myDrawable);
            }
            else if (temp < 5){
                clothingText.setText("Its freezing! Bundle up in a winter jacket and wear long, warm pants");
                Drawable myDrawable = getResources().getDrawable(R.drawable.pngtreeblank_sweater_apparel_6650587);
                clothingImage.setImageDrawable(myDrawable);
            }
        }
        else if(tempUnits == "imperial"){
            if (temp > 95) {
                clothingText.setText("Its burning outside! T-shirt or long sleeves, shorts, and possibly an umbrella!");
                Drawable myDrawable = getResources().getDrawable(R.drawable.blank_t_shirt_png_30253);
                clothingImage.setImageDrawable(myDrawable);
            }
            else if (temp > 68){
                clothingText.setText("It's cool outside. Enjoy the weather");
                Drawable myDrawable = getResources().getDrawable(R.drawable.blank_t_shirt_png_30253);
                clothingImage.setImageDrawable(myDrawable);
            }
            else if (temp > 41){
                clothingText.setText("Its a little chilly! Wear longs sleeves shirt or jacket with long pants");
                Drawable myDrawable = getResources().getDrawable(R.drawable.pngtreeblank_sweater_apparel_6650587);
                clothingImage.setImageDrawable(myDrawable);
            }
            else if (temp < 41){
                clothingText.setText("Its freezing! Bundle up in a winter jacket and wear long, warm pants");
                Drawable myDrawable = getResources().getDrawable(R.drawable.pngtreeblank_sweater_apparel_6650587);
                clothingImage.setImageDrawable(myDrawable);
            }
        }
        else{
            clothingText.setText("Something went wrong");
        }
    }
}
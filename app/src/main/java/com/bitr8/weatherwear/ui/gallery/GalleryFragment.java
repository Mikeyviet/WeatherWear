package com.bitr8.weatherwear.ui.gallery;

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
import com.bitr8.weatherwear.ui.home.HomeFragment;
import com.google.android.material.snackbar.Snackbar;

import com.bitr8.weatherwear.databinding.FragmentGalleryBinding;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    private TextView clothingText;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        clothingText = root.findViewById(R.id.text_clothing);
        Bundle bundle = this.getArguments();
        displayClothing();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void displayClothing(){

    }
}
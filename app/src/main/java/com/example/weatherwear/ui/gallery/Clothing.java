package com.example.weatherwear.ui.gallery;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Clothing extends AppCompatActivity {

    public String clothingType;
    public String clothingImage;

    // This launcher will handle the result of our image picker intent
    private final ActivityResultLauncher<Intent> mGetContent = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == AppCompatActivity.RESULT_OK) {
                    if (result.getData() != null) {
                        Uri imageUri = result.getData().getData();
                        try {
                            // Let's get that image as a stream
                            InputStream imageStream = getContentResolver().openInputStream(imageUri);
                            // Time to decode that stream into a bitmap
                            Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                            // Now, let's resize the image if it's too big
                            Bitmap resizedImage = resizeImage(selectedImage, 1024, 1024); // max width/height
                            // Convert the bitmap to a byte array for upload
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            resizedImage.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                            byte[] imageData = baos.toByteArray();
                            // TODO: Upload imageData to Google Drive
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
    );

    public void pickImage() {
        // Intent to pick an image
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        mGetContent.launch(intent);
    }

    private Bitmap resizeImage(Bitmap image, int maxWidth, int maxHeight) {
        int width = image.getWidth();
        int height = image.getHeight();

        if (width <= maxWidth && height <= maxHeight) {
            // No need to resize
            return image;
        }

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxWidth;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxHeight;
            width = (int) (height * bitmapRatio);
        }

        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    public void addClothing() {
        // TODO: Implement add clothing logic
    }

    public void editClothing() {
        // TODO: Implement edit clothing logic
    }

}

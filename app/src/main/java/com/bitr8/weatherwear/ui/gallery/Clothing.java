package com.bitr8.weatherwear.ui.gallery;
import com.bitr8.weatherwear.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Clothing extends AppCompatActivity {

    private String clothingType; // Switched to private to encapsulate the property
    private Bitmap clothingImage; // Changed from String to Bitmap to reflect the new code

    private final ActivityResultLauncher<Intent> mGetContent = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        Uri imageUri = data.getData();
                        try {
                            InputStream inputStream = getContentResolver().openInputStream(imageUri);
                            Bitmap selectedImage = BitmapFactory.decodeStream(inputStream);
                            Bitmap resizedImage = resizeImage(selectedImage, 500, 500); // Changed to 500 to match new code
                            byte[] imageData = compressImage(resizedImage);
                            uploadImageData(imageData);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothing);

        // Example usage: pick an image
        pickImage();
    }

    private void pickImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        mGetContent.launch(intent);
    }

    private Bitmap resizeImage(Bitmap image, int maxWidth, int maxHeight) {
        int width = image.getWidth();
        int height = image.getHeight();

        if (width <= maxWidth && height <= maxHeight) {
            return image;
        }

        float aspectRatio = (float) width / height;
        if (aspectRatio > 1) {
            width = maxWidth;
            height = (int) (width / aspectRatio);
        } else {
            height = maxHeight;
            width = (int) (height * aspectRatio);
        }

        return Bitmap.createScaledBitmap(image, width, height, false);
    }

    private byte[] compressImage(Bitmap image) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        return stream.toByteArray();
    }

    private void uploadImageData(byte[] imageData) {
        // TODO: Upload imageData to a desired destination (e.g., Google Drive)

        // Need to replace this code with the new code for Google Drive Rest API
        /*
        DriveFolder folder = Drive.DriveApi.getRootFolder(mGoogleApiClient);
        DriveApi.DriveContentsResult result = Drive.DriveApi.newDriveContents(mGoogleApiClient).await();
        if (!result.getStatus().isSuccess()) {
            // Handle error
            return;
        }
        DriveContents driveContents = result.getDriveContents();
        OutputStream outputStream = driveContents.getOutputStream();
        try {
            outputStream.write(imageData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MetadataChangeSet changeSet = new MetadataChangeSet.Builder()
                .setTitle("Clothing Image")
                .setMimeType("image/jpeg")
                .build();
        folder.createFile(mGoogleApiClient, changeSet, driveContents)
                .setResultCallback(new ResultCallback<DriveFolder.DriveFileResult>() {
                    @Override
                    public void onResult(@NonNull DriveFolder.DriveFileResult driveFileResult) {
                        if (driveFileResult.getStatus().isSuccess()) {
                            // File uploaded successfully
                            DriveFile driveFile = driveFileResult.getDriveFile();
                            // Handle the uploaded file as needed
                        } else {
                            // Handle error
                        }
                    }
                });
        */
    }

    // Additional methods from old code (if needed)
    public void addClothing() {
        // TODO: Implement add clothing logic
    }

    public void editClothing() {

    }

}
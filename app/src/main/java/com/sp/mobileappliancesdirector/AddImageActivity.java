package com.sp.mobileappliancesdirector;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class AddImageActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView imagePreview;
    private FirebaseFirestore db;
    private FirebaseStorage storage;
    private Uri imageUri;
    private String scanResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_image);

        // Initialize Firebase components
        db = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();

        scanResult = getIntent().getStringExtra("scanResult");

        imagePreview = findViewById(R.id.imagePreview);
        MaterialButton addImageButton = findViewById(R.id.addImageButton);
        MaterialButton uploadImageButton = findViewById(R.id.uploadImageButton);

        addImageButton.setOnClickListener(view -> openGallery());
        uploadImageButton.setOnClickListener(view -> uploadImage());
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    private void uploadImage() {
        if (imageUri != null) {
            StorageReference storageRef = storage.getReference().child("images/" + System.currentTimeMillis() + ".jpg");

            storageRef.putFile(imageUri)
                    .addOnSuccessListener(taskSnapshot -> {
                        // Image uploaded successfully
                        Toast.makeText(AddImageActivity.this, "Image uploaded successfully", Toast.LENGTH_SHORT).show();

                        // Retrieve the download URL of the uploaded image
                        storageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                            // Pass the image URL back to the calling activity
                            Intent resultIntent = new Intent();
                            resultIntent.putExtra("imageUrl", uri.toString());
                            resultIntent.putExtra("scanResult", scanResult);
                            setResult(Activity.RESULT_OK, resultIntent);
                            finish();
                        }).addOnFailureListener(e -> {
                            // Handle any errors
                            Toast.makeText(AddImageActivity.this, "Failed to get image URL", Toast.LENGTH_SHORT).show();
                        });
                    })
                    .addOnFailureListener(e -> {
                        // Handle unsuccessful uploads
                        Toast.makeText(AddImageActivity.this, "Failed to upload image", Toast.LENGTH_SHORT).show();
                    });
        } else {
            Toast.makeText(AddImageActivity.this, "No image selected", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            // Get the Uri of the selected image
            imageUri = data.getData();
            if (imageUri != null) {
                // Set the image preview
                imagePreview.setImageURI(imageUri);
            }
        }
    }
}

package com.sp.mobileappliancesdirector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Retrieve data from the intent or other sources to display details
        Intent intent = getIntent();
        String applianceType = intent.getStringExtra("APPLIANCE_TYPE");
        String applianceModel = intent.getStringExtra("APPLIANCE_MODEL");
        String applianceImageUrl = intent.getStringExtra("APPLIANCE_IMAGE_URL");
        Log.d("DetailsActivity", "Appliance Type: " + applianceType);
        Log.d("DetailsActivity", "Appliance Model: " + applianceModel);
        Log.d("DetailsActivity", "Appliance Image URL: " + applianceImageUrl);

        setTitle(applianceType + " Details");
        ImageView imageView = findViewById(R.id.detailsImageView);
        Picasso.with(this).load(applianceImageUrl).into(imageView);
    }
}
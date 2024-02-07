package com.sp.mobileappliancesdirector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Retrieve data from the intent to display details
        Intent intent = getIntent();
        String applianceType = intent.getStringExtra("APPLIANCE_TYPE");
        String applianceModel = intent.getStringExtra("APPLIANCE_MODEL");
        String applianceImageUrl = intent.getStringExtra("APPLIANCES_IMAGE_URL");

        Log.d("DetailsActivity", "Appliance Type: " + applianceType);
        Log.d("DetailsActivity", "Appliance Model: " + applianceModel);
        Log.d("DetailsActivity", "Appliance Image URL: " + applianceImageUrl);

        setTitle(applianceType + " Details");

        ImageView imageView = findViewById(R.id.detailsImageView);
        TextView applianceTypeTextView = findViewById(R.id.detailsApplianceType);
        TextView applianceModelTextView = findViewById(R.id.detailsApplianceModel);
        Button buttonOn = findViewById(R.id.buttonOn);
        Button buttonOff = findViewById(R.id.buttonOff);
        Spinner dropdownTimer = findViewById(R.id.dropdownTimer);
        Button buttonSetTimer = findViewById(R.id.buttonSetTimer);

        Picasso.with(this).load(applianceImageUrl).into(imageView);

        // Set the appliance type and appliance model in the TextViews
        applianceTypeTextView.setText(applianceType);
        applianceModelTextView.setText(applianceModel);

        buttonOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailsActivity.this, "Device is switched on", Toast.LENGTH_SHORT).show();
            }
        });

        buttonOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailsActivity.this, "Device is switched off", Toast.LENGTH_SHORT).show();
            }
        });

        // Set up the spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.timer_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdownTimer.setAdapter(adapter);

        buttonSetTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedTime = dropdownTimer.getSelectedItem().toString();
                Toast.makeText(DetailsActivity.this, "Device is turned on for " + selectedTime, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
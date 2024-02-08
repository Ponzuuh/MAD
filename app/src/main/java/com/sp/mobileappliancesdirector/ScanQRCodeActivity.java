package com.sp.mobileappliancesdirector;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.HashMap;
import java.util.Map;

public class ScanQRCodeActivity extends AppCompatActivity {

    private static final int REQUEST_ADD_IMAGE = 101;

    Button btn_scan;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String scanResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qrcode);
        btn_scan = findViewById(R.id.btnScan);
        btn_scan.setOnClickListener(v -> scanCode());
    }

    private void scanCode()
    {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Volume up to flash on");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        QRLauncher.launch(options);
    }

    ActivityResultLauncher<ScanOptions> QRLauncher = registerForActivityResult(new ScanContract(), result -> {
        if (result.getContents() != null) {
            scanResult = result.getContents(); // Assign value to scanResult
            showResultDialog(scanResult); // Move dialog display to a separate method
        }
    });

    private void showResultDialog(String scanResult) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ScanQRCodeActivity.this);
        builder.setTitle("Result");
        builder.setMessage(scanResult);
        builder.setPositiveButton("Register!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Start AddImageActivity for result
                Intent intent = new Intent(ScanQRCodeActivity.this, AddImageActivity.class);
                intent.putExtra("scanResult", scanResult);
                startActivityForResult(intent, REQUEST_ADD_IMAGE);
                dialog.dismiss();
            }
        }).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_ADD_IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            // Retrieve the image URL from the result
            String imageUrl = data.getStringExtra("imageUrl");

            // Now, you can save the data to Firestore including the image URL
            saveDataToFirestore(scanResult, imageUrl);
        }
    }

    private void saveDataToFirestore(String scanResult, String imageUrl) {
        // Create a new document within the "TEST_APPLIANCES" collection
        Map<String, Object> applianceData = new HashMap<>();
        applianceData.put("MODEL", scanResult);
        applianceData.put("NAME", scanResult);
        applianceData.put("IMAGE_URL", imageUrl); // Include image URL in Firestore data

        db.collection("TEST APPLIANCES")
                .add(applianceData)
                .addOnSuccessListener(documentReference -> {
                    // Success handling, e.g., show a confirmation message to the user
                    Toast.makeText(ScanQRCodeActivity.this, "Appliance Registered Successfully", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(ScanQRCodeActivity.this, "Error registering appliance", Toast.LENGTH_SHORT).show();
                });
    }
}
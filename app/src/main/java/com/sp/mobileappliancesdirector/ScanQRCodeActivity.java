package com.sp.mobileappliancesdirector;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.HashMap;
import java.util.Map;

public class ScanQRCodeActivity extends AppCompatActivity {


    Button btn_scan;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
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
        QRLaucher.launch(options);
    }

    ActivityResultLauncher<ScanOptions> QRLaucher = registerForActivityResult(new ScanContract(), result-> {

        if (result.getContents() !=null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(ScanQRCodeActivity.this);
            builder.setTitle("Result");
            builder.setMessage(result.getContents());
            builder.setPositiveButton("Register!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    //Save data to Firestore
                    saveDataToFirestore(result.getContents());
                    dialog.dismiss();
                }
            }).show();
        }

    });
    private void saveDataToFirestore(String scanResult) {
        // Create a new document within the "TEST_APPLIANCES" collection
        Map<String, Object> applianceData = new HashMap<>();

        applianceData.put("MODEL", scanResult);
        applianceData.put("NAME", scanResult);

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

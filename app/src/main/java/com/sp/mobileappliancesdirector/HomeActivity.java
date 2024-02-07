package com.sp.mobileappliancesdirector;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class HomeActivity extends AppCompatActivity {


    private Button generateQRBtn, scanQRBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        generateQRBtn = findViewById(R.id.idBtnGenerateQR);
        scanQRBtn = findViewById(R.id.idBtnScanQR);
        generateQRBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        scanQRBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}
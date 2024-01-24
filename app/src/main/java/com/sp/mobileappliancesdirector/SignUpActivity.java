package com.sp.mobileappliancesdirector;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.button.MaterialButton;

public class SignUpActivity extends AppCompatActivity {

    private EditText userName;
    private MaterialButton Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        userName = findViewById(R.id.username);
        Register = findViewById(R.id.signupbutton);

        //EditText username = (EditText) findViewById(R.id.username);
        //MaterialButton Register = (MaterialButton) findViewById(R.id.signupbutton);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName1 = userName.getText().toString();
                Toast.makeText(SignUpActivity.this, "Username is " + userName1, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

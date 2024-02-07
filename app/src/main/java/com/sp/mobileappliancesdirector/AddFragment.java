package com.sp.mobileappliancesdirector;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class AddFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);

        // Handle button click
        Button generateQRButton = view.findViewById(R.id.idBtnGenerateQR);
        generateQRButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to GenerateQRCodeActivity
                Intent intent = new Intent(requireContext(), GenerateQRCodeActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
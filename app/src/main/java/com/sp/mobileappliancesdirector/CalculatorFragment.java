package com.sp.mobileappliancesdirector;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class CalculatorFragment extends Fragment {

    private EditText powerConsumptionEditText, numberOfHoursEditText;
    private TextView resultTextView, costTextView;
    private Button calculateButton;
    private TextInputLayout powerConsumptionInputLayout, numberOfHoursInputLayout;

    public CalculatorFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);

        powerConsumptionEditText = view.findViewById(R.id.powerConsumptionEditText);
        numberOfHoursEditText = view.findViewById(R.id.numberOfHoursEditText);
        resultTextView = view.findViewById(R.id.resultTextView);
        costTextView = view.findViewById(R.id.costTextView);
        calculateButton = view.findViewById(R.id.calculateButton);
        powerConsumptionInputLayout = view.findViewById(R.id.powerConsumptionInputLayout);
        numberOfHoursInputLayout = view.findViewById(R.id.numberOfHoursInputLayout);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResult();
            }
        });
        return view;
    }
    private void calculateResult() {
        String powerConsumptionStr = powerConsumptionEditText.getText().toString();
        String numberOfHoursStr = numberOfHoursEditText.getText().toString();

        if (!powerConsumptionStr.isEmpty() && !numberOfHoursStr.isEmpty()) {
            double powerConsumption = Double.parseDouble(powerConsumptionStr);
            double numberOfHours = Double.parseDouble(numberOfHoursStr);

            double totalPowerConsumption = powerConsumption * numberOfHours;
            double cost = totalPowerConsumption * 0.3258;

            resultTextView.setText(String.format("%.2f kW", totalPowerConsumption));
            costTextView.setText(String.format("$ %.2f", cost));
        } else {
            // Show error if any of the fields is empty
            if (powerConsumptionStr.isEmpty()) {
                powerConsumptionInputLayout.setError("Please enter power consumption");
            } else {
                powerConsumptionInputLayout.setError(null);
            }

            if (numberOfHoursStr.isEmpty()) {
                numberOfHoursInputLayout.setError("Please enter number of hours");
            } else {
                numberOfHoursInputLayout.setError(null);
            }
        }
    }
}
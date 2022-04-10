package hr.unipu.android.zadaca.kalkulator;

import static hr.unipu.android.zadaca.kalkulator.CalculatorActivity.CALCULATOR_ACTIVITY_REQUEST_CODE;
import static hr.unipu.android.zadaca.kalkulator.CalculatorActivity.CALCULATOR_ACTIVITY_RESULT_KEY;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import hr.unipu.android.zadaca.R;

public class CalculatorStartActivity extends AppCompatActivity {

    // UI Widgets
    private Button startCalculator;
    private TextView calculatorAnswer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_calculator);

        startCalculator = findViewById(R.id.button_start_calculator);
        calculatorAnswer = findViewById(R.id.calculator_start_result);

        startCalculator.setOnClickListener(view -> startCalculator());
    }

    private void startCalculator() {
        startActivityForResult(new Intent(this, CalculatorActivity.class), CALCULATOR_ACTIVITY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode != CALCULATOR_ACTIVITY_REQUEST_CODE) return;

        if (resultCode == Activity.RESULT_OK) {
            final float result = data.getFloatExtra(CALCULATOR_ACTIVITY_RESULT_KEY, -1.f);
            calculatorAnswer.setText("Rezultat: " + result);
        }
    }
}

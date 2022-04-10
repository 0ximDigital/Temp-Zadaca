package hr.unipu.android.zadaca.kalkulator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.LinkedList;

import hr.unipu.android.zadaca.R;

public class CalculatorActivity extends AppCompatActivity {

    public static final int CALCULATOR_ACTIVITY_REQUEST_CODE = 100;
    public static final String CALCULATOR_ACTIVITY_RESULT_KEY = "CALCULATION_RESULT";

    // UI Widget
    private TextView display;

    private LinkedList<String> input = new LinkedList<String>();

    private boolean hasOperation = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        display = findViewById(R.id.current_equation);

        findViewById(R.id.button_0).setOnClickListener(view -> inputDigit("0"));
        findViewById(R.id.button_1).setOnClickListener(view -> inputDigit("1"));
        findViewById(R.id.button_2).setOnClickListener(view -> inputDigit("2"));
        findViewById(R.id.button_3).setOnClickListener(view -> inputDigit("3"));
        findViewById(R.id.button_4).setOnClickListener(view -> inputDigit("4"));
        findViewById(R.id.button_5).setOnClickListener(view -> inputDigit("5"));
        findViewById(R.id.button_6).setOnClickListener(view -> inputDigit("6"));
        findViewById(R.id.button_7).setOnClickListener(view -> inputDigit("7"));
        findViewById(R.id.button_8).setOnClickListener(view -> inputDigit("8"));
        findViewById(R.id.button_9).setOnClickListener(view -> inputDigit("9"));

        findViewById(R.id.button_multiply).setOnClickListener(view -> inputOperation("*"));
        findViewById(R.id.button_divide).setOnClickListener(view -> inputOperation("/"));
        findViewById(R.id.button_plus).setOnClickListener(view -> inputOperation("+"));
        findViewById(R.id.button_minus).setOnClickListener(view -> inputOperation("-"));

        findViewById(R.id.button_delete).setOnClickListener(view -> deleteLastInput());
        findViewById(R.id.button_equals).setOnClickListener(view -> calculateEquation());
    }

    private void inputDigit(final String digit) {
        input.add(digit);
        refreshDisplay();
    }

    private void inputOperation(final String operation) {
        // We ca only have a single operation
        if (hasOperation) return;

        // Operation must come after first number
        if (input.isEmpty()) return;

        hasOperation = true;
        input.add(" " + operation + " ");
        refreshDisplay();
    }

    private void deleteLastInput() {
        final String inputToDelete = input.peekLast();
        if (inputToDelete == null) return;

        if (inputToDelete.contains(" ")) {
            hasOperation = false;
        }

        input.removeLast();
        refreshDisplay();
    }

    private void calculateEquation() {
        if (!hasOperation) return;

        final String equation = getInputEquation();
        final String[] parts = equation.split(" ");

        final int firstOperand = Integer.parseInt(parts[0]);
        final String operation = parts[1];
        final int secondOperand = Integer.parseInt(parts[2]);

        final float result = calculateResult(firstOperand, secondOperand, operation);
        reportResultAndFinish(result);
    }

    private void refreshDisplay() {
        display.setText(getInputEquation());
    }

    private String getInputEquation() {
        String output = "";
        for (String character : input) {
            output = output + character;
        }
        return output;
    }

    private float calculateResult(final float firstOperand, final float secondOperand, final String operation) {
        switch (operation) {
            case "+":
                return firstOperand + secondOperand;
            case "-":
                return firstOperand - secondOperand;
            case "/":
                return firstOperand / secondOperand;
            case "*":
                return firstOperand * secondOperand;
            default:
                return -1;
        }
    }

    private void reportResultAndFinish(final float result) {
        final Intent resultIntent = new Intent();
        resultIntent.putExtra(CALCULATOR_ACTIVITY_RESULT_KEY, result);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}

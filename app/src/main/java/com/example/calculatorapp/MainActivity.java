package com.example.calculatorapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;
    private String currentInput = "";
    private String operator = "";
    private double firstNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.resultTextView);

        // Number buttons
        findViewById(R.id.button0).setOnClickListener(view -> appendToInput("0"));
        findViewById(R.id.button1).setOnClickListener(view -> appendToInput("1"));
        findViewById(R.id.button2).setOnClickListener(view -> appendToInput("2"));
        findViewById(R.id.button3).setOnClickListener(view -> appendToInput("3"));
        findViewById(R.id.button4).setOnClickListener(view -> appendToInput("4"));
        findViewById(R.id.button5).setOnClickListener(view -> appendToInput("5"));
        findViewById(R.id.button6).setOnClickListener(view -> appendToInput("6"));
        findViewById(R.id.button7).setOnClickListener(view -> appendToInput("7"));
        findViewById(R.id.button8).setOnClickListener(view -> appendToInput("8"));
        findViewById(R.id.button9).setOnClickListener(view -> appendToInput("9"));

        // Operator buttons
        findViewById(R.id.buttonAdd).setOnClickListener(view -> setOperator("+"));
        findViewById(R.id.buttonSubtract).setOnClickListener(view -> setOperator("-"));
        findViewById(R.id.buttonMultiply).setOnClickListener(view -> setOperator("*"));
        findViewById(R.id.buttonDivide).setOnClickListener(view -> setOperator("/"));

        // Clear button
        findViewById(R.id.buttonClear).setOnClickListener(view -> clearInput());

        // Equals button
        findViewById(R.id.buttonEquals).setOnClickListener(view -> performCalculation());
    }

    private void appendToInput(String number) {
        currentInput += number;
        resultTextView.setText(currentInput);
    }

    private void setOperator(String operator) {
        if (!currentInput.isEmpty()) {
            firstNumber = Double.parseDouble(currentInput);
            this.operator = operator;
            currentInput = "";
        }
    }

    private void clearInput() {
        currentInput = "";
        operator = "";
        firstNumber = 0;
        resultTextView.setText("0");
    }

    private void performCalculation() {
        if (!currentInput.isEmpty() && operator != "") {
            double secondNumber = Double.parseDouble(currentInput);
            double result = 0;
            switch (operator) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "*":
                    result = firstNumber * secondNumber;
                    break;
                case "/":
                    if (secondNumber != 0) {
                        result = firstNumber / secondNumber;
                    } else {
                        resultTextView.setText("Error");
                        return;
                    }
                    break;
            }
            resultTextView.setText(String.valueOf(result));
            currentInput = String.valueOf(result); // Store result for further calculation if needed
            operator = "";
        }
    }
}

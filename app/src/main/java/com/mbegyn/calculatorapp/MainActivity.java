package com.mbegyn.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView info, resultInput;
    private Button addBn, subtractBn, multiplyBn, divideBn;

    private Button nbZero, nbOne, nbTwo, nbThree, nbFour, nbFive, nbSix, nbSeven, nbHeight, nbNine;

    private Button equalBn, clearBn;

    private int number1;
    private int number2;
    private int result;

    private char ACTION;
    private final char ADDITION = '+';
    private final char SUBTRACTION = '-';
    private final char MULTIPLICATION = '*';
    private final char DIVISION = '/';
    private final char EQUAL = '0';

    boolean isOperation = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpButtons();

        nbZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOperation || resultInput.getText() == "0") {
                    resultInput.setText(null);
                }
                resultInput.setText(resultInput.getText().toString() + "0");
                isOperation = false;
            }
        });

        nbOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOperation || resultInput.getText().equals("0")) {
                    resultInput.setText(null);
                }
                resultInput.setText(resultInput.getText().toString() + "1");
                isOperation = false;
            }
        });

        nbTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOperation || resultInput.getText().equals("0")) {
                    resultInput.setText(null);
                }
                resultInput.setText(resultInput.getText().toString() + "2");
                isOperation = false;
            }
        });

        nbThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOperation || resultInput.getText().equals("0")) {
                    resultInput.setText(null);
                }

                resultInput.setText(resultInput.getText().toString() + "3");
                isOperation = false;
            }
        });

        nbFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOperation || resultInput.getText().equals("0")) {
                    resultInput.setText(null);
                }
                resultInput.setText(resultInput.getText().toString() + "4");
                isOperation = false;
            }
        });

        nbFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOperation || resultInput.getText().equals("0")) {
                    resultInput.setText(null);
                }
                resultInput.setText(resultInput.getText().toString() + "5");
                isOperation = false;
            }
        });

        nbSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOperation || resultInput.getText().equals("0")) {
                    resultInput.setText(null);
                }
                resultInput.setText(resultInput.getText().toString() + "6");
                isOperation = false;
            }
        });

        nbSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOperation || resultInput.getText().equals("0")) {
                    resultInput.setText(null);
                }
                resultInput.setText(resultInput.getText().toString() + "7");
                isOperation = false;
            }
        });

        nbHeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOperation || resultInput.getText().equals("0")) {
                    resultInput.setText(null);
                }
                resultInput.setText(resultInput.getText().toString() + "8");
                isOperation = false;

            }
        });

        nbNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOperation || resultInput.getText().equals("0")) {
                    resultInput.setText(null);
                }
                resultInput.setText(resultInput.getText().toString() + "9");
                isOperation = false;
            }
        });

        addBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                compute();
                ACTION = ADDITION;
                info.setText(String.valueOf(number1) + "+");
                isOperation = true;
            }
        });

        subtractBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                compute();
                ACTION = SUBTRACTION;
                info.setText(String.valueOf(number1) + "-");
                isOperation = true;
            }
        });

        multiplyBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                compute();
                ACTION = MULTIPLICATION;
                info.setText(String.valueOf(number1) + "*");
                isOperation = true;
            }
        });

        divideBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                compute();
                ACTION = DIVISION;
                info.setText(String.valueOf(number1) + "/");

                isOperation = true;
            }
        });

        equalBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                compute();
                ACTION = EQUAL;
                resultInput.setText(String.valueOf(result));
                info.setText(info.getText().toString() + "=");
            }
        });

        clearBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number1 = 0;
                number2 = 0;
                result = 0;
                info.setText(null);
                resultInput.setText("0");
            }
        });

    }

    private void compute() {
        if(number1 != 0) {
            number2 = Integer.parseInt(resultInput.getText().toString());

            info.setText(info.getText().toString() + number2);


            if(result != 0) { // if we already have a result
                number1 = Integer.parseInt(resultInput.getText().toString());
                result = calculate(result, number2);
            }
            else {
                result = calculate(number1, number2);
            }
        }
        else {
            number1 = Integer.parseInt(resultInput.getText().toString());
        }
    }

    private int calculate(int val1, int val2) {

        int result = 0;
        switch(ACTION) {
            case ADDITION:
                result = val1 + val2;
                break;
            case SUBTRACTION:
                result = val1 - val2;
                break;
            case MULTIPLICATION:
                result = val1 * val2;
                break;
            case DIVISION:
                result = val1 / val2;
                break;
            case EQUAL:
                break;
        }

        return result;
    }

    private void setUpButtons() {
        info = (TextView) findViewById(R.id.inputTXView);
        resultInput = (TextView) findViewById(R.id.resultTXView);

        addBn = (Button) findViewById(R.id.opAddBn);
        subtractBn = (Button) findViewById(R.id.opSubtractBn);
        multiplyBn = (Button) findViewById(R.id.opMultiplyBn);
        divideBn = (Button) findViewById(R.id.opDivideBn);

        equalBn = (Button) findViewById(R.id.opEqualBn);
        clearBn = (Button) findViewById(R.id.opClearBn);

        nbZero = (Button) findViewById(R.id.nbZeroBn);
        nbOne = (Button) findViewById(R.id.nbOneBn);
        nbTwo = (Button) findViewById(R.id.nbTwoBn);
        nbThree = (Button) findViewById(R.id.nbThreeBn);
        nbFour = (Button) findViewById(R.id.nbFourBn);
        nbFive = (Button) findViewById(R.id.nbFiveBn);
        nbSix = (Button) findViewById(R.id.nbSixBn);
        nbSeven = (Button) findViewById(R.id.nbSevenBn);
        nbHeight = (Button) findViewById(R.id.nbHeightBn);
        nbNine = (Button) findViewById(R.id.nbNineBn);
    }

}
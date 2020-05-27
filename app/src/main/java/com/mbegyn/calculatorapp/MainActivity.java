package com.mbegyn.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView info, resultInput;

    private Button addBn, subtractBn, multiplyBn, divideBn;
    private Button nbZero, nbOne, nbTwo, nbThree, nbFour, nbFive, nbSix, nbSeven, nbHeight, nbNine;
    private Button equalBn, clearBn;

    private String operation;
    private int number1;
    private int number2;
    private int result;

    private String PARAMS_URL;
    public final static String BASE_URI = "http://192.168.1.154:8080/calculatorServiceWAR/operations/";
    boolean isOperation = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpButtons();

        /******************** NUMBERS ********************/

        nbZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickOnNumber("0");
            }
        });

        nbOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickOnNumber("1");
            }
        });

        nbTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickOnNumber("2");
            }
        });

        nbThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickOnNumber( "3");
            }
        });

        nbFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickOnNumber( "4");
            }
        });

        nbFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickOnNumber( "5");
            }
        });

        nbSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickOnNumber( "6");
            }
        });

        nbSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickOnNumber("7");
            }
        });

        nbHeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickOnNumber( "8");
            }
        });

        nbNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickOnNumber( "9");
            }
        });


        /******************** OPERATORS ********************/

        addBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickOnOperator(OperationsEnum.add, OperationsEnum.add.symbol);
            }
        });

        subtractBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickOnOperator(OperationsEnum.subtract, OperationsEnum.subtract.symbol);
            }
        });

        multiplyBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickOnOperator(OperationsEnum.multiply, OperationsEnum.multiply.symbol);
            }
        });

        divideBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickOnOperator(OperationsEnum.divide, OperationsEnum.divide.symbol);
            }
        });



        /******************** EQUAL / CLEAR ********************/

        equalBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PARAMS_URL = BASE_URI + operation + "/" + number1 + "/" + number2 + "/";
                sendAPIRequest(PARAMS_URL);
            }
        });

        clearBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /** RESET ALL **/
                result = 0;
                number1 = 0;
                number2 = 0;
                operation = "";
                info.setText(null);
                resultInput.setText("0");
            }
        });

    }


    private void clickOnNumber(String number) {
        /** to clear the zero in the result **/
        if (isOperation || resultInput.getText().equals("0")) {
            resultInput.setText(null);
        }
        /** always display the number clicked in the result input **/
        resultInput.setText(resultInput.getText().toString() + number);

        /** set the boolean operation at false **/
        isOperation = false;

        /** set the numbers (n1, n2 and result) **/
        setNumbers();
    }


    private void clickOnOperator(OperationsEnum operator, String symbol) {

        /** set FIRST the numbers (n1, n2 and result)  **/
        /** In case the user wants to calculate with the result number  **/
        setNumbers();

        /** display the number and the operator clicked in the info input **/
        setText(symbol);

        /** set the operation type clicked for the final request **/
        operation = operator.toString();

        /** set the boolean operation at true **/
        isOperation = true;
    }


    private void setText(String symbol) {
        if (number1 != 0) {
            info.setText(info.getText().toString() + number2);
        }
        info.setText(String.valueOf(number1) + symbol);
    }

    private void setNumbers() {
        if(number1 != 0) {
            if(result != 0) {
                number1 = result;
            }
            number2 = Integer.parseInt(resultInput.getText().toString());
        }
        else {
            number1 = Integer.parseInt(resultInput.getText().toString());
        }
    }



    private void sendAPIRequest(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest objectRequest = new JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.e("Rest response", response.toString());

                    try {
                        result = response.getInt("result");
                        resultInput.setText(String.valueOf(result));
                        String text = number1 + OperationsEnum.valueOf(operation).symbol + number2 + "=";
                        info.setText(text);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("Rest response", error.toString());
                }
            }
        );
        requestQueue.add(objectRequest);
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
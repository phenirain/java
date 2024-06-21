package com.example.calculatorapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button One, Two, Three, Four, Five, Six, Seven, Eight, Nine, Zero;

    private Button Minus, Plus, Division, Multiply, Result, Sqrt, Sqr, Procent, Clear;

    private TextView EndResult;

    private char Action;

    private double ResultValue = Double.NaN;

    private double Value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        setupView();

        View.OnClickListener numberClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                EndResult.setText(EndResult.getText().toString() + button.getText().toString());
            }
        };
        One.setOnClickListener(numberClickListener);
        Two.setOnClickListener(numberClickListener);
        Three.setOnClickListener(numberClickListener);
        Four.setOnClickListener(numberClickListener);
        Five.setOnClickListener(numberClickListener);
        Six.setOnClickListener(numberClickListener);
        Seven.setOnClickListener(numberClickListener);
        Eight.setOnClickListener(numberClickListener);
        Nine.setOnClickListener(numberClickListener);
        Zero.setOnClickListener(numberClickListener);

        View.OnClickListener actionClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Button button = (Button) view;
                if (EndResult.getText().length() != 0 && Character.isDigit(EndResult.getText().toString().charAt(EndResult.getText().toString().length()-1))
                        || EndResult.getText().length() == 0){
                    calculate();
                    Action = button.getText().charAt(0);
                    EndResult.setText(String.valueOf(ResultValue) + Action);
                }
            }
        };
        Plus.setOnClickListener(actionClickListener);
        Minus.setOnClickListener(actionClickListener);
        Division.setOnClickListener(actionClickListener);
        Multiply.setOnClickListener(actionClickListener);
        Procent.setOnClickListener(actionClickListener);

        Result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (EndResult.getText().length() != 0
                        && Character.isDigit(EndResult.getText().toString().charAt(EndResult.getText().toString().length()-1))) {
                    calculate();
                    Action = '=';
                    EndResult.setText(String.valueOf(ResultValue));
                }
            }
        });

        View.OnClickListener specialClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Button button = (Button) view;
                if (EndResult.getText().length() != 0 &&
                        Character.isDigit(EndResult.getText().toString().charAt(EndResult.getText().toString().length()-1)) ||
                        EndResult.getText().length() == 0) {
                    calculate();
                    Action = button.getText().charAt(0);
                    EndResult.setText(String.valueOf(ResultValue) + Action);
                    specialCalculate();
                    EndResult.setText(String.valueOf(ResultValue));
                }
            }
        };

        Sqrt.setOnClickListener(specialClickListener);
        Sqr.setOnClickListener(specialClickListener);

        Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultValue = Double.NaN;
                EndResult.setText("0");
            }
        });
    }

    private void setupView(){
        One = (Button) findViewById(R.id.One);
        Two = (Button) findViewById(R.id.Two);
        Three = (Button) findViewById(R.id.Three);
        Four = (Button) findViewById(R.id.Four);
        Five = (Button) findViewById(R.id.Five);
        Six = (Button) findViewById(R.id.Six);
        Seven = (Button) findViewById(R.id.Seven);
        Eight = (Button) findViewById(R.id.Eight);
        Nine = (Button) findViewById(R.id.Nine);
        Zero = (Button) findViewById(R.id.Zero);
        Minus = (Button) findViewById(R.id.Minus);
        Plus = (Button) findViewById(R.id.Plus);
        Result = (Button) findViewById(R.id.Result);
        Division = (Button) findViewById(R.id.Division);
        Multiply = (Button) findViewById(R.id.Multiply);
        Sqrt = (Button) findViewById(R.id.Sqrt);
        Sqr = (Button) findViewById(R.id.Sqr);
        Procent = (Button) findViewById(R.id.Procent);
        Clear = (Button) findViewById(R.id.Clear);
        EndResult = (TextView) findViewById(R.id.EndResult);
    }

    private void calculate()
    {
        if (!Double.isNaN(ResultValue)) {
            String textEndResult = EndResult.getText().toString();
            int index = textEndResult.indexOf(Action);
            if (index != -1) {
                String numberValue = textEndResult.substring(index + 1);
                Value = Double.parseDouble(numberValue);
                switch (Action) {
                    case '/':
                        if (Value == 0) {
                            ResultValue = 0.0;
                        } else {
                            ResultValue /= Value;
                        }
                        break;
                    case '*':
                        ResultValue *= Value;
                        break;
                    case '+':
                        ResultValue += Value;
                        break;
                    case '-':
                        ResultValue -= Value;
                        break;
                    case '%':
                        ResultValue = ResultValue/100 * Value;
                        break;
                    case '=':
                        ResultValue = Value;
                        break;
                }
            }
        }
        else
        {
            try {
                ResultValue = Double.parseDouble(EndResult.getText().toString());
            } catch (Exception e){
                e.printStackTrace();
                ResultValue = 0.0;
            }
        }
        EndResult.setText(String.valueOf(ResultValue));
    }

    private void specialCalculate()
    {
        if (!Double.isNaN(ResultValue) && ResultValue != 0.0) {
            String textEndResult = EndResult.getText().toString();
            int index = textEndResult.indexOf(Action);
            if (index != -1) {
                switch (Action) {
                    case '√':
                        ResultValue = Math.sqrt(ResultValue);
                        break;
                    case '²':
                        ResultValue = Math.pow(ResultValue, 2);
                        break;
                }
            }
        }
        else
        {
            try {
                ResultValue = Double.parseDouble(EndResult.getText().toString());
            } catch (Exception e) {
                e.printStackTrace();
                ResultValue = 0.0;
            }
        }
    }

}
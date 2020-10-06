package com.example.testrun;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addButton = (Button) findViewById(R.id.calcButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText timeEditText = (EditText) findViewById(R.id.timeEditText);
                EditText distanceNumEditText = (EditText) findViewById(R.id.distanceEditText);
                TextView resultTextView = (TextView) findViewById(R.id.resultTextView);

                String time = timeEditText.getText().toString();
                String[] times = time.split(":");
                int hours = Integer.parseInt(times[0]);
                int minutes = Integer.parseInt(times[1]);
                int seconds = Integer.parseInt(times[2]);


                float num2 = Float.parseFloat(distanceNumEditText.getText().toString());
            }
        });
    }


}
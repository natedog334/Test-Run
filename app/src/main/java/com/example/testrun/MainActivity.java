package com.example.testrun;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    enum Units {
        MILES("mi"),
        METERS("m"),
        KILOMETERS("km");

        private String abbreviation;

        Units(String abbreviation) {
            this.abbreviation = abbreviation;
        }

        public String getAbbreviation() {
            return this.abbreviation;
        }
    }

    Units inputUnits;
    Units resultUnits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set up unit spinner
        Spinner unitSpinner = findViewById(R.id.unitSpinner);
        final Spinner resultUnitSpinner = findViewById(R.id.resultUnitSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.units, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitSpinner.setAdapter(adapter);
        resultUnitSpinner.setAdapter(adapter);
        unitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0) { // miles
                    inputUnits = Units.MILES;
                } else if(position == 1) { // meters
                    inputUnits = Units.METERS;
                } else if(position == 2) { // kilometers
                    inputUnits = Units.KILOMETERS;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                inputUnits = Units.MILES; // default to miles
            }
        });
        resultUnitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0) { // miles
                    resultUnits = Units.MILES;
                } else if(position == 1) { // meters
                    resultUnits = Units.METERS;
                } else if(position == 2) { // kilometers
                    resultUnits = Units.KILOMETERS;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                resultUnits = Units.MILES;
            }
        });

        // do pace calculation when button is pressed
        Button addButton = (Button) findViewById(R.id.calcButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText hoursEditText = (EditText) findViewById(R.id.hoursEditText);
                EditText minutesEditText = (EditText) findViewById(R.id.minutesEditText);
                EditText secondsEditText = (EditText) findViewById(R.id.secondsEditText);
                EditText distanceEditText = (EditText) findViewById(R.id.distanceEditText);

                TextView resultTextView = (TextView) findViewById(R.id.resultTextView);

                String[] times = {hoursEditText.getText().toString(), minutesEditText.getText().toString(), secondsEditText.getText().toString()};
                float distance = Float.parseFloat(distanceEditText.getText().toString());

                String pace = calculatePace(times, distance);
                pace = pace + " " + "min/" + resultUnits.getAbbreviation();

                resultTextView.setText(pace);
            }
        });
    }

    String calculatePace(String[] times, float distance) {
        int hours = Integer.parseInt(times[0]) * 3600;
        int minutes = Integer.parseInt(times[1]) * 60;
        int seconds = Integer.parseInt(times[2]);
        int totalSeconds = hours + minutes + seconds;

        float secondsPerDistance = totalSeconds / distance;

        int paceMinutes = (int)secondsPerDistance / 60;
        int paceSeconds = (int)secondsPerDistance % 60;
        String paceSecondsFormatted = String.format("%02d", paceSeconds);
        return paceMinutes + ":" + paceSecondsFormatted;
    }
}
package com.example.testrun;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner unitSpinner = findViewById(R.id.unitSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.units, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitSpinner.setAdapter(adapter);
        unitSpinner.setOnItemSelectedListener(this);

        Button addButton = (Button) findViewById(R.id.calcButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText timeEditText = (EditText) findViewById(R.id.timeEditText);
                EditText distanceNumEditText = (EditText) findViewById(R.id.distanceEditText);

                TextView resultTextView = (TextView) findViewById(R.id.resultTextView);

                String time = timeEditText.getText().toString();
                String[] times = time.split(":");
                float distance = Float.parseFloat(distanceNumEditText.getText().toString());

                String pace = calculatePace(times, distance);

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
        return paceMinutes + ":" + paceSeconds;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
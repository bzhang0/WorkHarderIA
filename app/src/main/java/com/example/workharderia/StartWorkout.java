package com.example.workharderia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class StartWorkout extends AppCompatActivity {
    private String selectedFocusArea;
    private TextView text_selectedFocusAreaTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_workout);

        Intent intent = getIntent();
        selectedFocusArea = intent.getStringExtra("com.example.workharderia.FOCUSAREA");

        text_selectedFocusAreaTitle = (TextView) findViewById(R.id.text_selectedFocusAreaTitle);
        text_selectedFocusAreaTitle.setText("selected focus area: " + selectedFocusArea);
    }
}
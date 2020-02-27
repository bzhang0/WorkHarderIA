package com.example.workharderia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button buttonGoToAddWorkout;
    private Button buttonGoToHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonGoToAddWorkout = findViewById(R.id.buttonGoToAddWorkout);
        buttonGoToAddWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddWorkoutActivity();
            }
        });
    }

    public void openAddWorkoutActivity() {
        Intent intent = new Intent(this, AddWorkout.class);
        startActivity(intent);
    }

    public void openViewHistory() {
        Intent intent = new Intent(this, AddWorkout.class);
        startActivity(intent);
    }
}

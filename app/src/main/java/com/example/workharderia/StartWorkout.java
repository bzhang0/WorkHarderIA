package com.example.workharderia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import org.w3c.dom.Text;

public class StartWorkout extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    private SQLiteDatabase mDatabase;

    private String passedSelectedFocusArea;
    private TextView text_selectedFocusAreaTitle;

    private Button button_selectWorkoutIndicator;

    private TextView mTextViewWeight;
    private TextView mTextViewReps;
    private int mWeight = 0;
    private int mReps = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_workout);

        Intent intent = getIntent();
        passedSelectedFocusArea = intent.getStringExtra("com.example.workharderia.FOCUSAREA");


        ExerciseDBHelper dbHelper = new ExerciseDBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();

        text_selectedFocusAreaTitle = (TextView) findViewById(R.id.text_selectedFocusAreaTitle);
        text_selectedFocusAreaTitle.setText("selected focus area: " + passedSelectedFocusArea);

        button_selectWorkoutIndicator = (Button) findViewById(R.id.button_selectWorkoutIndicator);
        mTextViewWeight = (TextView) findViewById(R.id.weight_amountDisplayText);
        mTextViewReps = (TextView) findViewById(R.id.rep_amountDisplayText);

        Button weight_buttonIncrease = findViewById(R.id.weight_add);
        Button weight_buttonDecrease = findViewById(R.id.weight_subtract);

        Button rep_buttonIncrease = findViewById(R.id.rep_add);
        Button rep_buttonDecrease = findViewById(R.id.rep_subtract);

        Button buttonInsertExercise = findViewById(R.id.insert_exercise);

        weight_buttonIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weightIncrease();
            }
        });

        weight_buttonDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weightDecrease();
            }
        });

        rep_buttonIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repIncrease();
            }
        });

        rep_buttonDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repDecrease();
            }
        });

        buttonInsertExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertExercise();
            }
        });
    }

    private void weightIncrease() {
        mWeight += 5;
        mTextViewWeight.setText(String.valueOf(mWeight));
    }

    private void weightDecrease() {
        if (mWeight > 0) {
            mWeight -= 5;
            mTextViewWeight.setText(String.valueOf(mWeight));
        }
    }

    private void repIncrease() {
        mReps++;
        mTextViewReps.setText(String.valueOf(mReps));
    }

    private void repDecrease() {
        if (mReps > 0) {
            mReps--;
            mTextViewReps.setText(String.valueOf(mReps));
        }
    }

    private void insertExercise() {
        String exerciseName = button_selectWorkoutIndicator.getText().toString();
        if (exerciseName.equals("select workout") || mReps == 0) {
            return;
        }

        ContentValues cv = new ContentValues();
        cv.put(WorkoutContract.WorkoutEntry.COLUMN_EXERCISENAME, exerciseName);
        cv.put(WorkoutContract.WorkoutEntry.COLUMN_WEIGHT, mWeight);
        cv.put(WorkoutContract.WorkoutEntry.COLUMN_REPS, mReps);

        mDatabase.insert(WorkoutContract.WorkoutEntry.TABLE_NAME, null, cv);
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        if (passedSelectedFocusArea.equals("arms")) {
            popup.inflate(R.menu.workout_menu_arms);
        } else if (passedSelectedFocusArea.equals("back")) {
            popup.inflate(R.menu.workout_menu_back);
        } else if (passedSelectedFocusArea.equals("chest")) {
            popup.inflate(R.menu.workout_menu_chest);
        } else if (passedSelectedFocusArea.equals("core")) {
            popup.inflate(R.menu.workout_menu_core);
        } else { // legs
            popup.inflate(R.menu.workout_menu_legs);
        }
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        button_selectWorkoutIndicator.setText(item.getTitle().toString());
        return true;
    }
}
package com.example.workharderia;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewHistory extends AppCompatActivity {
    private SQLiteDatabase mDatabase;
    private ExerciseAdapter mAdapter;

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
        setContentView(R.layout.activity_view_history);

        ExerciseDBHelper dbHelper = new ExerciseDBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();

        RecyclerView recyclerView = findViewById(R.id.recyclerviewer);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ExerciseAdapter(this, getAllExercises());
        recyclerView.setAdapter(mAdapter);
    }

    private Cursor getAllExercises() {
        return mDatabase.query(
                WorkoutContract.WorkoutEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                WorkoutContract.WorkoutEntry.COLUMN_TIMESTAMP + " DESC"
        );
    }
    /*
    public void viewAll() {
        buttonViewDatabaseAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllData();
                if (res.getCount() == 0) {
                    showMessage("error", "nothing found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();

                while (res.moveToNext()) {
                    buffer.append("exercise name: "+ res.getString(1) + "\n");
                    buffer.append("weight: "+ res.getString(2) + "\n");
                    buffer.append("reps: "+ res.getString(3) + "\n");
                    buffer.append("timestamp: "+ res.getString(4) + "\n\n");

                }

                showMessage("data", buffer.toString());
            }
        });
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);

        builder.show();
    }
     */
}

package com.example.workharderia;

import android.provider.BaseColumns;

public class WorkoutContract {
    private WorkoutContract() {
    }

    public static final class WorkoutEntry implements BaseColumns {
        public static final String TABLE_NAME = "exerciseList";
        public static final String COLUMN_EXERCISENAME = "exercisename";
        public static final String COLUMN_WEIGHT = "weight";
        public static final String COLUMN_REPS = "reps";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }
}

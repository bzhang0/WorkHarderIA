package com.example.workharderia;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.workharderia.WorkoutContract.*;

import androidx.annotation.Nullable;

public class ExerciseDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "exercisehistory.db";
    public static final int DATABASE_VERSION = 2;

    public ExerciseDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_EXERCISEHISTORY_TABLE = "CREATE TABLE " +
                WorkoutEntry.TABLE_NAME + " (" +
                WorkoutEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                WorkoutEntry.COLUMN_EXERCISENAME + " TEXT NOT NULL, " +
                WorkoutEntry.COLUMN_WEIGHT + " INTEGER NOT NULL, " +
                WorkoutEntry.COLUMN_REPS+ " INTEGER NOT NULL, " +
                WorkoutEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";

        db.execSQL(SQL_CREATE_EXERCISEHISTORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + WorkoutEntry.TABLE_NAME);
        onCreate(db);
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + WorkoutEntry.TABLE_NAME, null);
        return res;
    }
}

package com.example.workharderia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;

import org.w3c.dom.Text;

public class StartWorkout extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
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

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }
}
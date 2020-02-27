package com.example.workharderia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;


public class AddWorkout extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    public static final String FOCUS_AREA = "com.example.workharderia.FOCUSAREA";

    private Button button_selectFocusArea;
    private Button button_submitSelectedFocusArea;

    private TextView text_selectedFocusArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_workout);

        button_selectFocusArea = (Button) findViewById(R.id.button_selectFocusArea);
        button_submitSelectedFocusArea = (Button) findViewById(R.id.button_submitSelectedFocusArea);

        text_selectedFocusArea = (TextView) findViewById(R.id.text_selectedFocusArea);

        button_submitSelectedFocusArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(text_selectedFocusArea.getText().toString().equals("unselected"))) {
                    startWorkout();
                }
            }
        });
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        text_selectedFocusArea.setText(item.getTitle().toString());
        return true;
    }

    public void startWorkout() {
        String selectedFocusArea = text_selectedFocusArea.getText().toString();

        Intent intent = new Intent(this, StartWorkout.class);
        intent.putExtra(FOCUS_AREA, selectedFocusArea);
        startActivity(intent);
    }
}

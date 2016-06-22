package com.stanislav.tabswithfragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.UUID;

public class CreateWorkoutActivity extends AppCompatActivity {

    private static final String EXTRA_CRIME_ID =
            "com.bignerdranch.android.criminalintent.crime_id";
    private Workout workout;

    private static final String TAG = "CreateWorkoutActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_workout);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        TextView editText = (TextView) findViewById(R.id.editText);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setEnabled(true);
                v.setFocusable(true);
                v.getId();
            }
        });

        editText.setOnTouchListener(new View.OnTouchListener() {

            private Rect rect = new Rect();

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        v.setBackgroundDrawable(getResources().getDrawable(R.drawable.edt_bg_selected));
                        rect = new Rect(v.getLeft() - 40, v.getTop() - 20, v.getRight() + 40 , v.getBottom() + 20);
                        //v.getHitRect(rect);
                        //v.getGlobalVisibleRect(rect);
                        break;
                    case MotionEvent.ACTION_UP:
                        v.setBackgroundDrawable(getResources().getDrawable(R.drawable.edt_bg_normal));
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if(!rect.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())){
                            v.setBackgroundDrawable(getResources().getDrawable(R.drawable.edt_bg_normal));
                        }
                        break;
//                    case MotionEvent.ACTION_CANCEL:
//                        v.setBackgroundDrawable(getResources().getDrawable(R.drawable.edt_bg_normal));
//                        break;
//                    case MotionEvent.ACTION_HOVER_EXIT:
//                        v.setBackgroundDrawable(getResources().getDrawable(R.drawable.edt_bg_normal));
//                        break;
                }
                return true;
            }
        });

        workout = new Workout();
        workout.setBodyWeight("50");

        Button saveButton = (Button) findViewById(R.id.button_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LiftingLab.get(CreateWorkoutActivity.this).addWorkout(workout);
                Log.i(TAG, "Saved Workout");
            }
        });
    }

    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, CreateWorkoutActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create_workout, menu);
        return true;
    }

    public boolean onTouch(View v, MotionEvent m) {
        return true;
    }

}

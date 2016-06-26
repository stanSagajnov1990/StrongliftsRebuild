package com.stanislav.tabswithfragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class CreateWorkoutActivity extends AppCompatActivity implements WeightSelectorFragment.NoticeDialogListener {

    private static final String EXTRA_CRIME_ID =
            "com.bignerdranch.android.criminalintent.crime_id";
    private Workout workout;

    private static final String TAG = "CreateWorkoutActivity";
    private int dialogActiveNumber = 0;
    private TextView editTextSquat;
    private TextView editTextOHP;
    private TextView editTextDL;

    private EditText et_date;

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

        workout = new Workout();
        workout.setBodyWeight("50");
        workout.addExercise(createExercise(Exercise.SQUAT, 50));
        workout.addExercise(createExercise(Exercise.OVERHEAD_PRESS, 75));
        workout.addExercise(createExercise(Exercise.DL, 100));

        editText.setOnTouchListener(new View.OnTouchListener() {

            private Rect rect = new Rect();

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        v.setBackgroundDrawable(getResources().getDrawable(R.drawable.edt_bg_selected));
                        rect = new Rect(v.getLeft() - 40, v.getTop() - 20, v.getRight() + 40, v.getBottom() + 20);
                        //v.getHitRect(rect);
                        //v.getGlobalVisibleRect(rect);
                        break;
                    case MotionEvent.ACTION_UP:
                        v.setBackgroundDrawable(getResources().getDrawable(R.drawable.edt_bg_normal));
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (!rect.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
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

        editTextSquat = (TextView) findViewById(R.id.editTextSquat);
        editTextOHP = (TextView) findViewById(R.id.editTextOHP);
        editTextDL = (TextView) findViewById(R.id.editTextDL);
        et_date = (EditText) findViewById(R.id.et_date);


//        editTextSquat.setOnTouchListener(new View.OnTouchListener() {
//
//            private Rect rect = new Rect();
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        v.setBackgroundDrawable(getResources().getDrawable(R.drawable.edt_bg_selected));
//                        rect = new Rect(v.getLeft() - 40, v.getTop() - 20, v.getRight() + 40 , v.getBottom() + 20);
//                        //v.getHitRect(rect);
//                        //v.getGlobalVisibleRect(rect);
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        v.setBackgroundDrawable(getResources().getDrawable(R.drawable.edt_bg_normal));
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        if(!rect.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())){
//                            v.setBackgroundDrawable(getResources().getDrawable(R.drawable.edt_bg_normal));
//                        }
//                        break;
////                    case MotionEvent.ACTION_CANCEL:
////                        v.setBackgroundDrawable(getResources().getDrawable(R.drawable.edt_bg_normal));
////                        break;
////                    case MotionEvent.ACTION_HOVER_EXIT:
////                        v.setBackgroundDrawable(getResources().getDrawable(R.drawable.edt_bg_normal));
////                        break;
//                }
//                return true;
//            }
//        });

        editTextSquat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogActiveNumber = 0;
                FragmentManager manager = getSupportFragmentManager();
                WeightSelectorFragment dialog = WeightSelectorFragment.newInstance(String.valueOf(workout.getExercises().get(0).getWeight()), "kg");
                dialog.show(manager, "DialogDate");
            }
        });

        editTextOHP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogActiveNumber = 3;
                FragmentManager manager = getSupportFragmentManager();
                WeightSelectorFragment dialog = WeightSelectorFragment.newInstance(String.valueOf(workout.getExercises().get(1).getWeight()), "kg");
                dialog.show(manager, "DialogDate");
            }
        });

        editTextDL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogActiveNumber = 4;
                FragmentManager manager = getSupportFragmentManager();
                WeightSelectorFragment dialog = WeightSelectorFragment.newInstance(String.valueOf(workout.getExercises().get(2).getWeight()), "kg");
                dialog.show(manager, "DialogDate");
            }
        });


        Button saveButton = (Button) findViewById(R.id.button_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = et_date.getText().toString();
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                Date date = new Date();
                try {
                    date = sdf.parse(text);
                } catch (ParseException e){
                    e.printStackTrace();
                }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String weight = (String) data.getSerializableExtra(WeightSelectorFragment.EXTRA_DATE);
        Log.i("CreateWorkoutActivity", weight);

        super.onActivityResult(requestCode, resultCode, data);
    }

    public boolean onTouch(View v, MotionEvent m) {
        return true;
    }

    private Exercise createExercise(String type, double weight) {
        Exercise exercise = new Exercise();
        exercise.setType(type);
        exercise.setWeight(weight);

        return exercise;
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        WeightSelectorFragment fragment = (WeightSelectorFragment) dialog;
        Log.i("CreateWorkoutActivity", "Value from: " + fragment.getValue());
        double weight = Double.valueOf(fragment.getValue());

        DecimalFormat f = new DecimalFormat("0.##");
        String weightInText = f.format(weight);
        switch (dialogActiveNumber) {
            case WeightSelectorFragment.SQUAT:
                workout.getExercises().get(0).setWeight(weight);
                editTextSquat.setText(String.format("5x5 %sKG", weightInText));
                break;
            case WeightSelectorFragment.BENCH_PRESS:
                workout.getExercises().get(1).setWeight(weight);
                editTextOHP.setText(String.format("5x5 %sKG", weightInText));
                break;
            case WeightSelectorFragment.BARBELL_ROW:
                workout.getExercises().get(2).setWeight(weight);
                editTextDL.setText(String.format("5x5 %sKG", weightInText));
                break;
            case WeightSelectorFragment.OVERHEAD_PRESS:
                workout.getExercises().get(1).setWeight(weight);
                editTextDL.setText(String.format("5x5 %sKG", weightInText));
                break;
            case WeightSelectorFragment.DEADLIFT:
                workout.getExercises().get(2).setWeight(weight);
                editTextDL.setText(String.format("5x5 %sKG", weightInText));
                break;
        }
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }

    public void onClick(View v) {
        Log.i("CreateWorkoutActivity", (R.id.textView34 == v.getId()) + "");

        //switch (v.getId()){
        //case R.id.textView34:
        TextView textView = (TextView) v;
        int repitions = Integer.parseInt(String.valueOf(textView.getText()));
        if (repitions == 0) {
            repitions = 5;
        } else {
            repitions--;
        }
        textView.setText(String.valueOf(repitions));
        //     break;
        //}

    }
}

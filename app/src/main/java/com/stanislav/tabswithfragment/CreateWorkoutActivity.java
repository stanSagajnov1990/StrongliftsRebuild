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
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.stanislav.utils.DateFormatUtils;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CreateWorkoutActivity extends AppCompatActivity implements WeightSelectorFragment.NoticeDialogListener {

    private static final String EXTRA_CRIME_ID =
            "com.stanislav.tabswithfragment.workout_id";
    private Workout workout;

    private static final String TAG = "CreateWorkoutActivity";
    private int dialogActiveNumber = 0;
    private TextView editTextSquat;
    private TextView editTextOHP;
    private TextView editTextDL;

    private EditText et_date;
    private TextView tvExercise2;
    private TextView tvExercise3;
    private int activeWorkout = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_workout);

        tvExercise2 = (TextView) findViewById(R.id.tvExercise2);
        tvExercise3 = (TextView) findViewById(R.id.tvExercise3);
        editTextSquat = (TextView) findViewById(R.id.tvSquatWeight);
        editTextOHP = (TextView) findViewById(R.id.tvExerciseWeight2);
        editTextDL = (TextView) findViewById(R.id.tvExerciseWeight3);
        et_date = (EditText) findViewById(R.id.et_date);

        Button saveButton = (Button) findViewById(R.id.button_save);
        final UUID workoutId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        Log.i(TAG, "EXTRA_CRIME_ID: " + workoutId);
        if (workoutId != null) {
            saveButton.setText("SAVE");
            List<Workout> workouts = LiftingLab.get(this).getWorkouts();
            for (Workout w : workouts) {
                if (w.getUuid().equals(workoutId)) {
                    workout = w;
                }
            }

        } else {
            saveButton.setText("FINISH");
        }

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        TextView editText = (TextView) findViewById(R.id.tvBodyweightNumber);
        assert editText != null;
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setEnabled(true);
                v.setFocusable(true);
                v.getId();
            }
        });

        if (workout == null) {
            workout = createWorkout();
        }

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


        if (workout.getDate() != null) {
            Log.i(TAG, "new workout");
            et_date.setText(DateFormatUtils.formatddMMyyyy(workout.getDate()));
        }

        updateWeightTV(0, workout.getExercises().get(0).getWeight());
        updateWeightTV(1, workout.getExercises().get(1).getWeight());
        updateWeightTV(2, workout.getExercises().get(2).getWeight());

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


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                String textButton = (String) button.getText();
                if (textButton.equalsIgnoreCase("save")) {
                    String text = et_date.getText().toString();
                    Date date = DateFormatUtils.parseddMMyyyy(text);
                    date = date != null ? date : new Date();
                    workout.setDate(date);
                    LiftingLab.get(CreateWorkoutActivity.this).updateWorkout(workout);
                    Log.i(TAG, "Updated Workout");
                } else {
                    String text = et_date.getText().toString();
                    Date date = DateFormatUtils.parseddMMyyyy(text);
                    date = date != null ? date : new Date();
                    workout.setDate(date);
                    LiftingLab liftingLab = LiftingLab.get(CreateWorkoutActivity.this);
                    liftingLab.addWorkout(workout);
                    Log.i(TAG, "Saved Workout");
                }
                finish();
            }
        });
    }

    private Workout createWorkout() {
        Workout workout = new Workout();
        workout.setBodyWeight("50");
        workout.setType("A");
        workout.addExercise(createExercise(Exercise.SQUAT, 50));
        workout.addExercise(createExercise(Exercise.OVERHEAD_PRESS, 75));
        workout.addExercise(createExercise(Exercise.DL, 100));
        return workout;
    }

    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, CreateWorkoutActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create_workout, menu);
        MenuItem item = menu.findItem(R.id.action_switch);
        String textSwitch = getResources().getString(R.string.action_switch);
        item.setTitle(String.format(textSwitch, "B"));
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
                updateWeightTV(1, weight);
                break;
            case WeightSelectorFragment.BARBELL_ROW:
                workout.getExercises().get(2).setWeight(weight);
                editTextDL.setText(String.format("5x5 %sKG", weightInText));
                break;
            case WeightSelectorFragment.OVERHEAD_PRESS:
                updateWeightTV(1, weight);
                break;
            case WeightSelectorFragment.DEADLIFT:
                workout.getExercises().get(2).setWeight(weight);
                editTextDL.setText(String.format("5x5 %sKG", weightInText));
                break;
        }
    }

    private void updateWeightTV(int position, double weight) {
        DecimalFormat f = new DecimalFormat("0.##");
        String weightInText = f.format(weight);

        workout.getExercises().get(position).setWeight(weight);
        if (position == 0) {
            editTextSquat.setText(String.format("5x5 %sKG", weightInText));
        } else if (position == 1) {
            editTextOHP.setText(String.format("5x5 %sKG", weightInText));
        } else if (position == 2) {
            editTextDL.setText(String.format("5x5 %sKG", weightInText));
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {

        } else if (id == R.id.action_deleteWorkout) {
            LiftingLab.get(this).deleteWorkout(workout);
            finish();
        } else if (id == R.id.action_switch) {
            activeWorkout = (activeWorkout + 1) % 2;
            changeLabelsForWorkout(item);
        } else {

        }

        return super.onOptionsItemSelected(item);
    }

    private void changeLabelsForWorkout(MenuItem item) {
        String itemText = getString(R.string.action_switch);
        if (activeWorkout == 0) {
            workout.setType("B");
            tvExercise2.setText(getResources().getString(R.string.tv_ohp_exercise));
            tvExercise3.setText(getResources().getString(R.string.tv_dl_exercise));
            item.setTitle(String.format(itemText, "A"));
        } else if (activeWorkout == 1) {
            workout.setType("A");
            tvExercise2.setText(getResources().getString(R.string.tv_bp_exercise));
            tvExercise3.setText(getResources().getString(R.string.tv_row_exercise));
            item.setTitle(String.format(itemText, "B"));
        }
    }
}

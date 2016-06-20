package com.stanislav.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stanislav.tabswithfragment.CreateWorkoutActivity;
import com.stanislav.tabswithfragment.LiftingLab;
import com.stanislav.tabswithfragment.MainActivity;
import com.stanislav.tabswithfragment.R;
import com.stanislav.tabswithfragment.Workout;

import java.util.Locale;

public class Fragment1 extends Fragment {

    private static final String TAG = "Fragment1";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment1_layout, container, false);

        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.createWorkout);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Workout workout = new Workout();
                //LiftingLab.get(getActivity()).addWorkout(workout);
                Intent i = new Intent(getActivity(), CreateWorkoutActivity.class);
                //Intent intent = CreateWorkoutActivity.newIntent(getActivity(), workout.getId());
                startActivity(i);
            }
        });

        Workout workout = LiftingLab.get(getActivity()).getLatestWorkout();
        Log.i(TAG, "onCreateView");
        TextView tvSQNumber = (TextView) v.findViewById(R.id.textViewSQNumber);

        String outputForSQ = String.format(Locale.US, "5x5 %.1fkg",workout.getExercises().get(0).getWeight());
        tvSQNumber.setText(outputForSQ);

        return v;
    }
}

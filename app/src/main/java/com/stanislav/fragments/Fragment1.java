package com.stanislav.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
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
        TextView tvBPNumber = (TextView) v.findViewById(R.id.textViewBPNumber);
        TextView tvROWNumber = (TextView) v.findViewById(R.id.textViewROWNumber);

        String outputForSQ = String.format(Locale.US, "5x5 %.1fkg",workout.getExercises().get(0).getWeight());
        String outputForBP = String.format(Locale.US, "5x5 %.1fkg",workout.getExercises().get(1).getWeight());
        String outputForROW = String.format(Locale.US, "5x5 %.1fkg",workout.getExercises().get(2).getWeight());
        tvSQNumber.setText(outputForSQ);
        tvBPNumber.setText(outputForBP);
        tvROWNumber.setText(outputForROW);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_fragment1, menu);
    }
}

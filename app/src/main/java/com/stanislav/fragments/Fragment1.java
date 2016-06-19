package com.stanislav.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stanislav.tabswithfragment.CreateWorkoutActivity;
import com.stanislav.tabswithfragment.MainActivity;
import com.stanislav.tabswithfragment.R;
import com.stanislav.tabswithfragment.Workout;

public class Fragment1 extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment1_layout, container, false);

        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.createWorkout);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), CreateWorkoutActivity.class);
                Workout workout = new Workout();
                Intent intent = CreateWorkoutActivity.newIntent(getActivity(), workout.getId());
                startActivity(i);
            }
        });

        return v;
    }
}

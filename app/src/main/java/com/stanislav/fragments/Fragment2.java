package com.stanislav.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stanislav.tabswithfragment.LiftingLab;
import com.stanislav.tabswithfragment.R;
import com.stanislav.tabswithfragment.Workout;
import com.stanislav.utils.DateFormatUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Fragment2 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment2_layout, container, false);
        final HorizontalScrollView horizontalScrollView = (HorizontalScrollView) v.findViewById(R.id.horizontalScrollView);
        LinearLayout horizontalScrollView_child = (LinearLayout) v.findViewById(R.id.horizontalScrollView_child);


        List<Workout> workouts = LiftingLab.get(getActivity()).getWorkouts();
        for (int i = 0; i < workouts.size(); i++) {
            ViewGroup viewGroup = (ViewGroup) v.findViewById(R.id.workout_column);
            //View view = (LinearLayout) getResources().getLayout(R.layout.fragment2_column);
            ViewGroup column = (ViewGroup) inflater.inflate(R.layout.fragment2_column, viewGroup, false);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(getPx(120), ViewGroup.LayoutParams.WRAP_CONTENT);

            column.setLayoutParams(params);
            horizontalScrollView_child.addView(column);


            Workout workout = workouts.get(i);
            Date date = workout.getDate();


            TextView tvDay = (TextView) column.findViewById(R.id.tvDay);
            TextView tvDate = (TextView) column.findViewById(R.id.tvDate);
            tvDate.setText(DateFormatUtils.format(date));
            tvDay.setText(DateFormatUtils.getDayOfWeek(date));
        }
        horizontalScrollView.postDelayed(new Runnable() {
            public void run() {
                horizontalScrollView.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
            }
        }, 100L);

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
        inflater.inflate(R.menu.menu_fragment4, menu);
    }

    public int getPx(int dimensionDp) {
        float density = getResources().getDisplayMetrics().density;
        return (int) (dimensionDp * density + 0.5f);
    }
}

package com.stanislav.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Spinner;

import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.stanislav.tabswithfragment.R;

public class Fragment4 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment4_layout, container, false);

        MaterialCalendarView calendar = (MaterialCalendarView) v.findViewById(R.id.calendarView);
        calendar.setTopbarVisible(false);
        calendar.setWeekDayLabels(new String[]{"S", "M", "T", "M", "T", "F", "S"});
        calendar.setTileWidthDp(50);
        calendar.setTileHeightDp(40);

        return v;
    }
}

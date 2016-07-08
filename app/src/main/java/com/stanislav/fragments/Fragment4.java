package com.stanislav.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Spinner;

import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.stanislav.tabswithfragment.R;
import com.stanislav.utils.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        calendar.setSelectionMode(MaterialCalendarView.SELECTION_MODE_MULTIPLE);
        //calendar.setSelectedDate(new Date());

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        try {
            Date yesterday = sdf.parse("07.07.2016");
            Date beforeYesterday = sdf.parse("06.07.2016");
            calendar.setDateSelected(yesterday, true);
            calendar.setDateSelected(beforeYesterday, true);
        } catch (ParseException e) {
            e.printStackTrace();
        }

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
}

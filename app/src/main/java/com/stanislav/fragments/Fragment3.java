package com.stanislav.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.stanislav.tabswithfragment.R;

public class Fragment3 extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment3_layout, container, false);
        //Spinner spinner = (Spinner) v.findViewById(R.id.spinner_nav);
        //spinner.setEnabled(true);

        return v;
    }
}

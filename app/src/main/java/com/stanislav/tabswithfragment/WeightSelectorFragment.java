package com.stanislav.tabswithfragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import java.util.Date;


public class WeightSelectorFragment extends DialogFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static final int SQUAT = 0;
    public static final int BENCH_PRESS = 1;
    public static final int BARBELL_ROW = 2;
    public static final int OVERHEAD_PRESS = 3;
    public static final int DEADLIFT = 4;

    public static final String EXTRA_DATE =
            "com.bignerdranch.android.criminalintent.date";

    private EditText mEditTextWeight;

    private String mValue;

    public String getValue() {
        return mValue;
    }

    public interface NoticeDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    // Use this instance of the interface to deliver action events
    NoticeDialogListener mListener;


    private String mParam1;
    private String mParam2;

    public static WeightSelectorFragment newInstance(String param1, String param2) {
        WeightSelectorFragment fragment = new WeightSelectorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_weight_selector, null);

        mParam1 = (String) getArguments().getSerializable(ARG_PARAM1);
        Log.i("WeightSelectorFragment", "Param1: "+mParam1);

        mEditTextWeight = (EditText) v.findViewById(R.id.editTextWeight);
        mEditTextWeight.setText(mParam1);

        return new AlertDialog.Builder(getActivity()).setView(v).setPositiveButton(android.R.string.ok,new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Log.i("WeightSelectorFragment", mEditTextWeight.getText().toString());
                mValue = mEditTextWeight.getText().toString();
                mListener.onDialogPositiveClick(WeightSelectorFragment.this);
            }
        }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mListener.onDialogPositiveClick(WeightSelectorFragment.this);
            }
        }).create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof NoticeDialogListener) {
            mListener = (NoticeDialogListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }


}

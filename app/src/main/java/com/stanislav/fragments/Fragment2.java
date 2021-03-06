package com.stanislav.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stanislav.tabswithfragment.CreateWorkoutActivity;
import com.stanislav.tabswithfragment.LiftingLab;
import com.stanislav.tabswithfragment.R;
import com.stanislav.tabswithfragment.Workout;
import com.stanislav.utils.DateFormatUtils;

import org.w3c.dom.Text;

import java.util.List;

public class Fragment2 extends Fragment {

    private static final String TAG = "Fragment2";

    private RecyclerView mWorkoutsRecyclerView;
    private WorkoutsAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment2_layout, container, false);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mWorkoutsRecyclerView = (RecyclerView) v.findViewById(R.id.workouts_recycler_view);
        mWorkoutsRecyclerView.setLayoutManager(layoutManager);

        updateUI();
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        LiftingLab liftingLab = LiftingLab.get(getActivity());
        List<Workout> workouts = liftingLab.getWorkouts();

        mAdapter = new WorkoutsAdapter(workouts);
        mWorkoutsRecyclerView.setAdapter(mAdapter);
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

    private class WorkoutsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView mTvDate;
        private final TextView mTvDay;
        private final TextView mTvExerciseWeight2;
        private final TextView mTvExerciseWeight3;
        private final TextView mTvExerciseWeight1;
        private final TextView mTvExercise2;
        private final TextView mTvExercise3;

        private Workout mWorkout;

        public WorkoutsHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mTvDay = (TextView) itemView.findViewById(R.id.tvDay);
            mTvDate = (TextView) itemView.findViewById(R.id.tvDate);

            mTvExercise2 = (TextView) itemView.findViewById(R.id.tvExercise2);
            mTvExercise3 = (TextView) itemView.findViewById(R.id.tvExercise3);

            mTvExerciseWeight1 = (TextView) itemView.findViewById(R.id.tvWeight1);
            mTvExerciseWeight2 = (TextView) itemView.findViewById(R.id.tvWeight2);
            mTvExerciseWeight3 = (TextView) itemView.findViewById(R.id.tvWeight3);
        }

        public void bindWorkout(Workout workout){
            mWorkout = workout;
            mTvDay.setText(DateFormatUtils.getDayOfWeek(workout.getDate()));
            mTvDate.setText(DateFormatUtils.formatdMMMyyyy(workout.getDate()));

            if(workout.getType().equalsIgnoreCase("A")) {
                mTvExercise2.setText(getString(R.string.tv_bp_exercise_short));
                mTvExercise3.setText(getString(R.string.tv_row_exercise_short));
            } else {
                mTvExercise2.setText(getString(R.string.tv_ohp_exercise_short));
                mTvExercise3.setText(getString(R.string.tv_dl_exercise_short));
            }

            mTvExerciseWeight1.setText(workout.getExercises().get(0).getWeight().toString());
            mTvExerciseWeight2.setText(workout.getExercises().get(1).getWeight().toString());
            mTvExerciseWeight3.setText(workout.getExercises().get(2).getWeight().toString());
        }

        @Override
        public void onClick(View v) {
            Log.i(TAG, "this item selected");
            Intent intent = CreateWorkoutActivity.newIntent(getActivity(), mWorkout.getUuid());
            startActivity(intent);
        }
    }

    private class WorkoutsAdapter extends RecyclerView.Adapter<WorkoutsHolder>{

        private List<Workout> mWorkouts;

        public WorkoutsAdapter(List<Workout> workouts) {
            mWorkouts = workouts;
        }

        @Override
        public WorkoutsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.fragment2_column, parent, false);
            return new WorkoutsHolder(view);
        }

        @Override
        public void onBindViewHolder(WorkoutsHolder holder, int position) {
            Workout workout = mWorkouts.get(position);
            holder.bindWorkout(workout);
        }

        @Override
        public int getItemCount() {
            return mWorkouts.size();
        }
    }
}

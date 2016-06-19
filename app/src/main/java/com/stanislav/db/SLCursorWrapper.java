package com.stanislav.db;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.stanislav.tabswithfragment.Workout;

import com.stanislav.db.SLDbSchema.WorkoutTable;

import java.util.Date;
import java.util.UUID;


public class SLCursorWrapper extends CursorWrapper {

    public SLCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Workout getWorkout() {
        String uuidString = getString(getColumnIndex(WorkoutTable.Cols.UUID));
        long date = getLong(getColumnIndex(WorkoutTable.Cols.DATE));
        String squatWeight = getString(getColumnIndex(WorkoutTable.Cols.SQUAT_WEIGHT));

        Workout workout = new Workout(UUID.fromString(uuidString));
        workout.setDate(new Date(date));
        workout.setSquatWeight(squatWeight);

        return workout;
    }


}

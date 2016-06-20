package com.stanislav.db;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.stanislav.tabswithfragment.Exercise;
import com.stanislav.tabswithfragment.Workout;

import com.stanislav.db.SLDbSchema.WorkoutTable;

import com.stanislav.db.SLDbSchema.ExerciseTable;

import java.util.Date;
import java.util.List;
import java.util.UUID;


public class SLCursorWrapper extends CursorWrapper {

    public SLCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Workout getWorkout() {
        String uuidString = getString(getColumnIndex(WorkoutTable.Cols.UUID));
        long date = getLong(getColumnIndex(WorkoutTable.Cols.DATE));
        String bodyWeight = getString(getColumnIndex(WorkoutTable.Cols.BODY_WEIGHT));

        Workout workout = new Workout(UUID.fromString(uuidString));
        workout.setDate(new Date(date));
        workout.setBodyWeight(bodyWeight);

        return workout;
    }

    public Exercise getExercise(){
        String uuid = getString(getColumnIndex(ExerciseTable.Cols.UUID));
        String type = getString(getColumnIndex(ExerciseTable.Cols.TYPE));
        double weight = getDouble(getColumnIndex(ExerciseTable.Cols.WEIGHT));
        int first = getInt(getColumnIndex(ExerciseTable.Cols.FIRST));
        int second = getInt(getColumnIndex(ExerciseTable.Cols.SECOND));
        int third = getInt(getColumnIndex(ExerciseTable.Cols.THIRD));
        int fourth = getInt(getColumnIndex(ExerciseTable.Cols.FOURTH));
        int fifth = getInt(getColumnIndex(ExerciseTable.Cols.FIFTH));

        Exercise exercise = new Exercise(UUID.fromString(uuid));
        exercise.setType(type);
        exercise.setWeight(weight);
        exercise.setFirst(first);
        exercise.setSecond(second);
        exercise.setThird(third);
        exercise.setFourth(fourth);
        exercise.setFifth(fifth);

        return exercise;
    }

}

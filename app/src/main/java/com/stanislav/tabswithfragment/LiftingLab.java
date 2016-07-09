package com.stanislav.tabswithfragment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.stanislav.db.SLBaseHelper;
import com.stanislav.db.SLCursorWrapper;
import com.stanislav.db.SLDbSchema.ExerciseTable;
import com.stanislav.db.SLDbSchema.WorkoutTable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class LiftingLab {

    private static LiftingLab sLiftingLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    private static final String TAG = "LiftingLab";


    public static LiftingLab get(Context context) {
        if (sLiftingLab == null) {
            sLiftingLab = new LiftingLab(context);
        }
        return sLiftingLab;
    }

    private LiftingLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new SLBaseHelper(mContext).getWritableDatabase();
    }

    private static ContentValues getContentValues(Workout w) {
        ContentValues values = new ContentValues();
        values.put(WorkoutTable.Cols.UUID, w.getUuid().toString());
        values.put(WorkoutTable.Cols.DATE, w.getDate().getTime());
        values.put(WorkoutTable.Cols.BODY_WEIGHT, w.getBodyWeight());
        values.put(WorkoutTable.Cols.TYPE, w.getType());

        return values;
    }

    private static ContentValues getContentValues(Exercise e, int workoutId) {
        ContentValues values = new ContentValues();
        values.put(ExerciseTable.Cols.UUID, e.getId().toString());
        values.put(ExerciseTable.Cols.TYPE, e.getType());
        values.put(ExerciseTable.Cols.WEIGHT, e.getWeight());
        values.put(ExerciseTable.Cols.FIRST, e.getFirst());
        values.put(ExerciseTable.Cols.SECOND, e.getSecond());
        values.put(ExerciseTable.Cols.THIRD, e.getThird());
        values.put(ExerciseTable.Cols.FOURTH, e.getFourth());
        values.put(ExerciseTable.Cols.FIFTH, e.getFifth());
        values.put(ExerciseTable.Cols.WORKOUT_FK, workoutId);

        return values;
    }

    public void addWorkout(Workout workout) {
        ContentValues values = getContentValues(workout);
        int workoutId = (int) mDatabase.insert(WorkoutTable.NAME, null, values);

        for (Exercise exercise : workout.getExercises()) {
            ContentValues valuesExercise = getContentValues(exercise, workoutId);
            mDatabase.insert(ExerciseTable.NAME, null, valuesExercise);
        }
    }

    public void updateWorkout(Workout workout) {
        ContentValues values = getContentValues(workout);
        Log.i(TAG, workout.getUuid().toString());
        mDatabase.update(WorkoutTable.NAME, values, WorkoutTable.Cols.UUID + "=?", new String[]{workout.getUuid().toString()});

        for (Exercise exercise : workout.getExercises()) {
            ContentValues valuesExercise = getContentValues(exercise, workout.getId());
            mDatabase.update(ExerciseTable.NAME, valuesExercise, ExerciseTable.Cols.UUID + "=?", new String[]{exercise.getId().toString()});
        }
    }

    public void deleteWorkout(Workout workout) {
        ContentValues values = getContentValues(workout);
        Log.i(TAG, workout.getUuid().toString());
        mDatabase.delete(ExerciseTable.NAME, ExerciseTable.Cols.WORKOUT_FK+"=?", new String[]{ String.valueOf(workout.getId()) });
        mDatabase.delete(WorkoutTable.NAME, WorkoutTable.Cols.UUID + "=?", new String[]{workout.getUuid().toString()});
    }

    public Workout getLatestWorkout() {
        List<Workout> workouts = new ArrayList<Workout>();
        SLCursorWrapper cursor = queryWorkouts(null, null, "date desc", "1");

        Date maxDate = null;
        //TODO query db for latest workout
        cursor.moveToFirst();
        Workout workout = cursor.getWorkout();
        int workout_Id = cursor.getInt(cursor.getColumnIndex("_id"));
        cursor.close();

        String[] whereArgs = new String[1];
        whereArgs[0] = workout.getUuid().toString();
        cursor = queryExercises(ExerciseTable.Cols.WORKOUT_FK + " = " + workout_Id, null, null, null);

        List<Exercise> exercises = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            exercises.add(cursor.getExercise());
            cursor.moveToNext();
        }
        cursor.close();
        workout.setExercises(exercises);

        return workout;
    }

    private SLCursorWrapper queryWorkouts(String whereClause, String[] whereArgs) {
        return queryWorkouts(whereClause, whereArgs, null, null);
    }

    private SLCursorWrapper queryWorkouts(String whereClause, String[] whereArgs, String orderBy, String limit) {
        Cursor cursor = mDatabase.query(
                WorkoutTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                orderBy,  // orderBy
                limit
        );
        return new SLCursorWrapper(cursor);
    }

    private SLCursorWrapper queryExercises(String whereClause, String[] whereArgs, String orderBy, String limit) {
        Cursor cursor = mDatabase.query(
                ExerciseTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                orderBy,  // orderBy
                limit
        );

        return new SLCursorWrapper(cursor);
    }

    public List<Workout> getWorkouts() {
        List<Workout> workouts = new ArrayList<>();

        SLCursorWrapper cursorWorkouts = queryWorkouts(null, null, "date", null);

        cursorWorkouts.moveToFirst();
        while (!cursorWorkouts.isAfterLast()) {
            Workout workout = cursorWorkouts.getWorkout();
            workouts.add(workout);
            SimpleDateFormat formatter = new SimpleDateFormat();
            Log.i("LiftingLab", formatter.format(workout.getDate()));
            int workout_Id = cursorWorkouts.getInt(cursorWorkouts.getColumnIndex("_id"));

            SLCursorWrapper cursorExercises = queryExercises(ExerciseTable.Cols.WORKOUT_FK + " = " + workout_Id, null, null, null);
            List<Exercise> exercises = new ArrayList<>();
            cursorExercises.moveToFirst();
            while (!cursorExercises.isAfterLast()) {
                exercises.add(cursorExercises.getExercise());
                cursorExercises.moveToNext();
            }
            cursorExercises.close();
            workout.setExercises(exercises);
            cursorWorkouts.moveToNext();
        }
        cursorWorkouts.close();


        return workouts;
    }

    public void updateExercises(Workout workout) {

    }

    public void saveExercises(Workout workout) {

    }
}

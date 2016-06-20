package com.stanislav.tabswithfragment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.stanislav.db.SLBaseHelper;

import com.stanislav.db.SLCursorWrapper;
import com.stanislav.db.SLDbSchema;
import com.stanislav.db.SLDbSchema.WorkoutTable;
import com.stanislav.db.SLDbSchema.ExerciseTable;

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
        values.put(WorkoutTable.Cols.UUID, w.getId().toString());
        values.put(WorkoutTable.Cols.DATE, w.getDate().getTime());
        values.put(WorkoutTable.Cols.BODY_WEIGHT, w.getBodyWeight());

        return values;
    }

    public void addWorkout(Workout workout) {
        ContentValues values = getContentValues(workout);
        mDatabase.insert(WorkoutTable.NAME, null, values);
    }

    public void updateWorkout(Workout workout) {

    }

    public Workout getLatestWorkout(){
        List<Workout> workouts = new ArrayList<Workout>();
        SLCursorWrapper cursor = queryWorkouts(null, null, "date desc", "1");

        Date maxDate = null;
        //TODO query db for latest workout
        cursor.moveToFirst();
        Workout workout = cursor.getWorkout();
        int workout_Id = cursor.getInt(cursor.getColumnIndex("_id"));
        cursor.close();

        String[] whereArgs = new String[1];
        whereArgs[0] = workout.getId().toString();
//        cursor = queryExercises(ExerciseTable.Cols.WORKOUT_FK+" LIKE '"+ workout.getId().toString()+"' ", null, null, null);
        cursor = queryExercises(ExerciseTable.Cols.WORKOUT_FK+" = "+workout_Id, null, null, null);
//        cursor = queryExercises(null, null, null, null);


        List<Exercise> exercises = new ArrayList<>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            exercises.add(cursor.getExercise());
            cursor.moveToNext();
        }
        cursor.close();
        workout.setExercises(exercises);

        return workout;

        /*while(!cursor.isAfterLast()) {
            workouts.add(cursor.getWorkout());

            if(maxDate == null){
                maxDate = cursor.getWorkout().getDate();
            }
            if(cursor.getWorkout().getDate().after(maxDate)){
                maxDate = cursor.getWorkout().getDate();
            }

            cursor.moveToNext();
        }
        cursor.close();

        for(Workout workout : workouts) {
            if(workout.getDate().compareTo(maxDate) == 0){
                Log.i(TAG, "found latest workout");
                SimpleDateFormat sdf  = new SimpleDateFormat();
                Log.i(TAG, "latest workout" + sdf.format(workout.getDate()));
                return workout;
            }
        }

        return null;*/
    }

    private SLCursorWrapper queryWorkouts(String whereClause, String[] whereArgs) {
        /*Cursor cursor = mDatabase.query(
                WorkoutTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null  // orderBy
        );*/
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
//        List<Workout> crimes = new ArrayList<>();
//
//        CrimeCursorWrapper cursor = queryCrimes(null, null);
//
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()) {
//            crimes.add(cursor.getCrime());
//            cursor.moveToNext();
//        }
//        cursor.close();
        List<Workout> workouts = new ArrayList<Workout>();

        return workouts;
    }

}

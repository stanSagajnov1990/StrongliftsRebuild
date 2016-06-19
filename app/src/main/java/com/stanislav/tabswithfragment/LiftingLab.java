package com.stanislav.tabswithfragment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.stanislav.db.SLBaseHelper;

import com.stanislav.db.SLCursorWrapper;
import com.stanislav.db.SLDbSchema.WorkoutTable;

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
        values.put(WorkoutTable.Cols.SQUAT_WEIGHT, w.getSquatWeight());

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

        SLCursorWrapper cursor = queryCrimes(null, null);

        Date maxDate = null;


        //TODO query db for latest workout
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
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
                return workout;
            }
        }

        return null;
    }

    private SLCursorWrapper queryCrimes(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                WorkoutTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null  // orderBy
        );

        return new SLCursorWrapper(cursor);
    }

    public List<Workout> getCrimes() {
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

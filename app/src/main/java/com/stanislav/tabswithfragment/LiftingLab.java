package com.stanislav.tabswithfragment;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.stanislav.db.CrimeBaseHelper;
import com.stanislav.db.SLDbSchema;

import com.stanislav.db.SLDbSchema.WorkoutTable;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Stanislav on 19.06.2016.
 */
public class LiftingLab {

    private static LiftingLab sLiftingLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static LiftingLab get(Context context) {
        if (sLiftingLab == null) {
            sLiftingLab = new LiftingLab(context);
        }
        return sLiftingLab;
    }

    private LiftingLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();
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

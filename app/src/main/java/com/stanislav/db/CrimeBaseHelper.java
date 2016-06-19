package com.stanislav.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.stanislav.db.SLDbSchema.WorkoutTable;
import com.stanislav.tabswithfragment.Workout;

/**
 * Created by Stanislav on 19.06.2016.
 */
public class CrimeBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "workoutBase.db";

    public CrimeBaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + WorkoutTable.NAME+ "(" +
                    " _id integer primary key autoincrement, " +
                    WorkoutTable.Cols.UUID + ", " +
                    WorkoutTable.Cols.DATE + ", " +
                    WorkoutTable.Cols.SQUAT_WEIGHT +
                    ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

package com.stanislav.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.stanislav.db.SLDbSchema.WorkoutTable;
import com.stanislav.db.SLDbSchema.ExerciseTable;

import com.stanislav.tabswithfragment.Workout;

import java.util.UUID;

/**
 * Created by Stanislav on 19.06.2016.
 */
public class SLBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "workoutBase.db";

    public SLBaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + WorkoutTable.NAME+ "(" +
                    " _id integer primary key autoincrement, " +
                    WorkoutTable.Cols.UUID + ", " +
                    WorkoutTable.Cols.DATE + ", " +
                    WorkoutTable.Cols.BODY_WEIGHT + " NUMERIC, " +
                    WorkoutTable.Cols.TYPE + ", " +
                    WorkoutTable.Cols.FAILED +
                    ")"
        );
        db.execSQL("create table " + ExerciseTable.NAME+ "(" +
                " _id integer primary key autoincrement, " +
                ExerciseTable.Cols.UUID + ", " +
                ExerciseTable.Cols.TYPE + ", " +
                ExerciseTable.Cols.WEIGHT + " NUMERIC, " +
                ExerciseTable.Cols.FIRST + ", " +
                ExerciseTable.Cols.SECOND + ", " +
                ExerciseTable.Cols.THIRD + ", " +
                ExerciseTable.Cols.FOURTH + ", " +
                ExerciseTable.Cols.FIFTH + ", " +
                ExerciseTable.Cols.WORKOUT_FK+ ", " +
                "FOREIGN KEY(" + ExerciseTable.Cols.WORKOUT_FK + ") REFERENCES workouts(uuid)"+
                ")"
        );
        UUID uuid = UUID.randomUUID();
        db.execSQL("insert into "+WorkoutTable.NAME +" values(NULL, ?, date('now'), 98, 'A', 0)", new Object[]{ uuid.toString() });
        Object[] argsForExercise = new Object[2];
        argsForExercise[0] = UUID.randomUUID().toString();
        argsForExercise[1] = 1;
        db.execSQL("insert into "+ExerciseTable.NAME+ " values(NULL, ? , 'SQ', 47.5, 5, 5, 5, 5, 5, ?)", argsForExercise);
        argsForExercise[0] = UUID.randomUUID().toString();
        argsForExercise[1] = 1;
        db.execSQL("insert into "+ExerciseTable.NAME+ " values(NULL, ? , 'BP', 77.5, 5, 5, 5, 5, 5, ?)", argsForExercise);
        argsForExercise[0] = UUID.randomUUID().toString();
        argsForExercise[1] = 1;
        db.execSQL("insert into "+ExerciseTable.NAME+ " values(NULL, ? , 'ROW', 70, 5, 5, 5, 5, 5, ?)", argsForExercise);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

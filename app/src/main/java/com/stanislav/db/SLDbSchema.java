package com.stanislav.db;

/**
 * Created by Stanislav on 19.06.2016.
 */
public class SLDbSchema {

    public static final class WorkoutTable {
        public static final String NAME = "workouts";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String DATE = "date";
            public static final String SQUAT_WEIGHT = "sq_weight";
        }

    }

}

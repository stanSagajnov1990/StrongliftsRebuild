package com.stanislav.db;

/**
 * Created by Stanislav on 19.06.2016.
 */
public class SLDbSchema {

    public static final class WorkoutTable {
        public static final String NAME = "workouts";

        public static final class Cols {
            public static final String _id = "_id";
            public static final String UUID = "uuid";
            public static final String DATE = "date";
            public static final String BODY_WEIGHT = "body_weight";
            public static final String FAILED = "failed";
            public static final String TYPE = "type";
        }
    }

    public static final class ExerciseTable {
        public static final String NAME = "exercise";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TYPE = "type";
            public static final String WEIGHT = "weight";
            public static final String FIRST = "first";
            public static final String SECOND = "second";
            public static final String THIRD = "third";
            public static final String FOURTH = "fourth";
            public static final String FIFTH = "fifth";
            public static final String WORKOUT_FK = "workouts_id";
        }
    }

}

package com.stanislav.tabswithfragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Stanislav on 19.06.2016.
 */
public class Workout {

    private int id;
    private UUID mUuId;
    private Date mDate;
    private String mBodyWeight;
    private List<Exercise> mExercises = new ArrayList<>();

    public Workout() {
        this(UUID.randomUUID());
    }

    public Workout(UUID id) {
        mUuId = id;
        mDate = new Date();
    }

    public int getId() {
        return this.id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public UUID getUuid() {
        return mUuId;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public String getBodyWeight() {
        return mBodyWeight;
    }

    public void setBodyWeight(String bodyWeight) {
        mBodyWeight = bodyWeight;
    }

    public List<Exercise> getExercises() {
        return mExercises;
    }

    public void setExercises(List<Exercise> exercises) {
        mExercises = exercises;
    }

    public void addExercise(Exercise exercise) {
        mExercises.add(exercise);
    }
}

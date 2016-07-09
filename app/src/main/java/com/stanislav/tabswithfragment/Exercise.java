package com.stanislav.tabswithfragment;

import java.util.UUID;

/**
 * Created by Stanislav on 19.06.2016.
 */
public class Exercise {

    private UUID mId;
    private String mType;
    private Double mWeight;
    private Integer first;
    private Integer second;
    private Integer third;
    private Integer fourth;
    private Integer fifth;
    private Integer workout_id;

    public static final String SQUAT = "SQ";
    public static final String BENCH_PRESS = "BP";
    public static final String BARBELL_ROW = "ROW";
    public static final String OVERHEAD_PRESS = "OHP";
    public static final String DL = "DL";

    public Exercise(){
        this(UUID.randomUUID());
    }

    public Exercise(UUID uuid){
        this.mId = uuid;
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public Double getWeight() {
        return mWeight;
    }

    public void setWeight(Double weight) {
        mWeight = weight;
    }

    public Integer getFirst() {
        return first;
    }

    public void setFirst(Integer first) {
        this.first = first;
    }

    public Integer getSecond() {
        return second;
    }

    public void setSecond(Integer second) {
        this.second = second;
    }

    public Integer getThird() {
        return third;
    }

    public void setThird(Integer third) {
        this.third = third;
    }

    public Integer getFourth() {
        return fourth;
    }

    public void setFourth(Integer fourth) {
        this.fourth = fourth;
    }

    public Integer getFifth() {
        return fifth;
    }

    public void setFifth(Integer fifth) {
        this.fifth = fifth;
    }

    public Integer getWorkout_id() {
        return workout_id;
    }

    public void setWorkout_id(Integer workout_id) {
        this.workout_id = workout_id;
    }
}

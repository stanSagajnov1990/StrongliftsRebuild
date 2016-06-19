package com.stanislav.tabswithfragment;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Stanislav on 19.06.2016.
 */
public class Workout {

    private UUID mId;
    private Date mDate;
    private String mSquatWeight;

    public Workout() { this(UUID.randomUUID());}

    public Workout(UUID id) {
        mId = id;
        mDate = new Date();
    }

    public UUID getId() { return mId; }

    public Date getDate() { return mDate; }

    public void setDate(Date date) { mDate = date; }

    public String getSquatWeight() { return mSquatWeight; }

    public void setSquatWeight(String squatWeight) { mSquatWeight = squatWeight; }

}

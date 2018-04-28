package com.cs646.pwang.stravaplus.chart;

import com.sweetzpot.stravazpot.activity.model.Activity;
import com.sweetzpot.stravazpot.activity.model.ActivityType;

public class AverageRunHeartRateDataType {

    public ActivityType getType() {
        return ActivityType.RUN;
    }

    public float getData(Activity activity) {
        return activity.getAverageHeartRate();
    }
}

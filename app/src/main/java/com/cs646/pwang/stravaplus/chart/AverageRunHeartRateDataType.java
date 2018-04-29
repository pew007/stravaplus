package com.cs646.pwang.stravaplus.chart;

import com.sweetzpot.stravazpot.activity.model.Activity;
import com.sweetzpot.stravazpot.activity.model.ActivityType;

import java.util.List;

public class AverageRunHeartRateDataType extends AbstractChartDataType {

    public float getData(Activity activity) {
        return activity.getAverageHeartRate();
    }

    @Override
    public List<ActivityType> getActivityTypes() {
        return getRunType();
    }
}

package com.cs646.pwang.stravaplus.chart;

import com.sweetzpot.stravazpot.activity.model.Activity;
import com.sweetzpot.stravazpot.activity.model.ActivityType;

import java.util.Arrays;
import java.util.List;

public class AverageRideHeartRateDataType extends AbstractChartDataType {
    @Override
    public float getData(Activity activity) {
        return activity.getAverageHeartRate();
    }

    @Override
    public List<ActivityType> getActivityTypes() {
        return Arrays.asList(ActivityType.RIDE, ActivityType.VIRTUAL_RIDE, ActivityType.EBIKE_RIDE);
    }
}

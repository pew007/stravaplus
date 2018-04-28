package com.cs646.pwang.stravaplus.chart;

import com.sweetzpot.stravazpot.activity.model.Activity;
import com.sweetzpot.stravazpot.activity.model.ActivityType;

import java.util.Arrays;
import java.util.List;

public class AverageRidePowerDataType extends AbstractChartDataType {
    @Override
    public List<ActivityType> getActivityTypes() {
        return Arrays.asList(ActivityType.RIDE, ActivityType.EBIKE_RIDE, ActivityType.VIRTUAL_RIDE);
    }

    @Override
    public float getData(Activity activity) {
        return activity.getAverageWatts();
    }
}

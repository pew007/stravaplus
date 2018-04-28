package com.cs646.pwang.stravaplus.chart;

import com.cs646.pwang.stravaplus.util.DataTransformer;
import com.sweetzpot.stravazpot.activity.model.Activity;
import com.sweetzpot.stravazpot.activity.model.ActivityType;

import java.util.Collections;
import java.util.List;

public class AverageRunSpeedDataType extends AbstractChartDataType {
    @Override
    public List<ActivityType> getActivityTypes() {
        return Collections.singletonList(ActivityType.RUN);
    }

    @Override
    public float getData(Activity activity) {
        return (float) DataTransformer.speedToMilesPerHour(activity.getAverageSpeed());
    }
}

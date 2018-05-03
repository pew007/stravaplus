package com.cs646.pwang.stravaplus.chart.datatype;

import com.cs646.pwang.stravaplus.util.DataTransformer;
import com.sweetzpot.stravazpot.activity.model.Activity;
import com.sweetzpot.stravazpot.activity.model.ActivityType;

import java.util.List;

public class AverageRunSpeedDataType extends AbstractChartDataType {
    @Override
    public List<ActivityType> getActivityTypes() {
        return getRunType();
    }

    @Override
    public String getDescription() {
        return "Average Run Speed";
    }

    @Override
    public float getData(Activity activity) {
        return (float) DataTransformer.speedToMilesPerHour(activity.getAverageSpeed());
    }
}
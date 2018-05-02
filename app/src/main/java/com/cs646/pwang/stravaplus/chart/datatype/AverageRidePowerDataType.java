package com.cs646.pwang.stravaplus.chart.datatype;

import com.sweetzpot.stravazpot.activity.model.Activity;
import com.sweetzpot.stravazpot.activity.model.ActivityType;

import java.util.List;

public class AverageRidePowerDataType extends AbstractChartDataType {
    @Override
    public List<ActivityType> getActivityTypes() {
        return getRideType();    }

    @Override
    public float getData(Activity activity) {
        return activity.getAverageWatts();
    }

    @Override
    public String getDescription() {
        return "Average Ride Power";
    }
}

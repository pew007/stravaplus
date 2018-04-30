package com.cs646.pwang.stravaplus.chart.datatype;

import com.cs646.pwang.stravaplus.util.DataTransformer;
import com.github.mikephil.charting.components.Description;
import com.sweetzpot.stravazpot.activity.model.Activity;
import com.sweetzpot.stravazpot.activity.model.ActivityType;

import java.util.List;

public class AverageRideSpeedDataType extends AbstractChartDataType {
    @Override
    public List<ActivityType> getActivityTypes() {
        return getRideType();    }

    @Override
    public Description getChartDescription() {
        Description description = new Description();
        description.setText("Average Ride Speed");
        return description;
    }

    @Override
    public float getData(Activity activity) {
        return (float) DataTransformer.speedToMilesPerHour(activity.getAverageSpeed());
    }
}

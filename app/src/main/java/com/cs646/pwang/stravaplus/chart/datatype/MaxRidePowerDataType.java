package com.cs646.pwang.stravaplus.chart.datatype;

import com.sweetzpot.stravazpot.activity.model.Activity;
import com.sweetzpot.stravazpot.activity.model.ActivityType;

import java.util.List;
import java.util.Locale;

public class MaxRidePowerDataType extends AbstractChartDataType {
    @Override
    public List<ActivityType> getActivityTypes() {
        return getRideType();
    }

    @Override
    public float getData(Activity activity) {
        return activity.getMaxWatts();
    }

    @Override
    public String getDescription() {
        return "Max Cycling Power";
    }

    @Override
    public String formatDisplayData(float value) {
        return String.format(Locale.US, "%.0fW", value);
    }
}

package com.cs646.pwang.stravaplus.chart.datatype;

import com.sweetzpot.stravazpot.activity.model.Activity;
import com.sweetzpot.stravazpot.activity.model.ActivityType;

import java.util.List;
import java.util.Locale;

public class AverageRunHeartRateDataType extends AbstractChartDataType {

    public float getData(Activity activity) {
        return activity.getAverageHeartRate();
    }

    @Override
    public String getDescription() {
        return "Average Run Heart Rate";
    }

    @Override
    public String formatDisplayData(float value) {
        return String.format(Locale.US, "%.0f bpm", value);
    }

    @Override
    public List<ActivityType> getActivityTypes() {
        return getRunType();
    }
}

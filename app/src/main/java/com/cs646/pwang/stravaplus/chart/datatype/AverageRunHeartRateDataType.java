package com.cs646.pwang.stravaplus.chart.datatype;

import com.github.mikephil.charting.components.Description;
import com.sweetzpot.stravazpot.activity.model.Activity;
import com.sweetzpot.stravazpot.activity.model.ActivityType;

import java.util.List;

public class AverageRunHeartRateDataType extends AbstractChartDataType {

    public float getData(Activity activity) {
        return activity.getAverageHeartRate();
    }

    @Override
    public Description getChartDescription() {
        Description description = new Description();
        description.setText("Average Run Heart Rate");
        return description;
    }

    @Override
    public List<ActivityType> getActivityTypes() {
        return getRunType();
    }
}

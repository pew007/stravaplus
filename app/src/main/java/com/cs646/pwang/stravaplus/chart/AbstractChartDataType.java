package com.cs646.pwang.stravaplus.chart;

import com.sweetzpot.stravazpot.activity.model.Activity;
import com.sweetzpot.stravazpot.activity.model.ActivityType;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractChartDataType implements Serializable {

    public abstract List<ActivityType> getActivityTypes();

    public abstract float getData(Activity activity);
}

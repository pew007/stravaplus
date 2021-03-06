package com.cs646.pwang.stravaplus.chart.datatype;

import com.sweetzpot.stravazpot.activity.model.Activity;
import com.sweetzpot.stravazpot.activity.model.ActivityType;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class AbstractChartDataType implements Serializable {

    public abstract List<ActivityType> getActivityTypes();

    public abstract float getData(Activity activity);

    public abstract String getDescription();

    public abstract String formatDisplayData(float value);

    List<ActivityType> getRideType() {
        return Arrays.asList(ActivityType.RIDE, ActivityType.VIRTUAL_RIDE, ActivityType.EBIKE_RIDE);
    }

    List<ActivityType> getRunType() {
        return Collections.singletonList(ActivityType.RUN);
    }
}

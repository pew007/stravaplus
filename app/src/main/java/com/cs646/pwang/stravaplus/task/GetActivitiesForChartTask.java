package com.cs646.pwang.stravaplus.task;

import android.os.AsyncTask;

import com.cs646.pwang.stravaplus.StravaConfiguration;
import com.cs646.pwang.stravaplus.fragment.ChartFragment;
import com.sweetzpot.stravazpot.activity.model.Activity;
import com.sweetzpot.stravazpot.common.api.StravaConfig;

import java.util.Date;
import java.util.List;

public class GetActivitiesForChartTask extends AsyncTask<Date, Void, List<Activity>> {

    private ChartFragment mChartFragment;

    @Override
    protected List<Activity> doInBackground(Date... dates) {
        Date startDate = dates[0];
        Date endDate = dates[1];

        StravaConfig config = StravaConfiguration.getInstance().getConfig();

        return null;
    }

    @Override
    protected void onPostExecute(List<Activity> activities) {
        mChartFragment.displayChart(activities);
    }
}

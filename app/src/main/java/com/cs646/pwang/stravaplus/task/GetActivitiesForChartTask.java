package com.cs646.pwang.stravaplus.task;

import android.os.AsyncTask;

import com.cs646.pwang.stravaplus.StravaConfiguration;
import com.cs646.pwang.stravaplus.fragment.ChartFragment;
import com.sweetzpot.stravazpot.activity.api.ActivityAPI;
import com.sweetzpot.stravazpot.activity.model.Activity;
import com.sweetzpot.stravazpot.common.api.StravaConfig;
import com.sweetzpot.stravazpot.common.model.Time;

import java.util.List;

public class GetActivitiesForChartTask extends AsyncTask<Time, Void, List<Activity>> {

    private ChartFragment mChartFragment;

    public GetActivitiesForChartTask(ChartFragment chartFragment) {
        mChartFragment = chartFragment;
    }

    @Override
    protected List<Activity> doInBackground(Time... times) {
        Time startDate = times[0];
        Time endDate = times[1];

        StravaConfig config = StravaConfiguration.getInstance().getConfig();
        ActivityAPI activityAPI = new ActivityAPI(config);
        return activityAPI.listMyActivities()
                .perPage(30)
//                .before(endDate)
//                .after(startDate)
                .execute();
        }

    @Override
    protected void onPostExecute(List<Activity> activities) {
        mChartFragment.displayChart(activities);
    }
}

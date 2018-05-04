package com.cs646.pwang.stravaplus.task;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.cs646.pwang.stravaplus.StravaConfiguration;
import com.cs646.pwang.stravaplus.fragment.ChartFragment;
import com.sweetzpot.stravazpot.activity.api.ActivityAPI;
import com.sweetzpot.stravazpot.activity.model.Activity;
import com.sweetzpot.stravazpot.common.api.StravaConfig;
import com.sweetzpot.stravazpot.common.model.Time;

import java.util.Collections;
import java.util.List;

public class GetActivitiesForChartTask extends AsyncTask<Time, Void, List<Activity>> {

    private ChartFragment mChartFragment;
    private ProgressDialog mProgressDialog;

    public GetActivitiesForChartTask(ChartFragment chartFragment) {
        mChartFragment = chartFragment;
    }

    @Override
    protected void onPreExecute() {
        mProgressDialog = new ProgressDialog(mChartFragment.getContext());
        mProgressDialog.setMessage("Loading activities");
        mProgressDialog.show();
    }

    @Override
    protected List<Activity> doInBackground(Time... times) {
        Time startDate = times[0];
        Time endDate = times[1];

        StravaConfig config = StravaConfiguration.getInstance().getConfig();
        ActivityAPI activityAPI = new ActivityAPI(config);

        return activityAPI.listMyActivities()
                .perPage(100)
                .before(endDate)
                .after(startDate)
                .execute();
    }

    @Override
    protected void onPostExecute(List<Activity> activities) {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }

        Collections.reverse(activities);
        mChartFragment.displayChart(activities);
    }
}

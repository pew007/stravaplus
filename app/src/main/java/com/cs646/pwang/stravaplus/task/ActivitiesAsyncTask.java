package com.cs646.pwang.stravaplus.task;

import android.os.AsyncTask;

import com.sweetzpot.stravazpot.activity.api.ActivityAPI;
import com.sweetzpot.stravazpot.activity.model.Activity;
import com.sweetzpot.stravazpot.common.api.StravaConfig;

import java.util.List;

public class ActivitiesAsyncTask extends AsyncTask {

    private StravaConfig stravaConfig;

    public ActivitiesAsyncTask(StravaConfig stravaConfig) {
        this.stravaConfig = stravaConfig;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        ActivityAPI activityAPI = new ActivityAPI(this.stravaConfig);
        List<Activity> activities = activityAPI.listMyActivities().execute();
        return activities;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }
}

package com.cs646.pwang.stravaplus.task;

import android.os.AsyncTask;

import com.cs646.pwang.stravaplus.fragment.ActivitiesFragment;
import com.sweetzpot.stravazpot.activity.api.ActivityAPI;
import com.sweetzpot.stravazpot.activity.model.Activity;
import com.sweetzpot.stravazpot.common.api.StravaConfig;

import java.util.List;

public class ActivitiesAsyncTask extends AsyncTask<String, Void, List<Activity>> {

    private ActivitiesFragment mActivitiesFragment;

    public ActivitiesAsyncTask(ActivitiesFragment activitiesFragment) {
        mActivitiesFragment = activitiesFragment;
    }

    @Override
    protected List<Activity> doInBackground(String... strings) {
        String authToken = strings[0];
        StravaConfig config = StravaConfig.withToken(authToken).build();
        ActivityAPI activityAPI = new ActivityAPI(config);
        return activityAPI.listMyActivities().execute();
    }

    @Override
    protected void onPostExecute(List<Activity> activityList) {
        mActivitiesFragment.showActivityList(activityList);
    }
}

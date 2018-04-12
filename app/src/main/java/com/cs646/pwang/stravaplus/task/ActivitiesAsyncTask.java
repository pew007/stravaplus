package com.cs646.pwang.stravaplus.task;

import android.os.AsyncTask;

import com.sweetzpot.stravazpot.activity.api.ActivityAPI;
import com.sweetzpot.stravazpot.common.api.StravaConfig;

import java.util.List;

public class ActivitiesAsyncTask extends AsyncTask<String, Void, List> {

    @Override
    protected List doInBackground(String... strings) {
        String autoToken = strings[0];
        StravaConfig config = StravaConfig.withToken(autoToken).build();
        ActivityAPI activityAPI = new ActivityAPI(config);
        return activityAPI.listMyActivities().execute();
    }
}

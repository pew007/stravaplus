package com.cs646.pwang.stravaplus.task;

import android.os.AsyncTask;

import com.cs646.pwang.stravaplus.fragment.ActivityDetailsFragment;
import com.sweetzpot.stravazpot.activity.api.ActivityAPI;
import com.sweetzpot.stravazpot.activity.model.Activity;
import com.sweetzpot.stravazpot.common.api.StravaConfig;

public class RetrieveActivityTask extends AsyncTask<Object, Void, Activity> {

    private ActivityDetailsFragment mActivityDetailsFragment;

    public RetrieveActivityTask(ActivityDetailsFragment activityDetailsFragment) {
        mActivityDetailsFragment = activityDetailsFragment;
    }

    @Override
    protected Activity doInBackground(Object... objects) {
        String authToken = (String) objects[0];
        int activityId = (int) objects[1];
        StravaConfig config = StravaConfig.withToken(authToken).build();
        ActivityAPI activityAPI = new ActivityAPI(config);

        return activityAPI.getActivity(activityId)
                .includeAllEfforts(true)
                .execute();
    }

    @Override
    protected void onPostExecute(Activity activity) {
        mActivityDetailsFragment.showActivityDetails(activity);
    }
}

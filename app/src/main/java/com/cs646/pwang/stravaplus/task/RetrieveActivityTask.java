package com.cs646.pwang.stravaplus.task;

import android.os.AsyncTask;

import com.cs646.pwang.stravaplus.StravaConfiguration;
import com.cs646.pwang.stravaplus.fragment.ActivityDetailsFragment;
import com.sweetzpot.stravazpot.activity.api.ActivityAPI;
import com.sweetzpot.stravazpot.activity.model.Activity;
import com.sweetzpot.stravazpot.common.api.StravaConfig;

public class RetrieveActivityTask extends AsyncTask<Integer, Void, Activity> {

    private ActivityDetailsFragment mActivityDetailsFragment;

    public RetrieveActivityTask(ActivityDetailsFragment activityDetailsFragment) {
        mActivityDetailsFragment = activityDetailsFragment;
    }

    @Override
    protected Activity doInBackground(Integer... integers) {
        int activityId = integers[0];
        StravaConfig config = StravaConfiguration.getInstance().getConfig();
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

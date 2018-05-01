package com.cs646.pwang.stravaplus.task;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.cs646.pwang.stravaplus.StravaConfiguration;
import com.cs646.pwang.stravaplus.fragment.ActivitiesFragment;
import com.sweetzpot.stravazpot.activity.api.ActivityAPI;
import com.sweetzpot.stravazpot.activity.model.Activity;
import com.sweetzpot.stravazpot.common.api.StravaConfig;

import java.util.List;

public class GetActivitiesForListTask extends AsyncTask<Void, Void, List<Activity>> {

    private ActivitiesFragment mActivitiesFragment;
    private ProgressDialog mProgressDialog;

    public GetActivitiesForListTask(ActivitiesFragment activitiesFragment) {
        mActivitiesFragment = activitiesFragment;
    }

    @Override
    protected void onPreExecute() {
        mProgressDialog = new ProgressDialog(mActivitiesFragment.getContext());
        mProgressDialog.setMessage("Loading activities");
        mProgressDialog.show();
    }

    @Override
    protected List<Activity> doInBackground(Void... voids) {
        StravaConfiguration stravaConfiguration = StravaConfiguration.getInstance();

        StravaConfig config = stravaConfiguration.getConfig();
        ActivityAPI activityAPI = new ActivityAPI(config);
        return activityAPI.listMyActivities().perPage(100).execute();
    }

    @Override
    protected void onPostExecute(List<Activity> activityList) {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }

        mActivitiesFragment.showActivityList(activityList);
    }
}

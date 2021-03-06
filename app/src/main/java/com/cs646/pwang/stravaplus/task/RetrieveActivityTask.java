package com.cs646.pwang.stravaplus.task;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.cs646.pwang.stravaplus.StravaConfiguration;
import com.cs646.pwang.stravaplus.fragment.ActivityDetailsFragment;
import com.sweetzpot.stravazpot.activity.api.ActivityAPI;
import com.sweetzpot.stravazpot.activity.model.Activity;
import com.sweetzpot.stravazpot.common.api.StravaConfig;

public class RetrieveActivityTask extends AsyncTask<Integer, Void, Activity> {

    private ActivityDetailsFragment mActivityDetailsFragment;
    private ProgressDialog mProgressDialog;

    public RetrieveActivityTask(ActivityDetailsFragment activityDetailsFragment) {
        mActivityDetailsFragment = activityDetailsFragment;
    }

    @Override
    protected void onPreExecute() {
        mProgressDialog = new ProgressDialog(mActivityDetailsFragment.getContext());
        mProgressDialog.setMessage("Loading activity");
        mProgressDialog.show();
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
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }

        mActivityDetailsFragment.showActivityDetails(activity);
    }
}

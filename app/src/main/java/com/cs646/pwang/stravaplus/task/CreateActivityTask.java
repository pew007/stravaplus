package com.cs646.pwang.stravaplus.task;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.cs646.pwang.stravaplus.StravaConfiguration;
import com.cs646.pwang.stravaplus.fragment.AddActivityFragment;
import com.sweetzpot.stravazpot.activity.api.ActivityAPI;
import com.sweetzpot.stravazpot.activity.model.Activity;
import com.sweetzpot.stravazpot.activity.model.ActivityType;
import com.sweetzpot.stravazpot.common.api.StravaConfig;
import com.sweetzpot.stravazpot.common.model.Distance;
import com.sweetzpot.stravazpot.common.model.Time;

import java.util.Date;

public class CreateActivityTask extends AsyncTask<Object, Void, Activity> {

    private ProgressDialog mProgressDialog;
    private AddActivityFragment mFragment;

    public CreateActivityTask(AddActivityFragment fragment) {
        mFragment = fragment;
    }

    @Override
    protected void onPreExecute() {
        mProgressDialog = new ProgressDialog(mFragment.getContext());
        mProgressDialog.setMessage("Saving activity");
        mProgressDialog.show();
    }

    @Override
    protected Activity doInBackground(Object... objects) {

        String name = (String) objects[0];
        ActivityType type = (ActivityType) objects[1];
        Distance distance = (Distance) objects[2];
        Date startDate = (Date) objects[3];
        Time elapsed = (Time) objects[4];

        StravaConfiguration stravaConfiguration = StravaConfiguration.getInstance();

        StravaConfig config = stravaConfiguration.getConfig();
        ActivityAPI activityAPI = new ActivityAPI(config);

        return activityAPI.createActivity(name)
                .ofType(type)
                .startingOn(startDate)
                .withDistance(distance)
                .withElapsedTime(elapsed)
                .isPrivate(false)
                .withTrainer(false)
                .execute();
    }

    @Override
    protected void onPostExecute(Activity activity) {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }

        mFragment.onActivityCreated(activity);
    }
}

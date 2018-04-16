package com.cs646.pwang.stravaplus.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cs646.pwang.stravaplus.R;
import com.cs646.pwang.stravaplus.task.RetrieveActivityTask;
import com.sweetzpot.stravazpot.activity.model.Activity;

public class ActivityDetailsFragment extends Fragment {

    public ActivityDetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        int activityId = getArguments().getInt("activityId");
        String authToken = getArguments().getString("authToken");

        RetrieveActivityTask task = new RetrieveActivityTask(this);
        task.execute(authToken, activityId);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activity_details, container, false);
    }

    public void showActivityDetails(Activity activity) {

    }
}

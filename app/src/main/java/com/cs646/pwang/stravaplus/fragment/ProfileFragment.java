package com.cs646.pwang.stravaplus.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cs646.pwang.stravaplus.R;
import com.cs646.pwang.stravaplus.task.ActivitiesAsyncTask;
import com.cs646.pwang.stravaplus.task.GetAthleteTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String authToken = getArguments().getString("token");

        GetAthleteTask task = new GetAthleteTask();
        task.execute(authToken);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

}

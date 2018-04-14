package com.cs646.pwang.stravaplus.fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cs646.pwang.stravaplus.R;
import com.cs646.pwang.stravaplus.adapter.ActivityListItemAdapter;
import com.cs646.pwang.stravaplus.task.ActivitiesAsyncTask;
import com.sweetzpot.stravazpot.activity.model.Activity;

import java.util.List;

public class ActivitiesFragment extends ListFragment {

    public ActivitiesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String authToken = getArguments().getString("token");

        ActivitiesAsyncTask task = new ActivitiesAsyncTask(this);
        task.execute(authToken);

        return inflater.inflate(R.layout.fragment_activities, container, false);
    }

    public void showActivityList(List<Activity> activities) {

        ActivityListItemAdapter adapter = new ActivityListItemAdapter(
                getActivity(),
                R.layout.list_item_activity,
                activities
        );

        setListAdapter(adapter);
//        getListView().setOnItemClickListener(this);
    }

}

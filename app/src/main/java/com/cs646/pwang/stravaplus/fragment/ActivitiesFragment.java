package com.cs646.pwang.stravaplus.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.cs646.pwang.stravaplus.R;
import com.cs646.pwang.stravaplus.adapter.ActivityListItemAdapter;
import com.cs646.pwang.stravaplus.task.ActivitiesAsyncTask;
import com.sweetzpot.stravazpot.activity.model.Activity;

import java.util.List;

public class ActivitiesFragment extends ListFragment implements AdapterView.OnItemClickListener {

    private List<Activity> activities;

    public ActivitiesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_activities, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ActivitiesAsyncTask task = new ActivitiesAsyncTask(this);
        task.execute();
    }

    public void showActivityList(List<Activity> activities) {

        this.activities = activities;

        ActivityListItemAdapter adapter = new ActivityListItemAdapter(
                getActivity(),
                R.layout.list_item_activity,
                activities
        );

        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Activity activity = this.activities.get(i);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ActivityDetailsFragment detailsFragment = new ActivityDetailsFragment();
        Bundle data = new Bundle();
        data.putInt("activityId", activity.getID());
        detailsFragment.setArguments(data);
        fragmentTransaction.replace(R.id.content_fragment, detailsFragment);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.commit();
    }
}

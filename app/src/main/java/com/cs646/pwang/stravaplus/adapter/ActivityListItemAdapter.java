package com.cs646.pwang.stravaplus.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.cs646.pwang.stravaplus.R;
import com.sweetzpot.stravazpot.activity.model.Activity;
import com.sweetzpot.stravazpot.activity.model.ActivityType;
import com.sweetzpot.stravazpot.common.model.Distance;
import com.sweetzpot.stravazpot.common.model.Speed;
import com.sweetzpot.stravazpot.common.model.Time;

import java.util.Date;
import java.util.List;

public class ActivityListItemAdapter extends ArrayAdapter<Activity> {

    private Context mContext;
    private List<Activity> activityList;

    public ActivityListItemAdapter(@NonNull Context context, int resource, List<Activity> activityList) {
        super(context, resource, activityList);
        mContext = context;
        this.activityList = activityList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item_activity, parent, false);
        }

        Activity activity = activityList.get(position);
        String name = activity.getName();
        Date startDate = activity.getStartDateLocal();
        Distance distance = activity.getDistance();
        ActivityType type = activity.getType();
        Speed averageSpeed = activity.getAverageSpeed();
        Time elapsedTime = activity.getElapsedTime();


//        TextView name = listItem.findViewById(R.id.subject_name);
//        name.setText(activity.getTitle());

        return listItem;
    }
}

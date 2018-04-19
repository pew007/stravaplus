package com.cs646.pwang.stravaplus.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cs646.pwang.stravaplus.R;
import com.cs646.pwang.stravaplus.util.DisplayHelper;
import com.sweetzpot.stravazpot.activity.model.Activity;
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
        Speed averageSpeed = activity.getAverageSpeed();
        Time movingTime = activity.getMovingTime();

        TextView nameText = listItem.findViewById(R.id.activity_name);
        nameText.setText(name);

        TextView startDateText = listItem.findViewById(R.id.activity_date);
        startDateText.setText(DisplayHelper.displayActivityDate(startDate));

        TextView distanceText = listItem.findViewById(R.id.activity_distance);
        String text = "Distance " + DisplayHelper.displayActivityDistance(distance);
        distanceText.setText(text);

        TextView averageSpeedText = listItem.findViewById(R.id.activity_speed);
        text = "Speed " + DisplayHelper.displayActivityAverageSpeed(averageSpeed);
        averageSpeedText.setText(text);

        TextView movingTimeText = listItem.findViewById(R.id.activity_moving_time);
        movingTimeText.setText(DisplayHelper.displayTime(movingTime));

        return listItem;
    }
}

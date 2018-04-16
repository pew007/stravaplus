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
        Time movingTime = activity.getMovingTime();

        TextView nameText = listItem.findViewById(R.id.activity_name);
        nameText.setText(name);
        int drawable = type.name().equals("RUN") ? R.drawable.ic_run : R.drawable.ic_bike;
        nameText.setCompoundDrawablesWithIntrinsicBounds(drawable, 0, 0, 0);

        TextView startDateText = listItem.findViewById(R.id.activity_date);
        startDateText.setText(DisplayHelper.displayActivityDate(startDate));

        TextView distanceText = listItem.findViewById(R.id.activity_distance);
        distanceText.setText(DisplayHelper.displayActivityDistance(distance));

        TextView averageSpeedText = listItem.findViewById(R.id.activity_speed);
        averageSpeedText.setText(DisplayHelper.displayActivityAverageSpeed(averageSpeed));

        TextView movingTimeText = listItem.findViewById(R.id.activity_moving_time);
        movingTimeText.setText(DisplayHelper.displayElapsedTime(movingTime));

        return listItem;
    }
}

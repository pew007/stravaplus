package com.cs646.pwang.stravaplus.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cs646.pwang.stravaplus.R;
import com.cs646.pwang.stravaplus.task.RetrieveActivityTask;
import com.cs646.pwang.stravaplus.util.DataTransformer;
import com.cs646.pwang.stravaplus.util.DisplayHelper;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;
import com.sweetzpot.stravazpot.activity.model.Activity;
import com.sweetzpot.stravazpot.activity.model.ActivityType;

import java.util.List;
import java.util.Locale;

public class ActivityDetailsFragment extends Fragment implements OnMapReadyCallback {

    private Activity currentActivity;

    public ActivityDetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        int activityId = getArguments().getInt("activityId");

        RetrieveActivityTask task = new RetrieveActivityTask(this);
        task.execute(activityId);

        return inflater.inflate(R.layout.fragment_activity_details, container, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        MapFragment mapFragment = (MapFragment) getActivity().getFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            getActivity().getFragmentManager().beginTransaction().remove(mapFragment).commit();
        }
    }

    public void showActivityDetails(Activity activity) {
        this.currentActivity = activity;

        MapFragment mapFragment = (MapFragment) getActivity().getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        TextView name = getActivity().findViewById(R.id.activity_name);
        name.setText(activity.getName());

        TextView createdDate = getActivity().findViewById(R.id.activity_date);
        createdDate.setText(DisplayHelper.displayActivityDate(activity.getStartDateLocal()));

        TextView speed = getActivity().findViewById(R.id.activity_speed);
        speed.setText(DisplayHelper.displayActivityAverageSpeed(activity.getAverageSpeed()));

        TextView pace = getActivity().findViewById(R.id.activity_pace);
        pace.setText(DisplayHelper.displayActivityPace(activity.getAverageSpeed()));

        TextView calories = getActivity().findViewById(R.id.activity_calories);
        calories.setText(String.format(Locale.US, "%.0f", activity.getCalories()));

        TextView elevation = getActivity().findViewById(R.id.activity_elevation);
        elevation.setText(DisplayHelper.displayActivityElevation(activity.getTotalElevationGain()));

        TextView cadence = getActivity().findViewById(R.id.activity_cadence);
        cadence.setText(String.format(Locale.US, "%.0f", activity.getAverageCadence() * 2));

        TextView heartRate = getActivity().findViewById(R.id.activity_hr);
        heartRate.setText(String.format(Locale.US, "%.0f", activity.getAverageHeartRate()));

        TextView distance = getActivity().findViewById(R.id.activity_distance);
        distance.setText(DisplayHelper.displayActivityDistance(activity.getDistance()));

        TextView movingTime = getActivity().findViewById(R.id.activity_moving_time);
        movingTime.setText(DisplayHelper.displayTime(activity.getMovingTime()));

        ActivityType type = activity.getType();
        TextView stepsOrPower = getActivity().findViewById(R.id.activity_steps_or_power);
        TextView stepsOrPowerLabel = getActivity().findViewById(R.id.activity_steps_or_power_label);
        if (type.name().equals("RUN")) {
            stepsOrPowerLabel.setText(R.string.label_steps);
            int steps = DataTransformer.calculateSteps(activity.getAverageSpeed(), activity.getMovingTime());
            stepsOrPower.setText(String.format(Locale.US, "%d", steps));
        } else {
            stepsOrPowerLabel.setText(R.string.label_power);
            stepsOrPower.setText(String.format(Locale.US, "%.0f watts", activity.getAverageWatts()));
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        String encodedPolyline = currentActivity.getMap().getSummaryPolyline();

        if (encodedPolyline == null) {
            return;
        }

        List<LatLng> latlng = PolyUtil.decode(encodedPolyline);
        googleMap.addPolyline(new PolylineOptions().color(Color.RED)).setPoints(latlng);
    }
}

package com.cs646.pwang.stravaplus.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cs646.pwang.stravaplus.R;
import com.cs646.pwang.stravaplus.task.RetrieveActivityTask;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;
import com.sweetzpot.stravazpot.activity.model.Activity;

import java.util.List;

public class ActivityDetailsFragment extends Fragment implements OnMapReadyCallback {

    private Activity currentActivity;

    public ActivityDetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        int activityId = getArguments().getInt("activityId");
        String authToken = getArguments().getString("authToken");

        RetrieveActivityTask task = new RetrieveActivityTask(this);
        task.execute(authToken, activityId);

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
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        String encodedPolyline = currentActivity.getMap().getSummaryPolyline();

        if (encodedPolyline == null) {
            return;
        }

        List<LatLng> latlng = PolyUtil.decode(encodedPolyline);
        googleMap.addPolyline(new PolylineOptions()
                .color(Color.RED)
        ).setPoints(latlng);
    }
}

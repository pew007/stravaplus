package com.cs646.pwang.stravaplus.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cs646.pwang.stravaplus.R;
import com.github.mikephil.charting.charts.LineChart;
import com.sweetzpot.stravazpot.activity.model.Activity;

import java.util.List;

public class ChartFragment extends Fragment {

    public ChartFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chart, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        LineChart chart = getActivity().findViewById(R.id.chart);

    }

    public void displayChart(List<Activity> activities) {

    }
}

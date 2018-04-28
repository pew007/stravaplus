package com.cs646.pwang.stravaplus.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cs646.pwang.stravaplus.R;
import com.cs646.pwang.stravaplus.task.GetActivitiesForChartTask;
import com.cs646.pwang.stravaplus.util.DataTransformer;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.sweetzpot.stravazpot.activity.model.Activity;
import com.sweetzpot.stravazpot.common.model.Time;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChartFragment extends Fragment {

    public ChartFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_chart, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Date defaultStart = new Date(2018, 3, 1);
        Date defaultEnd = new Date(2018, 5, 1);
        Time start = new Time(defaultStart.getSeconds());
        Time end = new Time(defaultEnd.getSeconds());

        GetActivitiesForChartTask task = new GetActivitiesForChartTask(this);
        task.execute(start, end);
    }

    public void displayChart(List<Activity> activities) {
        LineChart chart = getActivity().findViewById(R.id.chart);

        List<Entry> entries = getEntries(activities);

        LineDataSet dataSet = new LineDataSet(entries, "Label"); // add entries to dataset
        dataSet.setColor(R.color.colorPrimary);
        dataSet.setValueTextColor(R.color.colorAccent);

        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);
        chart.invalidate();
    }

    private List<Entry> getEntries(List<Activity> activities) {
        List<Entry> entries = new ArrayList<>();

        for (int i = 0; i < activities.size(); i++) {
            Activity currentActivity = activities.get(i);
            float averageSpeed = (float) DataTransformer.speedToMilesPerHour(currentActivity.getAverageSpeed());

            entries.add(new Entry(i, averageSpeed));
        }

        return entries;
    }
}

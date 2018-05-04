package com.cs646.pwang.stravaplus.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cs646.pwang.stravaplus.R;
import com.cs646.pwang.stravaplus.chart.datatype.AbstractChartDataType;
import com.cs646.pwang.stravaplus.task.GetActivitiesForChartTask;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.sweetzpot.stravazpot.activity.model.Activity;
import com.sweetzpot.stravazpot.activity.model.ActivityType;
import com.sweetzpot.stravazpot.common.model.Time;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ChartFragment extends Fragment {

    AbstractChartDataType mChartDataType;
    Button mLastWeek;
    Button mLastMonth;
    Button mLastQuarter;

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

        mChartDataType = (AbstractChartDataType) getArguments().getSerializable("dataType");

        mLastWeek = getActivity().findViewById(R.id.last_week_button);
        mLastMonth = getActivity().findViewById(R.id.last_month_button);
        mLastQuarter = getActivity().findViewById(R.id.last_quarter_button);

        mLastWeek.setOnClickListener(event -> showLastWeek());
        mLastMonth.setOnClickListener(event -> showLastMonth());
        mLastQuarter.setOnClickListener(event -> showLastQuarter());

        showLastMonth();
    }

    private void showLastWeek() {
        Time start = getPastDateTime(7);
        Time end = getPastDateTime(0);

        getActivitiesForChart(start, end);
    }

    private void showLastMonth() {
        Time start = getPastDateTime(30);
        Time end = getPastDateTime(0);

        getActivitiesForChart(start, end);
    }

    private void showLastQuarter() {
        Time start = getPastDateTime(90);
        Time end = getPastDateTime(0);

        getActivitiesForChart(start, end);
    }

    private Time getPastDateTime(int numberOfDaysInThePast) {
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);

        calendar.add(Calendar.DAY_OF_MONTH, numberOfDaysInThePast * -1);
        Date targetDate = calendar.getTime();
        long timestamp = targetDate.getTime();

        return new Time((int) (timestamp / 1000));
    }

    private void getActivitiesForChart(Time start, Time end) {
        GetActivitiesForChartTask task = new GetActivitiesForChartTask(this);
        task.execute(start, end);
    }

    public void displayChart(List<Activity> activities) {
        TextView chartTile = getActivity().findViewById(R.id.chart_title);
        chartTile.setText(mChartDataType.getDescription());

        LineChart chart = getActivity().findViewById(R.id.chart);

        List<Entry> entries = getEntries(activities);

        LineDataSet dataSet = new LineDataSet(entries, "");
        dataSet.setColor(Color.RED);
        dataSet.setValueTextColor(Color.BLUE);
        dataSet.setValueFormatter((value, entry, dataSetIndex, viewPortHandler) -> mChartDataType.formatDisplayData(value));

        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);

        Description description = new Description();
        description.setEnabled(false);
        chart.setDescription(description);

        XAxis xAxis = chart.getXAxis();
        xAxis.setEnabled(true);
        xAxis.setPosition(XAxis.XAxisPosition.TOP_INSIDE);
        xAxis.setDrawGridLines(false);
        xAxis.setLabelRotationAngle(45);
        xAxis.setValueFormatter((value, axis) -> {
            Date date = new Date((long) (value * 1000));
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
            return sdf.format(date);
        });

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setDrawGridLines(false);

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setEnabled(false);

        Legend legend = chart.getLegend();
        legend.setEnabled(false);

        chart.invalidate();
    }

    private List<Entry> getEntries(List<Activity> activities) {
        List<Entry> entries = new ArrayList<>();

        for (int i = 0; i < activities.size(); i++) {
            Activity currentActivity = activities.get(i);

            List<ActivityType> activityTypes = mChartDataType.getActivityTypes();
            float data = mChartDataType.getData(currentActivity);
            float timestamp = (currentActivity.getStartDateLocal().getTime()) / 1000;

            if (data == 0 || !activityTypes.contains(currentActivity.getType())) {
                continue;
            }

            Entry entry = new Entry(timestamp, data);
            entry.setData(currentActivity);
            entries.add(entry);
        }

        return entries;
    }
}

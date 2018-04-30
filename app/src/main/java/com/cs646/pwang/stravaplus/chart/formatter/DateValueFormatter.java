package com.cs646.pwang.stravaplus.chart.formatter;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.sweetzpot.stravazpot.activity.model.Activity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateValueFormatter implements IValueFormatter {
    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        Activity activity = (Activity) entry.getData();
        Date startDate = activity.getStartDateLocal();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.US);

        return String.format(Locale.US, "%s:%.1f", format.format(startDate), entry.getY());
    }
}

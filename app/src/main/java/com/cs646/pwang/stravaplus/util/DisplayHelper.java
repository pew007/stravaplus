package com.cs646.pwang.stravaplus.util;

import com.sweetzpot.stravazpot.common.model.Distance;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DisplayHelper {
    public static String getDisplayActivityDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy h:mm a", Locale.US);
        return dateFormat.format(date);
    }

    public static String getDisplayActivityDistance(Distance distance) {
        double distanceInMiles = DataTransformer.distanceInMile(distance);
        return "Distance " + String.format(Locale.US, "%.2f", distanceInMiles) + " mi";
    }
}

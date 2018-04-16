package com.cs646.pwang.stravaplus.util;

import com.sweetzpot.stravazpot.activity.model.ActivityType;
import com.sweetzpot.stravazpot.common.model.Distance;
import com.sweetzpot.stravazpot.common.model.Speed;
import com.sweetzpot.stravazpot.common.model.Time;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DisplayHelper {
    public static String displayActivityDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy h:mm a", Locale.US);
        return dateFormat.format(date);
    }

    public static String displayActivityDistance(Distance distance) {
        double distanceInMiles = DataTransformer.distanceInMile(distance);
        return "Distance " + String.format(Locale.US, "%.2f", distanceInMiles) + " mi";
    }

    public static String displayActivityAverageSpeed(Speed speed) {
        double metersPerSecond = speed.getMetersPerSecond();

        double milesPerHour = DataTransformer.metersPerSecondToMilesPerHour(metersPerSecond);
        return "Speed " + String.format(Locale.US, "%.1f", milesPerHour) + " mph";
    }

    public static String displayElapsedTime(Time time) {
        int timeInSeconds = time.getSeconds();

        int hour = timeInSeconds / 60 / 60;
        int minutes = timeInSeconds / 60 % 60;

        return String.format(Locale.US, "%sh %sm", hour, minutes);
    }
}

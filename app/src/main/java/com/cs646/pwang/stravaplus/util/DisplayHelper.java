package com.cs646.pwang.stravaplus.util;

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
        return String.format(Locale.US, "%.2f mi", distanceInMiles);
    }

    public static String displayActivityAverageSpeed(Speed speed) {
        double milesPerHour = DataTransformer.speedToMilesPerHour(speed);
        return String.format(Locale.US, "%.1f mph", milesPerHour);
    }

    public static String displayActivityPace(Speed speed) {
        double metersPerSecond = speed.getMetersPerSecond();
        double secondsPerMile = (1 / metersPerSecond) * 1600;
        int minutes = (int) secondsPerMile / 60;
        int seconds = (int) secondsPerMile % 60;

        return String.format(Locale.US, "%02d:%02d /mi", minutes, seconds);
    }

    public static String displayTime(Time time) {
        int timeInSeconds = time.getSeconds();

        int hour = timeInSeconds / 60 / 60;
        int minutes = timeInSeconds / 60 % 60;

        return String.format(Locale.US, "%sh %sm", hour, minutes);
    }

    public static String displayActivityElevation(Distance elevation) {
        double elevationInMeters = elevation.getMeters();
        double elevationInFeet = elevationInMeters * 3.28084;

        return String.format(Locale.US, "%.0f ft", elevationInFeet);
    }
}

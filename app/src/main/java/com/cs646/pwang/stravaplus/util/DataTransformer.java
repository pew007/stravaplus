package com.cs646.pwang.stravaplus.util;

import com.sweetzpot.stravazpot.common.model.Distance;
import com.sweetzpot.stravazpot.common.model.Speed;
import com.sweetzpot.stravazpot.common.model.Time;

public class DataTransformer {

    public static double distanceInMile(Distance distance) {
        float meters = distance.getMeters();
        double meterToMile = 0.000621371;

        return meters * meterToMile;
    }

    public static double speedToMilesPerHour(Speed speed) {
        double metersPerSecondToMilesPerHour = 2.23694;
        double metersPerSecond = speed.getMetersPerSecond();

        return metersPerSecond * metersPerSecondToMilesPerHour;
    }

    public static int calculateSteps(Speed speed, Time time) {
        double metersPerSecond = speed.getMetersPerSecond();
        int seconds = time.getSeconds();
        return (int) metersPerSecond * seconds;
    }

    public static Distance milesToDistance(float distanceInMiles) {
        double meters = distanceInMiles * 1609.344;

        return new Distance((float) meters);
    }

    public static Time hhmmssToTime(String timeInHHMMSS) {
        String[] timeFields = timeInHHMMSS.split(":");

        int hours = Integer.parseInt(timeFields[0]);
        int minutes = Integer.parseInt(timeFields[1]);
        int seconds = Integer.parseInt(timeFields[2]);

        int timeInSeconds = hours * 3600 + minutes * 60 + seconds;

        return new Time(timeInSeconds);
    }
}

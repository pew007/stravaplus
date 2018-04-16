package com.cs646.pwang.stravaplus.util;

import com.sweetzpot.stravazpot.common.model.Distance;

public class DataTransformer {

    public static double distanceInMile(Distance distance) {
        float meters = distance.getMeters();
        double meterToMile = 0.000621371;

        return meters * meterToMile;
    }

    public static double metersPerSecondToMilesPerHour(double metersPerSecond) {
        double metersPerSecondToMilesPerHour = 2.23694;

        return metersPerSecond * metersPerSecondToMilesPerHour;
    }
}

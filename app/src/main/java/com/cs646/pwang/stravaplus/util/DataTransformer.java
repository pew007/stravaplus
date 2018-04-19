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
        return  (int) metersPerSecond * seconds;
    }
}

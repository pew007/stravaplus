package com.cs646.pwang.stravaplus.chart;

public class ChartDataTypeFactory {

    public static AbstractChartDataType getDataType(String option) {

        switch (option) {
            case "Average Running Heart Rate":
                return new AverageRunHeartRateDataType();
            case "Average Cycling Heart Rate":
                return new AverageRideHeartRateDataType();
            case "Average Running Speed":
                return new AverageRunSpeedDataType();
            case "Average Cycling Speed":
                return new AverageRideSpeedDataType();
            case "Average Cycling Power":
                return new AverageRidePowerDataType();
        }

        return null;
    }
}

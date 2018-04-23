package com.cs646.pwang.stravaplus;

import com.sweetzpot.stravazpot.common.api.StravaConfig;

public class StravaConfiguration {

    private StravaConfig config;
    private static volatile StravaConfiguration sStravaConfiguration;

    private StravaConfiguration() {
        if (sStravaConfiguration != null) {
            throw new RuntimeException("Use getInstance() method to get the single instance of StraveConfiguration");
        }
    }

    public static StravaConfiguration getInstance() {
        if (sStravaConfiguration == null) {
            synchronized (StravaConfiguration.class) {
                if (sStravaConfiguration == null) {
                    sStravaConfiguration = new StravaConfiguration();
                }
            }
        }

        return sStravaConfiguration;
    }

    public StravaConfig getConfig() {
        return config;
    }

    public void setConfig(StravaConfig config) {
        this.config = config;
    }
}

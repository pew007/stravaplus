package com.cs646.pwang.stravaplus.task;

import android.os.AsyncTask;

import com.cs646.pwang.stravaplus.StravaConfiguration;
import com.sweetzpot.stravazpot.athlete.api.AthleteAPI;
import com.sweetzpot.stravazpot.athlete.model.Athlete;
import com.sweetzpot.stravazpot.common.api.StravaConfig;

public class GetAthleteTask extends AsyncTask<Void, Void, Athlete> {
    @Override
    protected Athlete doInBackground(Void... voids) {
        StravaConfig config = StravaConfiguration.getInstance().getConfig();
        AthleteAPI athleteAPI = new AthleteAPI(config);

        return athleteAPI.retrieveCurrentAthlete().execute();
    }

    @Override
    protected void onPostExecute(Athlete athlete) {
        super.onPostExecute(athlete);
    }
}

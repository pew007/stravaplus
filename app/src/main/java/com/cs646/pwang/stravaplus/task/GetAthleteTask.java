package com.cs646.pwang.stravaplus.task;

import android.os.AsyncTask;

import com.sweetzpot.stravazpot.athlete.api.AthleteAPI;
import com.sweetzpot.stravazpot.athlete.model.Athlete;
import com.sweetzpot.stravazpot.common.api.StravaConfig;

public class GetAthleteTask extends AsyncTask<String, Void, Athlete> {
    @Override
    protected Athlete doInBackground(String... strings) {
        String authToken = strings[0];
        StravaConfig config = StravaConfig.withToken(authToken).build();
        AthleteAPI athleteAPI = new AthleteAPI(config);

        return athleteAPI.retrieveCurrentAthlete().execute();
    }

    @Override
    protected void onPostExecute(Athlete athlete) {
        super.onPostExecute(athlete);
    }
}

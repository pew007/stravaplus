package com.cs646.pwang.stravaplus.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.cs646.pwang.stravaplus.R;
import com.sweetzpot.stravazpot.athlete.api.AthleteAPI;
import com.sweetzpot.stravazpot.athlete.model.Athlete;
import com.sweetzpot.stravazpot.common.api.StravaConfig;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPref = getSharedPreferences("app", Context.MODE_PRIVATE);
        String key = getString(R.string.token_key);
        String authToken = sharedPref.getString(key, "");

        if (authToken.equals("")) {
            Intent authIntent = new Intent(this, AuthenticationActivity.class);
            startActivity(authIntent);
        } else {
            Thread thread = new Thread(() -> {
                StravaConfig config = StravaConfig.withToken(authToken).build();
                AthleteAPI athleteAPI = new AthleteAPI(config);
                Athlete athlete = athleteAPI.retrieveCurrentAthlete().execute();
                athlete.getCity();
                Log.i("pw", "ok");
            });
            thread.start();
        }
    }
}

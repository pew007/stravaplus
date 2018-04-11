package com.cs646.pwang.stravaplus.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;

import com.cs646.pwang.stravaplus.R;
import com.cs646.pwang.stravaplus.task.ActivitiesAsyncTask;
import com.sweetzpot.stravazpot.common.api.StravaConfig;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

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
            StravaConfig config = StravaConfig.withToken(authToken).build();
            new ActivitiesAsyncTask(config).execute();
        }

        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            // set item as selected to persist highlight
            menuItem.setChecked(true);
            // close drawer when item is tapped
            mDrawerLayout.closeDrawers();

            // Add code here to update the UI based on the item selected
            // For example, swap UI fragments here

            return true;
        });
    }
}

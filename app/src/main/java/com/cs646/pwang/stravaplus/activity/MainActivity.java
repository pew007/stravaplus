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
            Intent authIntent = new Intent(this, LoginActivity.class);
            startActivity(authIntent);
        } else {
            ActivitiesAsyncTask task = new ActivitiesAsyncTask();
            task.execute(authToken);
        }

        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            menuItem.setChecked(true);
            mDrawerLayout.closeDrawers();

            return true;
        });
    }
}

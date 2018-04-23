package com.cs646.pwang.stravaplus.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.cs646.pwang.stravaplus.R;
import com.cs646.pwang.stravaplus.StravaConfiguration;
import com.cs646.pwang.stravaplus.fragment.ActivitiesFragment;
import com.cs646.pwang.stravaplus.fragment.ProfileFragment;
import com.cs646.pwang.stravaplus.fragment.StatsFragment;
import com.sweetzpot.stravazpot.common.api.StravaConfig;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupActionBar();
        setupNavigationDrawer();

        SharedPreferences sharedPref = getSharedPreferences("app", Context.MODE_PRIVATE);
        String key = getString(R.string.token_key);
        String authToken = sharedPref.getString(key, "");

        if (authToken.equals("")) {
            Intent login = new Intent(this, LoginActivity.class);
            startActivity(login);
        } else {
            StravaConfig config = StravaConfig.withToken(authToken).build();
            StravaConfiguration stravaConfiguration = StravaConfiguration.getInstance();
            stravaConfiguration.setConfig(config);

            goToActivitiesFragment();
        }
    }

    private void setupActionBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
    }

    private void setupNavigationDrawer() {
        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            menuItem.setChecked(true);
            mDrawerLayout.closeDrawers();

            switch (menuItem.getItemId()) {
                case R.id.nav_activities:
                    goToActivitiesFragment();
                    return true;
                case R.id.nav_stats:
                    goToStatsFragment();
                    return true;
                case R.id.nav_profile:
                    goToProfileFragment();
                    return true;
                case R.id.nav_logout:
                    SharedPreferences sharedPref = getSharedPreferences("app", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("token", "");
                    editor.apply();

                    finish();
                    startActivity(getIntent());
                    return true;
            }

            return true;
        });
    }

    private void goToActivitiesFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ActivitiesFragment activitiesFragment = new ActivitiesFragment();

        fragmentTransaction.replace(R.id.content_fragment, activitiesFragment);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.commit();
    }

    private void goToStatsFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        StatsFragment statsFragment = new StatsFragment();

        fragmentTransaction.replace(R.id.content_fragment, statsFragment);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.commit();
    }

    private void goToProfileFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ProfileFragment profileFragment = new ProfileFragment();

        fragmentTransaction.replace(R.id.content_fragment, profileFragment);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

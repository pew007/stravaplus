package com.cs646.pwang.stravaplus.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.cs646.pwang.stravaplus.R;
import com.cs646.pwang.stravaplus.StravaConfiguration;
import com.cs646.pwang.stravaplus.fragment.ActivitiesFragment;
import com.cs646.pwang.stravaplus.fragment.AddActivityFragment;
import com.cs646.pwang.stravaplus.fragment.PerformanceFragment;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_view, menu);
        return true;
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
                case R.id.nav_performance:
                    goToPerformanceFragment();
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
        ActivitiesFragment activitiesFragment = new ActivitiesFragment();
        goToFragment(activitiesFragment);
    }

    private void goToPerformanceFragment() {
        PerformanceFragment performanceFragment = new PerformanceFragment();
        goToFragment(performanceFragment);
    }

    private void goToAddActivityFragment() {
        AddActivityFragment fragment = new AddActivityFragment();
        goToFragment(fragment);
    }

    private void goToFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.content_fragment, fragment);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_add_activity:
                goToAddActivityFragment();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

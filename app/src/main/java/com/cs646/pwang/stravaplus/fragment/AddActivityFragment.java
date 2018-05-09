package com.cs646.pwang.stravaplus.fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cs646.pwang.stravaplus.R;
import com.cs646.pwang.stravaplus.task.CreateActivityTask;
import com.cs646.pwang.stravaplus.util.DataTransformer;
import com.sweetzpot.stravazpot.activity.model.Activity;
import com.sweetzpot.stravazpot.activity.model.ActivityType;
import com.sweetzpot.stravazpot.common.model.Distance;
import com.sweetzpot.stravazpot.common.model.Time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddActivityFragment extends Fragment {

    private RadioGroup mActivityTypeGroup;
    private TextView mElapsedTime;
    private TextView mDistance;
    private TextView mName;
    private Button mStartDate;
    private Button mStartTime;

    final static ActivityType[] types = {ActivityType.RUN, ActivityType.RIDE};

    public AddActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_add_activity, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mActivityTypeGroup = getActivity().findViewById(R.id.activity_type);
        mElapsedTime = getActivity().findViewById(R.id.activity_elapsed);
        mDistance = getActivity().findViewById(R.id.activity_distance);
        mName = getActivity().findViewById(R.id.activity_name);
        mStartDate = getActivity().findViewById(R.id.activity_start_date);
        mStartDate.setOnClickListener(event -> {
            Calendar calendar = Calendar.getInstance();
            int currentYear = calendar.get(Calendar.YEAR);
            int currentMonth = calendar.get(Calendar.MONTH);
            int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog.OnDateSetListener listener = (datePicker, year, month, dayOfMonth) -> {
                String dateString = String.format(Locale.US, "%02d/%02d/%04d", month + 1, dayOfMonth, year);
                mStartDate.setText(dateString);
            };

            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), listener, currentYear, currentMonth, currentDay);
            datePickerDialog.show();
        });

        mStartTime = getActivity().findViewById(R.id.activity_start_time);
        mStartTime.setOnClickListener(event -> {
            Calendar calendar = Calendar.getInstance();
            int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
            int currentMinute = calendar.get(Calendar.MINUTE);

            TimePickerDialog.OnTimeSetListener listener = (timePicker, hourOfDay, minute) -> {
                String timeString = String.format(Locale.US, "%02d:%02d", hourOfDay, minute);
                mStartTime.setText(timeString);
            };

            TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), listener, currentHour, currentMinute, false);
            timePickerDialog.show();
        });

        Button saveButton = getActivity().findViewById(R.id.save_activity_button);
        saveButton.setOnClickListener(event -> {

            boolean isValid = validateActivityElapsedTime();

            if (isValid) {
                saveActivity();
            } else {
                Toast toast = Toast.makeText(getContext(), "Invalid activity elapsed time", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }

    private boolean validateActivityElapsedTime() {
        String elapsedTimeString = mElapsedTime.getText().toString();
        String[] timeFields = elapsedTimeString.split(":");

        if (timeFields.length < 3) {
            return false;
        }

        int hours = Integer.parseInt(timeFields[0]);
        int minutes = Integer.parseInt(timeFields[1]);
        int seconds = Integer.parseInt(timeFields[2]);

        return isValidHour(hours) && isValidMinuteOrSecond(minutes) && isValidMinuteOrSecond(seconds);
    }

    private boolean isValidHour(int value) {
        return value >= 0;
    }

    private boolean isValidMinuteOrSecond(int value) {
        return value >= 0 && value < 60;
    }

    private void saveActivity() {
        String activityName = mName.getText().toString();

        int radioButtonId = mActivityTypeGroup.getCheckedRadioButtonId();
        RadioButton button = getActivity().findViewById(radioButtonId);
        int index = mActivityTypeGroup.indexOfChild(button);
        ActivityType activityType = types[index];

        float distanceInMiles = Float.valueOf(mDistance.getText().toString());
        Distance distance = DataTransformer.milesToDistance(distanceInMiles);

        String dateString = String.format(
                Locale.US,
                "%s %s",
                mStartDate.getText().toString(),
                mStartTime.getText().toString()
        );
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm", Locale.US);
        try {
            Date startDateTime = sdf.parse(dateString);

            String elapsedTimeString = mElapsedTime.getText().toString();
            Time elapsedTime = DataTransformer.hhmmssToTime(elapsedTimeString);

            CreateActivityTask task = new CreateActivityTask(this);
            task.execute(activityName, activityType, distance, startDateTime, elapsedTime);
        } catch (Exception exception) {
            Log.e("pw", "error parsing date");
        }
    }

    public void onActivityCreated(Activity activity) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ActivityDetailsFragment fragment = new ActivityDetailsFragment();

        Bundle data = new Bundle();
        data.putInt(getString(R.string.key_activity_id), activity.getID());
        fragment.setArguments(data);

        fragmentTransaction.replace(R.id.content_fragment, fragment);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.commit();
    }
}

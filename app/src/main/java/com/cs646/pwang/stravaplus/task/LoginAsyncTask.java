package com.cs646.pwang.stravaplus.task;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.cs646.pwang.stravaplus.R;
import com.cs646.pwang.stravaplus.activity.LoginActivity;
import com.sweetzpot.stravazpot.authenticaton.api.AuthenticationAPI;
import com.sweetzpot.stravazpot.authenticaton.model.AppCredentials;
import com.sweetzpot.stravazpot.authenticaton.model.LoginResult;
import com.sweetzpot.stravazpot.common.api.AuthenticationConfig;

public class LoginAsyncTask extends AsyncTask<String, Void, String> {

    private static final int CLIENT_ID = 9353;
    private static final String CLIENT_SECRET = "adf9e2e21ce5de147b7816cb82103455aacb18e8";

    private LoginActivity context;

    public LoginAsyncTask(LoginActivity context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        String code = strings[0];
        AuthenticationConfig config = AuthenticationConfig.create()
                .debug()
                .build();
        AuthenticationAPI api = new AuthenticationAPI(config);
        LoginResult result = api.getTokenForApp(AppCredentials.with(CLIENT_ID, CLIENT_SECRET))
                .withCode(code)
                .execute();

        return result.getToken().toString();
    }

    @Override
    protected void onPostExecute(String token) {
        saveToken(token);
        this.context.finish();
    }

    private void saveToken(String token) {
        SharedPreferences sharedPref = this.context.getSharedPreferences("app", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        String key = this.context.getString(R.string.token_key);
        editor.putString(key, token);
        editor.apply();
    }
}

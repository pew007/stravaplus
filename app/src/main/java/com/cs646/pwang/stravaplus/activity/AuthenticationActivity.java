package com.cs646.pwang.stravaplus.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;

import com.cs646.pwang.stravaplus.R;
import com.sweetzpot.stravazpot.authenticaton.api.AccessScope;
import com.sweetzpot.stravazpot.authenticaton.api.ApprovalPrompt;
import com.sweetzpot.stravazpot.authenticaton.api.AuthenticationAPI;
import com.sweetzpot.stravazpot.authenticaton.api.StravaLogin;
import com.sweetzpot.stravazpot.authenticaton.model.AppCredentials;
import com.sweetzpot.stravazpot.authenticaton.model.LoginResult;
import com.sweetzpot.stravazpot.authenticaton.ui.StravaLoginActivity;
import com.sweetzpot.stravazpot.authenticaton.ui.StravaLoginButton;
import com.sweetzpot.stravazpot.common.api.AuthenticationConfig;
import com.sweetzpot.stravazpot.common.api.StravaConfig;

public class AuthenticationActivity extends AppCompatActivity {

    private static final int RQ_LOGIN = 1;
    private static final int CLIENT_ID = 9353;
    private static final String CLIENT_SECRET = "adf9e2e21ce5de147b7816cb82103455aacb18e8";
    private static final String REDIRECT_URI = "stravapluscs646://cs646callback.com";

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        StravaLoginButton loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(event -> login());
    }

    private void login() {
        Intent intent = StravaLogin.withContext(this)
                .withClientID(CLIENT_ID)
                .withRedirectURI(REDIRECT_URI)
                .withApprovalPrompt(ApprovalPrompt.FORCE)
                .withAccessScope(AccessScope.PUBLIC)
                .makeIntent();
        startActivityForResult(intent, RQ_LOGIN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RQ_LOGIN && resultCode == RESULT_OK && data != null) {
            String code = data.getStringExtra(StravaLoginActivity.RESULT_CODE);

            Thread thread = new Thread(() -> {
                try {
                    AuthenticationConfig config = AuthenticationConfig.create()
                            .debug()
                            .build();
                    AuthenticationAPI api = new AuthenticationAPI(config);
                    LoginResult result = api.getTokenForApp(AppCredentials.with(CLIENT_ID, CLIENT_SECRET))
                            .withCode(code)
                            .execute();

                    String token = result.getToken().toString();
                    saveToken(token);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            thread.start();
        }
    }

    private void saveToken(String token) {
        SharedPreferences sharedPref = getSharedPreferences("app", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        String key = getString(R.string.token_key);
        editor.putString(key, token);
        editor.apply();
    }
}

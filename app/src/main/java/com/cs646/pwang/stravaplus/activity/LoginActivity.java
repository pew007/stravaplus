package com.cs646.pwang.stravaplus.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;

import com.cs646.pwang.stravaplus.R;
import com.cs646.pwang.stravaplus.task.LoginTask;
import com.sweetzpot.stravazpot.authenticaton.api.AccessScope;
import com.sweetzpot.stravazpot.authenticaton.api.ApprovalPrompt;
import com.sweetzpot.stravazpot.authenticaton.api.StravaLogin;
import com.sweetzpot.stravazpot.authenticaton.ui.StravaLoginActivity;
import com.sweetzpot.stravazpot.authenticaton.ui.StravaLoginButton;

public class LoginActivity extends AppCompatActivity {

    private static final int RQ_LOGIN = 1;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        StravaLoginButton loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(event -> login());
    }

    private void login() {
        int clientId = Integer.parseInt(getString(R.string.client_id));
        String redirectUrl = getString(R.string.redirect_url);

        Intent intent = StravaLogin.withContext(this)
                .withClientID(clientId)
                .withRedirectURI(redirectUrl)
                .withApprovalPrompt(ApprovalPrompt.FORCE)
                .withAccessScope(AccessScope.VIEW_PRIVATE_WRITE)
                .makeIntent();
        startActivityForResult(intent, RQ_LOGIN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RQ_LOGIN && resultCode == RESULT_OK && data != null) {
            String code = data.getStringExtra(StravaLoginActivity.RESULT_CODE);
            LoginTask task = new LoginTask(this);
            task.execute(code);
        }
    }
}

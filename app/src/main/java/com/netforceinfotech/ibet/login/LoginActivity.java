package com.netforceinfotech.ibet.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.dashboard.Dashboard;
import com.netforceinfotech.ibet.general.UserSessionManager;
import com.netforceinfotech.ibet.profilesetting.ProfileSettingActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    public CallbackManager mCallbackManager;
    private LoginButton buttonFacebook;
    private List<String> permissions;
    Button buttonFacebookCustom;
    private Profile profile;
    private Intent intent;
    private UserSessionManager userSessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(getApplication());
        setContentView(R.layout.activity_login);
        userSessionManager = new UserSessionManager(getApplicationContext());
        mCallbackManager = CallbackManager.Factory.create();
        findViewById(R.id.textViewRegister).setOnClickListener(this);
        buttonFacebookCustom = (Button) findViewById(R.id.buttonCustomFB);
        buttonFacebookCustom.setOnClickListener(this);
        buttonFacebook = (LoginButton) findViewById(R.id.login_button);
        permissions = new ArrayList<String>();
        permissions.add("email");
        permissions.add("user_birthday");
        buttonFacebook.setReadPermissions(permissions);
        buttonFacebook.registerCallback(mCallbackManager, mCallBack);
        profile = Profile.getCurrentProfile();
        if (profile != null) {
            Intent intent = new Intent(getApplicationContext(), Dashboard.class);
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.enter, R.anim.exit);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textViewRegister:
                Intent intent = new Intent(getApplicationContext(), EmailLogin.class);
                startActivity(intent);
                overridePendingTransition(R.anim.enter, R.anim.exit);
                break;
            case R.id.buttonCustomFB:
                buttonFacebook.performClick();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode,
                resultCode, data);

    }

    FacebookCallback<LoginResult> mCallBack = new FacebookCallback<LoginResult>() {

        @Override
        public void onSuccess(final LoginResult loginResult) {
            GraphRequest request = GraphRequest.newMeRequest(
                    loginResult.getAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback() {
                        public String home;

                        @Override
                        public void onCompleted(
                                JSONObject object,
                                GraphResponse response) {
                            Log.i("facebook", response.toString());
                            // Application code
                            if (object != null) {
                                //parameters.putString("fields", "id,name,email,gender, birthday,picture ");
                                AccessToken accessToken = loginResult.getAccessToken();
                                Profile profile = Profile.getCurrentProfile();
                                String fbName;
                                try {
                                    fbName = object.getString("name");
                                    userSessionManager.setName(fbName);
                                } catch (JSONException e) {
                                    fbName = "";
                                    e.printStackTrace();
                                }
                                String fbId;
                                try {
                                    fbId = object.getString("id");
                                    userSessionManager.setFBID(fbId);
                                } catch (JSONException e) {
                                    fbId = "";
                                    e.printStackTrace();
                                }
                                String fbEmail;
                                try {
                                    fbEmail = object.getString("email");
                                    userSessionManager.setEmail(fbEmail);
                                } catch (JSONException e) {
                                    fbEmail = "";
                                    e.printStackTrace();
                                }
                                String fbBirthday;
                                try {
                                    fbBirthday = object.getString("birthday");
                                } catch (JSONException e) {
                                    fbBirthday = "";
                                    e.printStackTrace();
                                }
                                String fbGender;
                                try {
                                    fbGender = object.getString("gender");
                                } catch (JSONException e) {
                                    fbGender = "";
                                    e.printStackTrace();
                                }

                                String fbToken = accessToken.getToken();
                                userSessionManager.setToken(fbToken);
                                String imageURL = "https://graph.facebook.com/" + fbId + "/picture?type=large";
                                buttonFacebookCustom.setText(R.string.logout);
                                Log.i("facebookgrapth", fbName + " " + fbBirthday);
                                UserSessionManager userSessionManager = new UserSessionManager(getApplicationContext());
                                if (userSessionManager.getIsFirstTime()) {
                                    intent = new Intent(getApplicationContext(), ProfileSettingActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    intent = new Intent(getApplicationContext(), ProfileSettingActivity.class);
                                    startActivity(intent);
                                    finish();
                                   /* intent = new Intent(getApplicationContext(), Dashboard.class);
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.enter, R.anim.exit);
                                    finish();*/
                                }
                            }
                        }
                    });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name,email,gender, birthday");
            request.setParameters(parameters);
            request.executeAsync();

        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {
            Log.i("LoginActivity", error.toString());
        }
    };
}

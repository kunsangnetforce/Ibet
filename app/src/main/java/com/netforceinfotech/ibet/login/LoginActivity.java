package com.netforceinfotech.ibet.login;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.Cancellable;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.async.http.AsyncHttpClientMiddleware;
import com.koushikdutta.ion.Ion;
import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.dashboard.Dashboard;
import com.netforceinfotech.ibet.general.UserSessionManager;
import com.netforceinfotech.ibet.profilesetting.ProfileSettingActivity;

import org.json.JSONArray;
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
    Context context;
    RelativeLayout relative_login;
    LinearLayout linearLayoutProgress;
    private String TAG = "MyFirebaseIIDService";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(getApplication());
        setContentView(R.layout.activity_login);
        context = this;
        linearLayoutProgress = (LinearLayout) findViewById(R.id.linearLayoutProgress);
        userSessionManager = new UserSessionManager(getApplicationContext());
        mCallbackManager = CallbackManager.Factory.create();
        findViewById(R.id.textViewRegister).setOnClickListener(this);
        findViewById(R.id.buttonGuest).setOnClickListener(this);
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
            LoginManager.getInstance().logOut();
           /* Intent intent = new Intent(getApplicationContext(), ProfileSettingActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.enter, R.anim.exit);*/
        }
        sendRegId();
    }

    private void sendRegId() {
        String url = getResources().getString(R.string.url);
        String pushurl = "/push_notification.php?user_id=" + userSessionManager.getCustomerId() + "&regid=" + userSessionManager.getRegId();

        Ion.with(getApplicationContext())
                .load(url + pushurl)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {

                        if (result == null) {
                            Log.i(TAG, "not sending");
                            userSessionManager.setGCMRegistered(false);
                        } else {
                            Log.i(TAG, "successfully registered");

                        }


                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.buttonGuest:
                userSessionManager.setLoginMode("0");
                userSessionManager.setToken("");
                userSessionManager.setEmail("");
                userSessionManager.setFBID("");
                userSessionManager.setProfilePic("no");
                userSessionManager.setCustomerId("");
                userSessionManager.setName("User");
                Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                startActivity(intent);
                overridePendingTransition(R.anim.enter, R.anim.exit);
                break;
            case R.id.textViewRegister:
                Intent intent2 = new Intent(getApplicationContext(), Dashboard.class);
                startActivity(intent2);
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
                                String reg_id = "abdfdf123423fdf";
                                login(fbToken, fbName, fbId, reg_id, fbEmail);
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

    private void login(String fbToken, String fbName, final String fbId, String reg_id, String email) {
        //https://netforcesales.com/ibet_admin/api/services.php?opt=register&email=kunwangyal15@yahoo.com&fb_token=qwerty1&name=Kunsang%20Wangyal&facebook=1&fb_id=1sdfasdf232324&device_id=asdf23232322&reg_id=asdfasdf232324
        String url = getResources().getString(R.string.url);
        String device_id = getDeviceId();
        fbName = fbName.replace(" ", "%20");
        url = url + "/services.php?opt=register&email=" + email + "&fb_token=" + fbToken + "&name=" + fbName + "&fb_id=" + fbId + "&device_id=" + device_id + "&reg_id=" + userSessionManager.getRegId() + "&login_mode=1";
        Log.i("result url", url);
        setHeader();
        linearLayoutProgress.setVisibility(View.VISIBLE);
        Ion.with(context)
                .load(url)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        linearLayoutProgress.setVisibility(View.GONE);
                        if (result == null) {
                            showMessage("nothings is here");
                        } else {
                            Log.i("kunsang_test_login", result.toString());
                            String status = result.get("status").getAsString().toLowerCase();
                            if (status.equalsIgnoreCase("success")) {
                                String imageURL = "https://graph.facebook.com/" + fbId + "/picture?type=large";
                                userSessionManager.setProfilePic(imageURL);
                                userSessionManager.setLoginMode("1");
                                JsonArray data = result.getAsJsonArray("data");
                                JsonObject object = data.get(0).getAsJsonObject();
                                String api_token = result.get("api_token").getAsString();
                                String customer_id = object.get("customer_id").getAsString();
                                userSessionManager.setCustomerId(customer_id);
                                userSessionManager.setApitoken(api_token);
                                if (userSessionManager.getIsFirstTime()) {
                                    intent = new Intent(context, ProfileSettingActivity.class);
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.enter, R.anim.exit);
                                } else {
                                    intent = new Intent(getApplicationContext(), ProfileSettingActivity.class);
                                    startActivity(intent);
                                    finish();
                                    overridePendingTransition(R.anim.enter, R.anim.exit);
                                }

                            }
                        }

                    }
                });
    }

    private void showMessage(String s) {
        Toast.makeText(LoginActivity.this, s, Toast.LENGTH_SHORT).show();
    }

    private String getDeviceId() {
        return "asdfasdfsadfd";
    }

    private void setHeader() {
        final String appkey = getResources().getString(R.string.appkey);
        Ion.getDefault(context).getHttpClient().insertMiddleware(new AsyncHttpClientMiddleware() {
            @Override
            public void onRequest(OnRequestData data) {
                data.request.setHeader("APPKEY", appkey);
            }

            @Override
            public Cancellable getSocket(GetSocketData data) {
                return null;
            }

            @Override
            public boolean exchangeHeaders(OnExchangeHeaderData data) {
                return false;
            }

            @Override
            public void onRequestSent(OnRequestSentData data) {

            }

            @Override
            public void onHeadersReceived(OnHeadersReceivedDataOnRequestSentData data) {

            }

            @Override
            public void onBodyDecoder(OnBodyDataOnRequestSentData data) {

            }

            @Override
            public void onResponseComplete(OnResponseCompleteDataOnRequestSentData data) {

            }
        });
    }
}

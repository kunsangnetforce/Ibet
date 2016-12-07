package com.netforceinfotech.ibet.general.firebasegcm;

import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.general.UserSessionManager;


public class MyFirebaseIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";
    UserSessionManager userSessionManager;

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    // [START refresh_token]
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        userSessionManager = new UserSessionManager(getApplicationContext());
        Log.i(TAG, "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(refreshedToken);
    }
    // [END refresh_token]

    /**
     * Persist token to third-party servers.
     * <p>
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(String token) {
        // TODO: Implement this method to send token to your app server.
        //https://netforcesales.com/ibet_admin/api/push_notification.php?user_id=83&regid=614704
        String url = getResources().getString(R.string.url);
        String pushurl = "/push_notification.php?user_id=" + userSessionManager.getCustomerId() + "&regid=" + token;
        userSessionManager.setRegId(token);
        userSessionManager.setGCMRegistered(false);
      /*  if (userSessionManager.getLoginMode().equalsIgnoreCase("0")) {
            return;
        }
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

                            String status = result.get("status").getAsString().toLowerCase();
                            if (status.equalsIgnoreCase("success")) {
                                Log.i(TAG, "successfully registered");
                                userSessionManager.setGCMRegistered(true);
                            } else {
                                userSessionManager.setGCMRegistered(false);
                                Log.i(TAG, "successfully registered");
                            }
                        }

                    }
                });*/
    }

    private void showMessage(String s) {
        Toast.makeText(MyFirebaseIDService.this, s, Toast.LENGTH_SHORT).show();
    }
}
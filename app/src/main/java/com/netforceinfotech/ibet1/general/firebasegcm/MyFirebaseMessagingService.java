package com.netforceinfotech.ibet1.general.firebasegcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.JsonObject;
import com.netforceinfotech.ibet1.Debugger.Debugger;
import com.netforceinfotech.ibet1.MainActivity;
import com.netforceinfotech.ibet1.R;
import com.netforceinfotech.ibet1.general.UserSessionManager;

import org.json.JSONObject;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    UserSessionManager userSessionManager;
    private static final String TAG = "MyFirebaseMsgService";

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        userSessionManager = new UserSessionManager(getApplicationContext());
        Log.i(TAG, remoteMessage.getData().toString());
        String message = remoteMessage.getData().get("message");

        if (userSessionManager.getSoundOnOff()) {
            try {
                JSONObject obj = new JSONObject(message);
                String title = obj.getString("title");
                sendNotification(title, obj);
            } catch (Exception t) {
                t.printStackTrace();
            }

        }
    }

    private void sendNotification(String title, JSONObject message) {
        Debugger.i("kunsang_notification", message.toString());
        String subtitle = "", messageString = "", home_logo = "", away_logo = "", bet_id = "";
        try {
            subtitle = message.getString("subtitle");
            messageString = message.getString("message");
            home_logo = message.getString("home_team");
            away_logo = message.getString("away_team");
        } catch (Exception ex) {
            subtitle = getString(R.string.ibet_notification);
            messageString = getString(R.string.you_got_notification);
            home_logo = "";
            away_logo = "";

        }
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);
       /* RemoteViews remoteViews = new RemoteViews(getPackageName(),
                R.layout.notificatin_bet);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                this).setSmallIcon(R.drawable.ic_launcher).setContent(
                remoteViews);*/
        Uri path = Uri.parse("android.resource://com.netforceinfotech.ibet1/raw/cheering");
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.statusbarlogo)
                .setContentTitle(subtitle)
                .setContentText(messageString)
                .setAutoCancel(true)
                .setSound(path)
                .setContentIntent(pendingIntent);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}
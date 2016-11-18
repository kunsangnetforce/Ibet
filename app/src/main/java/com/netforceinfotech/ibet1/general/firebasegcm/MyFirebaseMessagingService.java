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

import org.json.JSONException;
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
                sendNotification(obj);
            } catch (Exception t) {
                t.printStackTrace();
            }

        }
    }

    private void sendNotification(JSONObject message) {
        Debugger.i("kunsang_notification", message.toString());
        String flag = "", event = "", result = "";
        try {
            flag = message.getString("flag");
            if (flag.equalsIgnoreCase("bet")) {
                result = message.getString("result");
                setupBetNotificationLogic(result, message);
            } else if (flag.equalsIgnoreCase("event")) {
                String type = message.getString("type");
                String team_id = message.getString("team_id");
                String subtitle = message.getString("subtitle");
                setupEventNotification(type, team_id, subtitle);
            } else if (flag.equalsIgnoreCase("accept_bet")) {
                setupAcceptBetNotification(message);
            } else {
                setupJoinBetNotification(message);
            }
        } catch (Exception ex) {

        }

    }

    private void setupAcceptBetNotification(JSONObject message) {
        String accept_bet, string_accept_bet, title, sound;
        try {
            accept_bet = message.getString("accept_bet");
            title = message.getString("title");

        } catch (Exception ex) {
            accept_bet = "Ibet Notification";
            title = "Ibet";

        }
        if (accept_bet.equalsIgnoreCase("0")) {
            sound = "cheering";
            string_accept_bet = getString(R.string.bet_accepted);
        } else if (accept_bet.equalsIgnoreCase("1")) {
            sound = "crowd_boo";
            string_accept_bet = getString(R.string.bet_rejected);
        } else {
            return;
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
        Uri path = Uri.parse("android.resource://com.netforceinfotech.ibet1/raw/" + sound);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.statusbarlogo)
                .setContentTitle(title)
                .setContentText(string_accept_bet)
                .setAutoCancel(true)
                .setSound(path)
                .setContentIntent(pendingIntent);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

    private void setupJoinBetNotification(JSONObject message) {
        String subtitle, messageString, home_logo, away_logo, bet_creator;
        try {
            subtitle = message.getString("subtitle");
            messageString = message.getString("message");

            home_logo = message.getString("home_team");
            away_logo = message.getString("away_team");
            bet_creator = message.getString("bet_creator");
        } catch (Exception ex) {
            subtitle = "Ibet Notification";
            messageString = "You have got notification";
            home_logo = "";
            away_logo = "";
            bet_creator = "";

        }
        if (bet_creator.equalsIgnoreCase(userSessionManager.getCustomerId())) {
            return;
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

    private void setupEventNotification(String type, String team_id, String subtitle) {
        if (!userSessionManager.getTeamNotification(team_id)) {
            return;
        }
        if (userSessionManager.getGeneralNotification(type + "general")) {
            String soundName = userSessionManager.getGeneralNotificationSoundName(type + "soundname");
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                    PendingIntent.FLAG_ONE_SHOT);

            Uri path = Uri.parse("android.resource://com.netforceinfotech.ibet1/raw/" + soundName);
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.statusbarlogo)
                    .setContentTitle(subtitle)
                    .setContentText(type)
                    .setAutoCancel(true)
                    .setSound(path)
                    .setContentIntent(pendingIntent);
            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
        }
    }

    private void setupBetNotificationLogic(String result, JSONObject message) {

        if (result.equalsIgnoreCase("win")) {
            if (userSessionManager.getWinBet()) {
                String winMessage = "You won a bet";
                String winSubtitle = null;
                try {
                    winSubtitle = message.getString("subtitle");
                } catch (JSONException e) {
                    winSubtitle = getString(R.string.got_notification);
                }
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                        PendingIntent.FLAG_ONE_SHOT);

                Uri path = Uri.parse("android.resource://com.netforceinfotech.ibet1/raw/cheering");
                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.statusbarlogo)
                        .setContentTitle(winSubtitle)
                        .setContentText(winMessage)
                        .setAutoCancel(true)
                        .setSound(path)
                        .setContentIntent(pendingIntent);
                NotificationManager notificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
            }

        } else if (result.equalsIgnoreCase("lost")) {
            if (userSessionManager.getWinBet()) {
                String loseMessage = "You Lost a bet";
                String loseSubtitle = "";
                try {
                    loseSubtitle = message.getString("subtitle");
                } catch (JSONException e) {
                    loseSubtitle = getString(R.string.got_notification);

                }
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                        PendingIntent.FLAG_ONE_SHOT);

                Uri path = Uri.parse("android.resource://com.netforceinfotech.ibet1/raw/crowd_boo");
                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.statusbarlogo)
                        .setContentTitle(loseSubtitle)
                        .setContentText(loseMessage)
                        .setAutoCancel(true)
                        .setSound(path)
                        .setContentIntent(pendingIntent);
                NotificationManager notificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
            }


        }
    }
}
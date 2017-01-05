package com.netforceinfotech.ibet.dashboard.setting.notification.generalNotification.notification;

/**
 * Created by Netforce on 9/20/2016.
 */
public class NotificationData {
    String name,type;
    boolean status;

    public NotificationData(String name, boolean status,String type) {
        this.name = name;
        this.type=type;
        this.status = status;
    }
}

package com.netforceinfotech.ibet.dashboard.setting.notification.generalNotification.sound;

/**
 * Created by Netforce on 9/20/2016.
 */
public class SoundData {
    String eventname, filename,songname;

    public SoundData(String eventname, String filename,String songname) {
        this.eventname = eventname;
        this.filename = filename;
        this.songname=songname;
    }
}

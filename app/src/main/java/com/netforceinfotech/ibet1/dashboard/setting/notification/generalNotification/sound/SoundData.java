package com.netforceinfotech.ibet1.dashboard.setting.notification.generalNotification.sound;

/**
 * Created by Netforce on 9/20/2016.
 */
public class SoundData {
    String eventname,type, filename,songname;

    public SoundData(String eventname, String filename,String songname,String type) {
        this.eventname = eventname;
        this.type=type;
        this.filename = filename;
        this.songname=songname;
    }
}

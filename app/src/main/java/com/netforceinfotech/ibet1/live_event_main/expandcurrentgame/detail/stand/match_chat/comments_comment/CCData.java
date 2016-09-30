package com.netforceinfotech.ibet1.live_event_main.expandcurrentgame.detail.stand.match_chat.comments_comment;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class CCData {
    String imageurl;
    String name;
    Long timestamp;
    String comment;
    String key;

    CCData(String imageurl, String name, Long timestamp, String comment, String key) {
        this.imageurl = imageurl;
        this.name = name;
        this.timestamp = timestamp;
        this.comment = comment;
        this.key = key;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CCData)) {
            return false;
        }

        CCData that = (CCData) obj;
        return this.key.equals(that.key);
    }
}

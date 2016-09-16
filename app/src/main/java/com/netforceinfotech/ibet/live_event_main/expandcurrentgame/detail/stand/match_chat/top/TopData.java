package com.netforceinfotech.ibet.live_event_main.expandcurrentgame.detail.stand.match_chat.top;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class TopData {
    String imageurl;
    String name;
    Long timestamp;
    String comment;
    String share;
    String dislike;
    String like;
    String message;
    String key;

    TopData(String imageurl, String name, Long timestamp, String comment, String share, String dislike, String like, String message, String key) {
        this.imageurl = imageurl;
        this.name = name;
        this.timestamp = timestamp;
        this.comment = comment;
        this.share = share;
        this.dislike = dislike;
        this.like = like;
        this.message = message;
        this.key=key;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TopData)) {
            return false;
        }

        TopData that = (TopData) obj;
        return this.key.equals(that.key);
    }
}

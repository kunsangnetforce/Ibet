package com.netforceinfotech.ibet1.currentbet.betarena.thearena;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class TheArenaData {
    String imageurl;
    String name;
    Long timestamp;
    String comment;
    String share;
    String dislike;
    String like;
    String message;
    String key;

    TheArenaData(String imageurl, String name, Long timestamp, String comment, String share, String dislike, String like, String message, String key) {
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
        if (!(obj instanceof TheArenaData)) {
            return false;
        }

        TheArenaData that = (TheArenaData) obj;
        return this.key.equals(that.key);
    }
}

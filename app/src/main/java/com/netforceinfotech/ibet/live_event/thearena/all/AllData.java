package com.netforceinfotech.ibet.live_event.thearena.all;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class AllData {
    String imageurl;
    String name;
    Long timestamp;
    String comment;
    String share;
    String dislike;
    String like;
    String message;
    AllData(String imageurl, String name, Long timestamp, String comment, String share, String dislike, String like, String message){
        this.imageurl=imageurl;
        this.name=name;
        this.timestamp=timestamp;
        this.comment=comment;
        this.share=share;
        this.dislike=dislike;
        this.like=like;
        this.message=message;
    }
}

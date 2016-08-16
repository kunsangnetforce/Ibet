package com.netforceinfotech.ibet.scratchview;

/**
 * Created by Netforce on 8/16/2016.
 */
public class ScratchData {
    int position;
    String value, imgUrl, type;

    ScratchData(int position, String value, String imgUrl, String type) {
        this.position = position;
        this.value = value;
        this.imgUrl = imgUrl;
        this.type = type;
    }
}

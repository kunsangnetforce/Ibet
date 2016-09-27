package com.netforceinfotech.ibet.scratchview;

/**
 * Created by Netforce on 8/16/2016.
 */
public class ScratchData {
    int position, imgUrl,value;
    String  type;

    ScratchData(int position, int value, int imgUrl, String type) {
        this.position = position;
        this.value = value;
        this.imgUrl = imgUrl;
        this.type = type;
    }
}

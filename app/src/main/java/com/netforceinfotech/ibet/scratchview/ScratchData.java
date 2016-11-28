package com.netforceinfotech.ibet.scratchview;

/**
 * Created by Netforce on 8/16/2016.
 */
public class ScratchData {
    int position, coins,value;
    String  type;

    ScratchData(int position, int value, int coins, String type) {
        this.position = position;
        this.value = value;
        this.coins = coins;
        this.type = type;
    }
}

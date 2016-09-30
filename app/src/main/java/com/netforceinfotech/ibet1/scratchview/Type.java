package com.netforceinfotech.ibet1.scratchview;

/**
 * Created by Netforce on 9/22/2016.
 */
public class Type {
    int value, count;

    public Type(int value, int count) {
        this.value = value;
        this.count = count;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Type)) {
            return false;
        }

        Type that = (Type) obj;
        return this.value == that.value;
    }


}

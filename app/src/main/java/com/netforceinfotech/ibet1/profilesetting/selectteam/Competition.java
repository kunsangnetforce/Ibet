package com.netforceinfotech.ibet1.profilesetting.selectteam;

/**
 * Created by Netforce on 10/7/2016.
 */
public class Competition {
    String compId, compName;

    Competition(String compId, String compName) {
        this.compId = compId;
        this.compName = compName;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Competition)) {
            return false;
        }

        Competition that = (Competition) obj;
        return this.compId.equals(that.compId);
    }
}

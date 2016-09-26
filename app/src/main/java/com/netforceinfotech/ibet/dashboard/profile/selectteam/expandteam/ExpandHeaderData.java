package com.netforceinfotech.ibet.dashboard.profile.selectteam.expandteam;

/**
 * Created by Netforce on 8/27/2016.
 */
public class ExpandHeaderData {
    public String id, name;

    public ExpandHeaderData(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ExpandHeaderData)) {
            return false;
        }

        ExpandHeaderData that = (ExpandHeaderData) obj;
        return this.id.equals(that.id);
    }
}

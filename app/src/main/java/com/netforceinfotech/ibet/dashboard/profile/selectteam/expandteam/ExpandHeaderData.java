package com.netforceinfotech.ibet.dashboard.profile.selectteam.expandteam;

/**
 * Created by Netforce on 8/27/2016.
 */
public class ExpandHeaderData {
    public String  name;
    public int id;

    public ExpandHeaderData(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ExpandHeaderData)) {
            return false;
        }

        ExpandHeaderData that = (ExpandHeaderData) obj;
        return this.id==that.id;
    }
}

package com.netforceinfotech.ibet.profilesetting.selectteam.expandteam;

/**
 * Created by Netforce on 8/27/2016.
 */
public class ExpandChildData {
    public String logo, id, name;

    public ExpandChildData(String id, String name, String logo) {
        this.id = id;
        this.name = name;
        this.logo = logo;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ExpandChildData)) {
            return false;
        }

        ExpandChildData that = (ExpandChildData) obj;
        return this.id.equals(that.id);
    }
}

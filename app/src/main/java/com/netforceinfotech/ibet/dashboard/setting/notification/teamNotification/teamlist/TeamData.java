package com.netforceinfotech.ibet.dashboard.setting.notification.teamNotification.teamlist;

import com.netforceinfotech.ibet.profilesetting.selectteam.listofteam.TeamListData;

/**
 * Created by Netforce on 9/19/2016.
 */
public class TeamData {
    String id;
    public String name;
    String logo;

    public TeamData(String id, String name, String logo) {
        this.id = id;
        this.name = name;
        this.logo = logo;
    }
}

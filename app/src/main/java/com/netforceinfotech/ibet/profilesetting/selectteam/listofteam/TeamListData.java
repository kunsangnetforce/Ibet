package com.netforceinfotech.ibet.profilesetting.selectteam.listofteam;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class TeamListData {
    public String logo, id, name,compid,compName;

    public TeamListData(String id, String name, String logo,String compid,String compName) {
        this.id = id;
        this.name = name;
        this.logo = logo;
        this.compid=compid;
        this.compName=compName;

    }
}

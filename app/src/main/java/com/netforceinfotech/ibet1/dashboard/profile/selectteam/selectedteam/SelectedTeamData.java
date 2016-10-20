package com.netforceinfotech.ibet1.dashboard.profile.selectteam.selectedteam;

/**
 * Created by Netforce on 9/23/2016.
 */
public class SelectedTeamData {
    public String logo, id, name, compName;
    public int compid;

    public SelectedTeamData(String id, String name, String logo, int compid, String compName) {
        this.id = id;
        this.name = name;
        this.logo = logo;
        this.compid = compid;
        this.compName = compName;

    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SelectedTeamData)) {
            return false;
        }

        SelectedTeamData that = (SelectedTeamData) obj;
        //this.str1.equals(that.getStr1()) && this.str2.equals(that.getStr2());
        return this.id.equals(that.id);
    }
}

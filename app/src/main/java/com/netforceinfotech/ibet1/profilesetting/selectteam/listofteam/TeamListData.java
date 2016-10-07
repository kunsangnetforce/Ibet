package com.netforceinfotech.ibet1.profilesetting.selectteam.listofteam;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class TeamListData implements Comparable<TeamListData> {
    public String logo, id, name, compName;
    public int compid;

    public TeamListData(String id, String name, String logo, int compid, String compName) {
        this.id = id;
        this.name = name;
        this.logo = logo;
        this.compid = compid;
        this.compName = compName;

    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TeamListData)) {
            return false;
        }

        TeamListData that = (TeamListData) obj;
        //this.str1.equals(that.getStr1()) && this.str2.equals(that.getStr2());
        return this.id.equals(that.id) && this.compid == that.compid;
    }

    @Override
    public int compareTo(TeamListData eventsData) {
        return Double.compare(compid, eventsData.compid);

    }
}

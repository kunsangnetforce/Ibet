package com.netforceinfotech.ibet.dashboard.home.startnewbet.upcominggame;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class UpcomingGameData {
    String matchid, teama, teamb, logoa, logob;

    UpcomingGameData(String matchid, String teama, String teamb, String logoa, String logob) {
        this.matchid = matchid;
        this.teama = teama;
        this.teamb = teamb;
        this.logoa = logoa;
        this.logob = logob;
    }
}

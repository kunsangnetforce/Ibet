package com.netforceinfotech.ibet.live_event;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class CurrentGameData {
    String matchid, teama, teamb, logoa, logob;

    CurrentGameData(String matchid, String teama, String teamb, String logoa, String logob) {
        this.matchid = matchid;
        this.teama = teama;
        this.teamb = teamb;
        this.logoa = logoa;
        this.logob = logob;
    }
}

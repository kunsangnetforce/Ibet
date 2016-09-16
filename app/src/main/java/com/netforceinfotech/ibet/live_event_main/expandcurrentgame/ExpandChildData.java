package com.netforceinfotech.ibet.live_event_main.expandcurrentgame;

/**
 * Created by Netforce on 8/27/2016.
 */
public class ExpandChildData {
    String matchid, teama, teamb, logoa, logob, teamaid, teambid, competition_name, compition_id;

    ExpandChildData(String matchid, String teama, String teamb, String logoa, String logob, String teamaid, String teambid, String compition_id, String competition_name) {
        this.matchid = matchid;
        this.teama = teama;
        this.teamb = teamb;
        this.logoa = logoa;
        this.logob = logob;
        this.teamaid = teamaid;
        this.teambid = teambid;
        this.competition_name = competition_name;
        this.compition_id = compition_id;
    }
}

package com.netforceinfotech.ibet.live_event_main;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class CurrentGameData {
    public String matchid, teama, teamb, logoa, logob, teamaid, teambid, competition_name, compition_id;

    public CurrentGameData(String matchid, String teama, String teamb, String logoa, String logob, String teamaid, String teambid, String compition_id, String competition_name) {
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

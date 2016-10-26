package com.netforceinfotech.ibet1.dashboard.home.bets_to_join;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class BetsToJoinData {
    String userdp, name, numberparticipant, time, teamalogo, teamblogo, teamaname, teambname, betid;

    BetsToJoinData(String userdp, String name, String numberparticipant, String time, String teamalogo, String teamblogo, String teamaname, String teambname, String betid) {
        this.name = name;
        this.userdp = userdp;
        this.numberparticipant = numberparticipant;
        this.time = time;
        this.teamalogo = teamalogo;
        this.teamblogo = teamblogo;
        this.teamaname = teamaname;
        this.teambname = teambname;
        this.betid = betid;
    }
}

package com.netforceinfotech.ibet.dashboard.home.finsihed_bet;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class FinsihedData {
    String userdp, name, numberparticipant, time, teamalogo, teamblogo, teamaname, teambname, betstatus, betid, match_id;

    FinsihedData(String userdp, String name, String numberparticipant, String time, String teamalogo, String teamblogo, String teamaname, String teambname, String betstatus, String betid, String match_id) {
        this.name = name;
        this.userdp = userdp;
        this.match_id = match_id;
        this.numberparticipant = numberparticipant;
        this.time = time;
        this.teamalogo = teamalogo;
        this.teamblogo = teamblogo;
        this.teamaname = teamaname;
        this.teambname = teambname;
        this.betstatus = betstatus;
        this.betid = betid;
    }
}

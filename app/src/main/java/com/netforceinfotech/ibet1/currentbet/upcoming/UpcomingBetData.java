package com.netforceinfotech.ibet1.currentbet.upcoming;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class UpcomingBetData {
    String userdp, name, selectedteamlogo, selectedteamname, numberparticipant, numberpost, time, teamalogo, teamblogo, teamaname, teambname, betstatus, betid;

    UpcomingBetData(String userdp, String name, String selectedteamlogo, String selectedteamname, String numberparticipant, String numberpost, String time, String teamalogo, String teamblogo, String teamaname, String teambname, String betstatus, String betid) {
        this.name = name;
        this.userdp = userdp;
        this.selectedteamlogo = selectedteamlogo;
        this.selectedteamname = selectedteamname;
        this.numberparticipant = numberparticipant;
        this.numberpost = numberpost;
        this.time = time;
        this.teamalogo = teamalogo;
        this.teamblogo = teamblogo;
        this.teamaname = teamaname;
        this.teambname = teambname;
        this.betstatus = betstatus;
        this.betid = betid;
    }
}

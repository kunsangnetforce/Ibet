package com.netforceinfotech.ibet1.currentbet.livebet;

/**
 * Created by Netforce on 8/30/2016.
 */
public class LiveBetData {
    String userdp, name, selectedteamlogo, selectedteamname, numberparticipant, numberpost, time, teamalogo, teamblogo, teamaname, teambname, betstatus, betid;

    LiveBetData(String userdp, String name, String selectedteamlogo, String selectedteamname, String numberparticipant, String numberpost, String time, String teamalogo, String teamblogo, String teamaname, String teambname, String betstatus, String betid) {
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

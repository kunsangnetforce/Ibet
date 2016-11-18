package com.netforceinfotech.ibet1.dashboard.home.bets_to_join;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class BetsToJoinData {
    String userdp, name, numberparticipant, time, teamalogo, teamblogo, teamaname, teambname, betid;
    public String matchid, bet_option, bet_amount,creator_id;

    BetsToJoinData(String userdp, String name, String numberparticipant, String time, String teamalogo, String teamblogo, String teamaname, String teambname, String betid, String matchid, String bet_option, String bet_amount, String creator_id) {
        this.name = name;
        this.creator_id=creator_id;
        this.bet_amount = bet_amount;
        this.bet_option = bet_option;
        this.userdp = userdp;
        this.matchid = matchid;
        this.numberparticipant = numberparticipant;
        this.time = time;
        this.teamalogo = teamalogo;
        this.teamblogo = teamblogo;
        this.teamaname = teamaname;
        this.teambname = teambname;
        this.betid = betid;
    }
}

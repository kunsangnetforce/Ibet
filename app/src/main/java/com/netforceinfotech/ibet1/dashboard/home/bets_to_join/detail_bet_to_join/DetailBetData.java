package com.netforceinfotech.ibet1.dashboard.home.bets_to_join.detail_bet_to_join;

public class DetailBetData {
    String userdp, username, selectedteam, score, result;

    DetailBetData(String userdp, String username, String result, String selectedteam, String score) {
        this.userdp = userdp;
        this.username = username;
        this.result = result;
        this.selectedteam = selectedteam;
        this.score = score;
    }
}

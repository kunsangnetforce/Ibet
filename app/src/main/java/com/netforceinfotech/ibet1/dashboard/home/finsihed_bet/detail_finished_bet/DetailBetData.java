package com.netforceinfotech.ibet1.dashboard.home.finsihed_bet.detail_finished_bet;

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

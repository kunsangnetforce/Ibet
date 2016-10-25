package com.netforceinfotech.ibet1.solobet.currentgame;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class CurrentGameData {
    public String match_id, home_name, away_name, home_logo, away_logo, home_id, away_id, competition_name, compition_id;

    public CurrentGameData(String match_id, String home_name, String away_name, String home_logo, String away_logo, String home_id, String away_id, String compition_id, String competition_name) {
        this.match_id = match_id;
        this.home_name = home_name;
        this.away_name = away_name;
        this.home_logo = home_logo;
        this.away_logo = away_logo;
        this.home_id = home_id;
        this.away_id = away_id;
        this.competition_name = competition_name;
        this.compition_id = compition_id;
    }
}

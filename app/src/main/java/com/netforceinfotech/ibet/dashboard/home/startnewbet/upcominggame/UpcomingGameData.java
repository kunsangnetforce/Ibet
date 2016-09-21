package com.netforceinfotech.ibet.dashboard.home.startnewbet.upcominggame;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class UpcomingGameData {
    public String match_id, home_name, away_name, home_logo, away_logo, home_id, away_id, competition_name, compition_id;

    UpcomingGameData(String match_id, String home_name, String away_name, String home_logo, String away_logo, String home_id, String away_id, String competition_name, String compition_id) {
        this.match_id = match_id;
        this.home_name = home_name;
        this.away_name = away_name;
        this.home_logo = home_logo;
        this.away_logo = away_logo;
    }
}

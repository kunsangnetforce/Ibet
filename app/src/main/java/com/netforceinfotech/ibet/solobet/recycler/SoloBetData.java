package com.netforceinfotech.ibet.solobet.recycler;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class SoloBetData {
    String bookername, bookerid, home_odds, away_odds, draw_odds;

    SoloBetData(String bookerid, String bookername, String home_odds, String away_odds, String draw_odds) {
        this.bookerid = bookerid;
        this.bookername = bookername;
        this.home_odds = home_odds;
        this.away_odds = away_odds;
        this.draw_odds = draw_odds;
    }
}

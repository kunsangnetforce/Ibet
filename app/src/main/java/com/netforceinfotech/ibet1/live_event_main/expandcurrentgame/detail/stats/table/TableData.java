package com.netforceinfotech.ibet1.live_event_main.expandcurrentgame.detail.stats.table;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class TableData {
    /*
    *  String position = jsonObject.get("position").getAsString();
            String points = jsonObject.get("points").getAsString();
            String goalDiff = jsonObject.get("goal_difference").getAsString();
            String wins = jsonObject.get("overall_win").getAsString();
            String overall_draw = jsonObject.get("overall_draw").getAsString();
            String overall_loose = jsonObject.get("overall_loose").getAsString();
            String overall_played = jsonObject.get("overall_played").getAsString();
            JsonObject team = jsonObject.getAsJsonObject("team");
            String name = team.get("name").getAsString();
            String logo = team.get("logo").getAsString();
    * */
    String name, logo, id, points, goalDiff, overall_wins, overall_draw, overall_loose, overall_played;

    TableData(String name, String logo, String id, String points, String goalDiff, String overall_wins, String overall_draw, String overall_loose,
              String overall_played) {
        this.name = name;
        this.id = id;
        this.points = points;
        this.logo = logo;
        this.goalDiff = goalDiff;
        this.overall_draw = overall_draw;
        this.overall_loose = overall_loose;
        this.overall_wins = overall_wins;
        this.overall_played = overall_played;
    }
}

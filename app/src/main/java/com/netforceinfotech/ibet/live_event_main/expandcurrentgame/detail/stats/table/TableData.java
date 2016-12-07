package com.netforceinfotech.ibet.live_event_main.expandcurrentgame.detail.stats.table;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class TableData implements Comparable<TableData> {
    String name, logo, id, points, goalDiff, overall_wins, overall_draw, overall_loose, overall_played;
    int position;

    TableData(String name, String logo, String id, String points, String goalDiff, String overall_wins, String overall_draw, String overall_loose,
              String overall_played, int position) {
        this.name = name;
        this.id = id;
        this.position = position;
        this.points = points;
        this.logo = logo;
        this.goalDiff = goalDiff;
        this.overall_draw = overall_draw;
        this.overall_loose = overall_loose;
        this.overall_wins = overall_wins;
        this.overall_played = overall_played;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TableData)) {
            return false;
        }

        TableData that = (TableData) obj;
        return this.id.equals(that.id);
    }

    @Override
    public int compareTo(TableData eventsData) {
        return Double.compare(position, eventsData.position);

    }
}

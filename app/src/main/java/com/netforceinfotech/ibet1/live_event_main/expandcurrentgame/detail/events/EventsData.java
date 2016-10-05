package com.netforceinfotech.ibet1.live_event_main.expandcurrentgame.detail.events;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class EventsData implements Comparable<EventsData> {
    String name, teamid, type, min, extra, in, out, team, id, stringTime;
    int time;

    EventsData(String name, String type, String teamid, String min, String extra, String in, String out, String team, String id) {
        this.name = name;
        this.teamid = teamid;
        this.type = type;
        this.in = in;
        this.team = team;
        this.out = out;
        this.min = min;
        this.id = id;
        this.extra = extra;
        if (extra.equalsIgnoreCase("") || extra.equalsIgnoreCase("0")) {
            this.time = Integer.parseInt(min);
        } else {
            this.time = Integer.parseInt(min) + Integer.parseInt(extra);
        }
        if (extra.equalsIgnoreCase("0") || extra.equalsIgnoreCase("")) {
            this.stringTime = min + "'";
        } else {
            this.stringTime = min + "'" + extra + "'";
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof EventsData)) {
            return false;
        }

        EventsData that = (EventsData) obj;
        return this.id.equals(that.id);
    }

    @Override
    public int compareTo(EventsData eventsData) {
        return  Double.compare(time,eventsData.time);

    }
}

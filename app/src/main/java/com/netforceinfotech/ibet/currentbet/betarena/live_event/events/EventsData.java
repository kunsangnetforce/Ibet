package com.netforceinfotech.ibet.currentbet.betarena.live_event.events;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class EventsData {
    String name, id, type, time, in, out, team;

    EventsData(String name, String type, String id, String time, String in, String out, String team) {
        this.name = name;
        this.id = id;
        this.type = type;
        this.in = in;
        this.team = team;
        this.out = out;
        this.time = time;
    }
}

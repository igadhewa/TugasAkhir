package com.mobile.livescoreapp;

/**
 * Created by JohnDoe on 5/17/2017.
 */

public class eventsItem {
    public String id,type,minute,team,player,assist,result;

    public eventsItem(String id, String type, String minute, String team, String player, String assist, String result) {
        this.id = id;
        this.type = type;
        this.minute = minute;
        this.team = team;
        this.player = player;
        this.assist = assist;
        this.result = result;
    }
}

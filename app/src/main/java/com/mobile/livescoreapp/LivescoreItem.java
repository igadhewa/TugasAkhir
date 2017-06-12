package com.mobile.livescoreapp;

import java.util.List;

/**
 * Created by JohnDoe on 5/11/2017.
 */

public class LivescoreItem {

    public String id,homeTeam,awayTeam,status,venue,visitorTeamScore,localTeamScore,events;
    public eventsItem mItem;


    public LivescoreItem(String id, String homeTeam, String awayTeam, String status, String venue, String visitorTeamScore, String localTeamScore,String events) {
        this.events = events;
        this.id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.status = status;
        this.venue = venue;
        this.visitorTeamScore = visitorTeamScore;
        this.localTeamScore = localTeamScore;
    }

    public LivescoreItem(String id, String homeTeam, String awayTeam, String status) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.status = status;
        this.id = id;
    }

}

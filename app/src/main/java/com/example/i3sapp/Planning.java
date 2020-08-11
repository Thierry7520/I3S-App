package com.example.i3sapp;

public class Planning
{

    String planningId;
    String planningGenre;
    String planningPostDate;
    String planningChannel;
    String planningType;
    String planningDescription;
    String palnningSponsoring;
    String planningHachtags;

    public Planning()
    {

    }

    public Planning(String planningId, String planningGenre, String planningPostDate, String planningChannel, String planningType, String planningDescription, String palnningSponsoring, String planningHachtags) {
        this.planningId = planningId;
        this.planningGenre = planningGenre;
        this.planningPostDate = planningPostDate;
        this.planningChannel = planningChannel;
        this.planningType = planningType;
        this.planningDescription = planningDescription;
        this.palnningSponsoring = palnningSponsoring;
        this.planningHachtags = planningHachtags;
    }

    public String getPlanningId() {
        return planningId;
    }

    public String getPlanningGenre() {
        return planningGenre;
    }

    public String getPlanningPostDate() {
        return planningPostDate;
    }

    public String getPlanningChannel() {
        return planningChannel;
    }

    public String getPlanningType() {
        return planningType;
    }

    public String getPlanningDescription() {
        return planningDescription;
    }

    public String getPalnningSponsoring() {
        return palnningSponsoring;
    }

    public String getPlanningHachtags() {
        return planningHachtags;
    }
}

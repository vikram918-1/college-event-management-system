package com.eventorg.event;

import java.util.ArrayList;
import java.util.List;

public class Hackathon extends Event {

    private int durationHours;
    private String theme;
    private List<String> mentors;

    public Hackathon(String eventId, String name, String date,
                     int maxParticipants, int durationHours,
                     String theme) {

        super(eventId, name, date, maxParticipants);
        this.durationHours = durationHours;
        this.theme = theme;
        mentors = new ArrayList<>();
    }

    @Override
    public void conductEvent() {
        System.out.println(
            "Hackathon conducted for " +
            durationHours + " hours."
        );
    }

    public void assignMentor(String mentor) {
        mentors.add(mentor);
    }

    public void removeMentor(String mentor) {
        mentors.remove(mentor);
    }

    @Override
    public String getEventType() {
        return "Hackathon";
    }

    public int getDurationHours() {
        return durationHours;
    }

    public String getTheme() {
        return theme;
    }

    public List<String> getMentors() {
        return mentors;
    }
}
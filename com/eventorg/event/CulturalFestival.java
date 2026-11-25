package com.eventorg.event;

import java.util.ArrayList;
import java.util.List;

public class CulturalFestival extends Event {

    private String category;
    private String performanceType;
    private List<String> judges;

    public CulturalFestival(
            String eventId,
            String name,
            String date,
            int maxParticipants,
            String category,
            String performanceType) {

        super(eventId, name, date, maxParticipants);
        this.category = category;
        this.performanceType = performanceType;
        judges = new ArrayList<>();
    }

    @Override
    public void conductEvent() {
        System.out.println(
            "Cultural festival conducted."
        );
    }

    public void addJudge(String judge) {
        judges.add(judge);
    }

    public void removeJudge(String judge) {
        judges.remove(judge);
    }

    public void evaluatePerformance() {
        System.out.println("Performance evaluated.");
    }

    @Override
    public String getEventType() {
        return "Cultural Festival";
    }

    public String getCategory() {
        return category;
    }

    public String getPerformanceType() {
        return performanceType;
    }

    public List<String> getJudges() {
        return judges;
    }
}
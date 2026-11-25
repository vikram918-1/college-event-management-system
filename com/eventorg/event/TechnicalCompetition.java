package com.eventorg.event;

import com.eventorg.participant.Participant;

public class TechnicalCompetition extends Event {

    private int problemCount;
    private String difficultyLevel;
    private String judgingCriteria;

    public TechnicalCompetition(
            String eventId,
            String name,
            String date,
            int maxParticipants,
            int problemCount,
            String difficultyLevel,
            String judgingCriteria) {

        super(eventId, name, date, maxParticipants);
        this.problemCount = problemCount;
        this.difficultyLevel = difficultyLevel;
        this.judgingCriteria = judgingCriteria;
    }

    @Override
    public void conductEvent() {
        System.out.println(
            "Technical competition conducted."
        );
    }

    public void evaluateParticipant(Participant participant) {
        System.out.println(
            participant.getDisplayName() +
            " evaluated based on " +
            judgingCriteria
        );
    }

    @Override
    public String getEventType() {
        return "Technical Competition";
    }

    public int getProblemCount() {
        return problemCount;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public String getJudgingCriteria() {
        return judgingCriteria;
    }
}
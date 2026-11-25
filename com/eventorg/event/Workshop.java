package com.eventorg.event;

public class Workshop extends Event {

    private String instructor;
    private String topic;
    private int duration;

    public Workshop(String eventId, String name, String date,
                    int maxParticipants, String instructor,
                    String topic, int duration) {

        super(eventId, name, date, maxParticipants);
        this.instructor = instructor;
        this.topic = topic;
        this.duration = duration;
    }

    @Override
    public void conductEvent() {
        System.out.println(
            "Workshop conducted by " + instructor
        );
    }

    @Override
    public String getEventType() {
        return "Workshop";
    }

    public String getInstructor() {
        return instructor;
    }

    public String getTopic() {
        return topic;
    }

    public int getDuration() {
        return duration;
    }
}
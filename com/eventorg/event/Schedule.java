package com.eventorg.event;

import java.time.Duration;
import java.time.LocalTime;

public class Schedule {

    private String scheduleId;
    private String date;
    private String startTime;
    private String endTime;
    private String sessionName;
    private String speaker;

    public Schedule(String scheduleId, String date,
                    String startTime, String endTime,
                    String sessionName, String speaker) {

        this.scheduleId = scheduleId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.sessionName = sessionName;
        this.speaker = speaker;
    }

    public int getDuration() {

        LocalTime start = LocalTime.parse(startTime);
        LocalTime end = LocalTime.parse(endTime);

        return (int) Duration.between(start, end).toMinutes();
    }

    public void reschedule(String date,
                           String startTime,
                           String endTime) {

        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public String getDate() {
        return date;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getSessionName() {
        return sessionName;
    }

    public String getSpeaker() {
        return speaker;
    }
}
package com.eventorg.event;

import java.util.ArrayList;
import java.util.List;
import com.eventorg.exception.EventFullException;
import com.eventorg.participant.Participant;

public abstract class Event {

    private String eventId;
    private String name;
    private String date;
    private int maxParticipants;
    private Venue venue;
    private Schedule schedule;
    private List<Participant> participants;
    private List<Attendance> attendanceRecords;
    private boolean cancelled;

    public Event(String eventId, String name, String date,
                 int maxParticipants) {

        this.eventId = eventId;
        this.name = name;
        this.date = date;
        this.maxParticipants = maxParticipants;
        participants = new ArrayList<>();
        attendanceRecords = new ArrayList<>();
        cancelled = false;
    }

    public void registerParticipant(Participant participant) throws EventFullException {

        if (cancelled) {
            System.out.println("Event is cancelled.");
            return;
        }

        if (isFull()) {
            throw new EventFullException(
                name + " is full (max " + maxParticipants + "). Cannot register "
                + participant.getDisplayName()
            );
        }

        if (participants.contains(participant)) {
            System.out.println("Participant already registered.");
            return;
        }

        participants.add(participant);
        participant.register(this);

        System.out.println(
            participant.getDisplayName() +
            " registered successfully."
        );
    }

    public void removeParticipant(Participant participant) {

        if (participants.remove(participant)) {
            participant.unregister(this);

            System.out.println(
                participant.getDisplayName() +
                " removed successfully."
            );
        } else {
            System.out.println("Participant not found.");
        }
    }

    public boolean isFull() {
        return participants.size() >= maxParticipants;
    }

    public void addAttendance(Attendance attendance) {
        attendanceRecords.add(attendance);
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Venue getVenue() {
        return venue;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public List<Attendance> getAttendanceRecords() {
        return attendanceRecords;
    }

    public int getParticipantCount() {
        return participants.size();
    }

    public void cancel() {
        cancelled = true;
        System.out.println(name + " cancelled.");
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public String getEventId() {
        return eventId;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public abstract void conductEvent();

    public abstract String getEventType();
}
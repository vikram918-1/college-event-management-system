package com.eventorg.event;

import java.time.Duration;
import java.time.LocalTime;
import com.eventorg.participant.Participant;

public class Attendance {

    private Participant participant;
    private String date;
    private boolean present;
    private String checkInTime;
    private String checkOutTime;

    public Attendance(Participant participant, String date) {
        this.participant = participant;
        this.date = date;
        present = false;
    }

    public void markPresent(String checkInTime) {
        present = true;
        this.checkInTime = checkInTime;
    }

    public void markAbsent() {
        present = false;
        checkInTime = null;
        checkOutTime = null;
    }

    public void checkOut(String checkOutTime) {
        if (present) {
            this.checkOutTime = checkOutTime;
        }
    }

    public int calculateDuration() {

        if (!present || checkInTime == null || checkOutTime == null) {
            return 0;
        }

        LocalTime checkIn = LocalTime.parse(checkInTime);
        LocalTime checkOut = LocalTime.parse(checkOutTime);

        return (int) Duration.between(checkIn, checkOut).toMinutes();
    }

    public boolean isPresent() {
        return present;
    }

    public Participant getParticipant() {
        return participant;
    }

    public String getDate() {
        return date;
    }

    public String getCheckInTime() {
        return checkInTime;
    }

    public String getCheckOutTime() {
        return checkOutTime;
    }
}
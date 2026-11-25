package com.eventorg.participant;

import com.eventorg.event.Event;
import java.time.LocalDate;
import com.eventorg.exception.InvalidRegistrationException;

public class Registration {

    private String registrationId;
    private Participant participant;
    private Event event;
    private String registrationDate;
    private String status;

    private static int count = 1;

    public Registration(Participant participant, Event event) {
        registrationId = "REG" + count++;
        this.participant = participant;
        this.event = event;
        registrationDate = LocalDate.now().toString();
        status = "Registered";
    }

    public void cancel() throws InvalidRegistrationException {
        if (status.equals("Cancelled")) {
            throw new InvalidRegistrationException(
                "Registration " + registrationId + " is already cancelled."
            );
        }
        status = "Cancelled";
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public Participant getParticipant() {
        return participant;
    }

    public Event getEvent() {
        return event;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public String getStatus() {
        return status;
    }
}
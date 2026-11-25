package com.eventorg.participant;

import java.util.ArrayList;
import java.util.List;
import com.eventorg.event.Event;
import com.eventorg.exception.InvalidRegistrationException;

public abstract class Participant {

    protected String participantId;
    protected List<Registration> registrations;

    public Participant(String participantId) {
        this.participantId = participantId;
        registrations = new ArrayList<>();
    }

    public void register(Event event) {
        Registration registration = new Registration(this, event);
        registrations.add(registration);
    }

    // removes active registrations for this event; cancelled ones are kept as history
    public void unregister(Event event) {
        registrations.removeIf(r -> r.getEvent() == event && r.getStatus().equals("Registered"));
    }

    public Registration getRegistration(String registrationId) throws InvalidRegistrationException {
        for (Registration r : registrations) {
            if (r.getRegistrationId().equalsIgnoreCase(registrationId)) {
                return r;
            }
        }
        throw new InvalidRegistrationException(
            "Invalid registration ID " + registrationId + " for " + getDisplayName()
        );
    }

    public String getParticipantId() {
        return participantId;
    }

    public List<Registration> getRegistrations() {
        return registrations;
    }

    public abstract String getDisplayName();
}
package com.eventorg.event;

import com.eventorg.exception.EventNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class EventManager<T extends Event> {

    private List<T> events = new ArrayList<>();
    private List<Venue> venues = new ArrayList<>();

    public void addEvent(T event) {
        events.add(event);
    }

    public void removeEvent(T event) {
        events.remove(event);
    }

    public void addVenue(Venue venue) {
        venues.add(venue);
    }

    public void removeVenue(Venue venue) {
        venues.remove(venue);
    }

    public void allocateVenue(T event, Venue venue) {
        if (venue.isAvailable(event.getDate())) {
            event.setVenue(venue);
            venue.reserve(event.getDate());
            System.out.println("Venue allocated successfully.");
        } else {
            System.out.println("Venue not available on " + event.getDate());
        }
    }

    public void releaseVenue(T event) {
        if (event.getVenue() != null) {
            event.getVenue().release(event.getDate());
            System.out.println("Venue released.");
        }
    }

    public T searchEvent(String id) throws EventNotFoundException {
        for (T event : events) {
            if (event.getEventId().equals(id)) {
                return event;
            }
        }
        throw new EventNotFoundException("No event found with ID " + id);
    }

    public void cancelEvent(String id) throws EventNotFoundException {
        T event = searchEvent(id);
        event.cancel();
        releaseVenue(event);
    }

    public void displayEvents() {
        for (T event : events) {
            System.out.println(event.getName() + " - " + event.getEventType());
        }
    }

    public List<T> getEvents() {
        return events;
    }

    public List<Venue> getVenues() {
        return venues;
    }
}

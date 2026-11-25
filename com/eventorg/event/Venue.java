package com.eventorg.event;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Venue {

    private String venueId;
    private String name;
    private String location;
    private int capacity;
    private List<String> facilities;
    private Set<String> bookedDates;

    public Venue(String venueId, String name,
                 String location, int capacity,
                 List<String> facilities) {

        this.venueId = venueId;
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.facilities = facilities;
        bookedDates = new HashSet<>();
    }

    public boolean isAvailable(String date) {
        return !bookedDates.contains(date);
    }

    public void reserve(String date) {

        if (isAvailable(date)) {
            bookedDates.add(date);
            System.out.println(
                "Venue reserved for " + date
            );
        } else {
            System.out.println(
                "Venue already booked on " + date
            );
        }
    }

    public void release(String date) {
        bookedDates.remove(date);
    }

    public boolean hasFacility(String facility) {
        return facilities.contains(facility);
    }

    public String getVenueId() {
        return venueId;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<String> getFacilities() {
        return facilities;
    }
}
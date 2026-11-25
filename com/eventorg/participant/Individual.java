package com.eventorg.participant;

public class Individual extends Participant {

    private String name;
    private String email;
    private String phone;

    public Individual(String participantId, String name,
                      String email, String phone) {

        super(participantId);
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public String getDisplayName() {
        return name;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
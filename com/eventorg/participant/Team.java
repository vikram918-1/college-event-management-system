package com.eventorg.participant;

import java.util.ArrayList;
import java.util.List;

public class Team extends Participant {

    private String teamName;
    private List<Individual> members;

    public Team(String participantId, String teamName) {
        super(participantId);
        this.teamName = teamName;
        members = new ArrayList<>();
    }

    public void addMember(Individual member) {
        members.add(member);
    }

    public void removeMember(Individual member) {
        members.remove(member);
    }

    @Override
    public String getDisplayName() {
        return teamName;
    }

    public String getTeamName() {
        return teamName;
    }

    public List<Individual> getMembers() {
        return members;
    }
}
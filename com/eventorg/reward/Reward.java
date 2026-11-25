package com.eventorg.reward;

import com.eventorg.event.Event;
import com.eventorg.exception.InvalidRewardException;
import com.eventorg.participant.Participant;

public abstract class Reward {

    private String rewardId;
    private String name;
    private String description;
    private Event event;
    private Participant assignedTo;
    private int position;
    private RewardPolicy policy;

    public Reward(String rewardId, String name, String description,
                  Event event, RewardPolicy policy) throws InvalidRewardException {

        if (name == null || name.trim().isEmpty()) {
            throw new InvalidRewardException("Reward name cannot be empty.");
        }

        this.rewardId = rewardId;
        this.name = name;
        this.description = description;
        this.event = event;
        this.policy = policy;
    }

    public void assignTo(Participant participant, int position) {
        this.assignedTo = participant;
        this.position = position;
    }

    public boolean isAssigned() {
        return assignedTo != null;
    }

    // each type of reward calculates its own value
    public abstract double calculateValue();

    public String getRewardId() {
        return rewardId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Event getEvent() {
        return event;
    }

    public Participant getAssignedTo() {
        return assignedTo;
    }

    public int getPosition() {
        return position;
    }

    public RewardPolicy getPolicy() {
        return policy;
    }
}

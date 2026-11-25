package com.eventorg.reward;

import com.eventorg.exception.InvalidRewardException;
import com.eventorg.participant.Participant;

import java.util.ArrayList;
import java.util.List;

public class RewardManager<T extends Reward> {

    private List<T> rewards = new ArrayList<>();
    private List<RewardPolicy> policies = new ArrayList<>();

    public void addReward(T reward) {
        rewards.add(reward);
    }

    public void removeReward(T reward) {
        rewards.remove(reward);
    }

    public void addPolicy(RewardPolicy policy) {
        policies.add(policy);
    }

    public void removePolicy(RewardPolicy policy) {
        policies.remove(policy);
    }

    public void assignReward(Participant participant, T reward, int position)
            throws InvalidRewardException {

        if (reward.isAssigned()) {
            throw new InvalidRewardException(
                reward.getName() + " is already assigned to " +
                reward.getAssignedTo().getDisplayName()
            );
        }

        RewardPolicy policy = reward.getPolicy();

        if (policy != null) {
            if (!policy.isEligible(position)) {
                throw new InvalidRewardException(
                    participant.getDisplayName() + " (position " + position +
                    ") is not eligible for " + reward.getName() +
                    " - only top " + policy.getMinimumPosition() + " qualify"
                );
            }
            if (!policy.validateReward(reward)) {
                throw new InvalidRewardException(
                    reward.getName() + " is worth " + reward.calculateValue() +
                    ", which exceeds the limit of " + policy.getRewardLimit()
                );
            }
        }

        reward.assignTo(participant, position);
        System.out.println(reward.getName() + " assigned to " + participant.getDisplayName());
    }

    public T findReward(String id) {
        for (T reward : rewards) {
            if (reward.getRewardId().equals(id)) {
                return reward;
            }
        }
        return null;
    }

    public List<T> getRewards() {
        return rewards;
    }

    public List<RewardPolicy> getPolicies() {
        return policies;
    }
}

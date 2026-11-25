package com.eventorg.reward;

import com.eventorg.event.Event;
import com.eventorg.exception.InvalidRewardException;

public class CashReward extends Reward {

    private double amount;
    private String currency;

    public CashReward(String rewardId, String name, String description,
                      Event event, RewardPolicy policy,
                      double amount, String currency) throws InvalidRewardException {

        super(rewardId, name, description, event, policy);

        if (amount < 0) {
            throw new InvalidRewardException("Cash amount cannot be negative: " + amount);
        }

        this.amount = amount;
        this.currency = currency;
    }

    public void processPayment() {
        if (!isAssigned()) {
            System.out.println(getName() + " has not been assigned to anyone yet.");
            return;
        }
        System.out.println(
            "Paid " + currency + " " + amount + " to " +
            getAssignedTo().getDisplayName()
        );
    }

    @Override
    public double calculateValue() {
        return amount;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }
}

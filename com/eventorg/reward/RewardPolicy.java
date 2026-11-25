package com.eventorg.reward;

public class RewardPolicy {

    private String policyId;
    private int minimumPosition;
    private String eligibilityCriteria;
    private double rewardLimit;

    public RewardPolicy(String policyId, int minimumPosition,
                        String eligibilityCriteria, double rewardLimit) {

        this.policyId = policyId;
        this.minimumPosition = minimumPosition;
        this.eligibilityCriteria = eligibilityCriteria;
        this.rewardLimit = rewardLimit;
    }

    // e.g. minimumPosition = 3 means only positions 1, 2 and 3 qualify
    public boolean isEligible(int position) {
        return position >= 1 && position <= minimumPosition;
    }

    // a reward must not be worth more than the policy allows
    public boolean validateReward(Reward reward) {
        return reward.calculateValue() <= rewardLimit;
    }

    public String getPolicyId() {
        return policyId;
    }

    public int getMinimumPosition() {
        return minimumPosition;
    }

    public String getEligibilityCriteria() {
        return eligibilityCriteria;
    }

    public double getRewardLimit() {
        return rewardLimit;
    }
}

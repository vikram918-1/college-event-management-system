package com.eventorg.reward;

import com.eventorg.event.Event;
import com.eventorg.exception.InvalidRewardException;

public class CertificateReward extends Reward {

    private String certificateType;
    private String issuer;

    public CertificateReward(String rewardId, String name, String description,
                             Event event, RewardPolicy policy,
                             String certificateType, String issuer) throws InvalidRewardException {

        super(rewardId, name, description, event, policy);
        this.certificateType = certificateType;
        this.issuer = issuer;
    }

    public void generateCertificate() {
        if (!isAssigned()) {
            System.out.println(getName() + " has not been assigned to anyone yet.");
            return;
        }
        System.out.println(
            certificateType + " certificate generated for " +
            getAssignedTo().getDisplayName() + " - " + getEvent().getName()
        );
    }

    // a certificate has no monetary value
    @Override
    public double calculateValue() {
        return 0;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public String getIssuer() {
        return issuer;
    }
}

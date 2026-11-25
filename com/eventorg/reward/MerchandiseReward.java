package com.eventorg.reward;

import com.eventorg.event.Event;
import com.eventorg.exception.InvalidRewardException;

public class MerchandiseReward extends Reward {

    private String itemName;
    private int quantity;
    private String size;
    private double unitPrice;

    public MerchandiseReward(String rewardId, String name, String description,
                             Event event, RewardPolicy policy,
                             String itemName, int quantity, String size,
                             double unitPrice) throws InvalidRewardException {

        super(rewardId, name, description, event, policy);

        if (quantity <= 0) {
            throw new InvalidRewardException("Quantity must be at least 1: " + quantity);
        }
        if (unitPrice < 0) {
            throw new InvalidRewardException("Unit price cannot be negative: " + unitPrice);
        }

        this.itemName = itemName;
        this.quantity = quantity;
        this.size = size;
        this.unitPrice = unitPrice;
    }

    public void dispatchItem() {
        if (!isAssigned()) {
            System.out.println(getName() + " has not been assigned to anyone yet.");
            return;
        }
        System.out.println(
            "Dispatched " + quantity + " x " + itemName + " (size " + size + ") to " +
            getAssignedTo().getDisplayName()
        );
    }

    @Override
    public double calculateValue() {
        return quantity * unitPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getSize() {
        return size;
    }

    public double getUnitPrice() {
        return unitPrice;
    }
}

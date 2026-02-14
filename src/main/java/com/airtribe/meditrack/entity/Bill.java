package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.strategy.BillingStrategy;

public class Bill {

    private String billId;
    private double baseAmount;
    private BillingStrategy strategy;

    public Bill(String billId, double baseAmount, BillingStrategy strategy) {
        this.billId = billId;
        this.baseAmount = baseAmount;
        this.strategy = strategy;
    }

    public double calculateTotal() {
        return strategy.calculate(baseAmount);
    }

    public String getBillId() {
        return billId;
    }

    @Override
    public String toString() {
        return "Bill ID: " + billId +
                ", Base: " + baseAmount +
                ", Total: " + calculateTotal();
    }
}

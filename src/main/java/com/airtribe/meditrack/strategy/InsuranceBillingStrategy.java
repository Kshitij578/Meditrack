package com.airtribe.meditrack.strategy;

import com.airtribe.meditrack.constants.Constants;

public class InsuranceBillingStrategy implements BillingStrategy {

    @Override
    public double calculate(double baseAmount) {

        double discounted = baseAmount * 0.8; // 20% discount
        return discounted + (discounted * Constants.TAX_RATE);
    }
}

package com.airtribe.meditrack.strategy;

import com.airtribe.meditrack.constants.Constants;

public class StandardBillingStrategy implements BillingStrategy {

    @Override
    public double calculate(double baseAmount) {
        return baseAmount + (baseAmount * Constants.TAX_RATE);
    }
}

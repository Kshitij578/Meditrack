package com.airtribe.meditrack.strategy;

import com.airtribe.meditrack.constants.Constants;

public class EmergencyBillingStrategy implements BillingStrategy {

    @Override
    public double calculate(double baseAmount) {

        double emergencyCharge = baseAmount + 500;
        return emergencyCharge + (emergencyCharge * Constants.TAX_RATE);
    }
}

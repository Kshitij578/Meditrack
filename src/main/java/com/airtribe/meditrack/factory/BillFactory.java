package com.airtribe.meditrack.factory;

import com.airtribe.meditrack.entity.Bill;
import com.airtribe.meditrack.strategy.*;

public class BillFactory {

    public static Bill createBill(String type, String billId, double amount) {

        BillingStrategy strategy;

        switch (type.toLowerCase()) {
            case "insurance":
                strategy = new InsuranceBillingStrategy();
                break;

            case "emergency":
                strategy = new EmergencyBillingStrategy();
                break;

            default:
                strategy = new StandardBillingStrategy();
        }

        return new Bill(billId, amount, strategy);
    }
}

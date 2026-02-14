package com.airtribe.meditrack.interfacee;

public interface Payable {

    double calculateTotal();

    default void printReceipt() {
        System.out.println("Payment processed successfully.");
    }
}

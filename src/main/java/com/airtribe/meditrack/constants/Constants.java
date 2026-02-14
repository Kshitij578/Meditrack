package com.airtribe.meditrack.constants;

public class Constants {

    public static final double TAX_RATE = 0.18;
    public static final String PATIENT_FILE = "patients.csv";
    public static final String DOCTOR_FILE = "doctors.csv";
    public static final String APPOINTMENT_FILE = "appointments.csv";

    public static int globalCounter;

    static {
        globalCounter = 1000;
        System.out.println("MediTrack Configuration Loaded Successfully.");
    }

    private Constants() {
        // Prevent instantiation
    }
}
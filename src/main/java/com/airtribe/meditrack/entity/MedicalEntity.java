package com.airtribe.meditrack.entity;

public abstract class MedicalEntity {

    protected String id;

    static {
        System.out.println("Hospital Management System Initialized...");
    }

    public MedicalEntity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public abstract void displayInfo();
}
package com.airtribe.meditrack.entity;

import java.util.ArrayList;
import java.util.List;

public class Patient extends Person implements Cloneable {

    private List<String> medicalHistory;

    public Patient(String id, String name, int age) {
        super(id, name, age);
        this.medicalHistory = new ArrayList<>();

    }

    public void addMedicalRecord(String record) {
        medicalHistory.add(record);
    }

    public List<String> getMedicalHistory() {
        return new ArrayList<>(medicalHistory);
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Patient ID: " + id +
                ", Name: " + name +
                ", Age: " + age);
    }

    @Override
    public Patient clone() {
        try {
            Patient cloned = (Patient) super.clone();
            cloned.medicalHistory = new ArrayList<>(this.medicalHistory); // deep copy
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Cloning failed");
        }
    }

    @Override
    public String toString() {
        return name + " (Age: " + age + ")";
    }

    public void setName(String newName) {
        this.name = name;
    }
}

package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.interfacee.Searchable;

import java.util.List;

public class Doctor extends Person implements Searchable {

    private Specialization specialization;
    private double consultationFee;

    public Doctor(String id, String name, int age,
                  Specialization specialization,
                  double consultationFee) {
        super(id, name, age);
        this.specialization = specialization;
        this.consultationFee = consultationFee;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Doctor ID: " + id +
                ", Name: " + name +
                ", Specialization: " + specialization +
                ", Fee: " + consultationFee);
    }

    @Override
    public String toString() {
        return name + " (" + specialization + ")";
    }

    @Override
    public List searchByName(String name) {
        return List.of();
    }

    public void setName(String newName) {
        this.name = name;
    }

}
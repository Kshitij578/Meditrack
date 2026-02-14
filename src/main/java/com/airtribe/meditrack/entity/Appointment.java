package com.airtribe.meditrack.entity;

import java.time.LocalDateTime;

public class Appointment extends MedicalEntity implements Cloneable {

    private Patient patient;
    private Doctor doctor;
    private LocalDateTime appointmentTime;
    private AppointmentStatus status;

    public Appointment(String id,
                       Patient patient,
                       Doctor doctor,
                       LocalDateTime appointmentTime) {
        super(id);
        this.patient = patient;
        this.doctor = doctor;
        this.appointmentTime = appointmentTime;
        this.status = AppointmentStatus.PENDING;
    }

    public void cancel() {
        this.status = AppointmentStatus.CANCELLED;
    }

    public void confirm() {
        this.status = AppointmentStatus.CONFIRMED;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    @Override
    public void displayInfo() {
        System.out.println("Appointment ID: " + id +
                ", Patient: " + patient.getName() +
                ", Doctor: " + doctor.getName() +
                ", Time: " + appointmentTime +
                ", Status: " + status);
    }

    @Override
    public Appointment clone() {
        try {
            Appointment cloned = (Appointment) super.clone();
            cloned.doctor = doctor;
            cloned.patient = this.patient.clone(); // deep clone patient
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Appointment cloning failed");
        }
    }
}

package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.*;
import com.airtribe.meditrack.exception.AppointmentNotFoundException;
import com.airtribe.meditrack.util.CSVUtil;
import com.airtribe.meditrack.constants.Constants;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


public class AppointmentService {

    private Map<String, Appointment> appointmentMap = new HashMap<>();

    // CREATE
    public void createAppointment(Appointment appointment) {

        Objects.requireNonNull(appointment, "Appointment cannot be null");

        if (appointmentMap.containsKey(appointment.getId())) {
            throw new IllegalArgumentException("Appointment already exists.");
        }

        appointmentMap.put(appointment.getId(), appointment);
        System.out.println("Appointment created successfully!");
    }

    // VIEW ALL
    public Appointment getAppointmentById(String id) {
        return appointmentMap.get(id);
    }
    public Collection<Appointment> getAllAppointments() {
        return appointmentMap.values();
    }

    // UPDATE
    public void confirmAppointment(String id) {

        Appointment appointment = appointmentMap.get(id);

        if (appointment != null) {
            appointment.setStatus(AppointmentStatus.CONFIRMED);
            System.out.println("Appointment confirmed!");
        } else {
            System.out.println("Appointment not found!");
        }
    }


    // CANCEL
    public void cancelAppointment(String id) {

        Appointment appointment = appointmentMap.get(id);

        if (appointment != null) {
            appointment.setStatus(AppointmentStatus.CANCELLED);
            System.out.println("Appointment cancelled!");
        } else {
            System.out.println("Appointment not found!");
        }
    }

    // DELETE
    public void removeAppointment(String id) {

        if (appointmentMap.remove(id) != null) {
            System.out.println("Appointment removed successfully!");
        } else {
            System.out.println("Appointment not found!");
        }
    }

    // Analytics – Appointments per Doctor
    public Map<String, Long> getAppointmentsPerDoctor() {
        return appointmentMap.values().stream()
                .collect(Collectors.groupingBy(
                        a -> a.getDoctor().getName(),
                        Collectors.counting()
                ));
    }

    // SEARCH

    public List<Appointment> searchByDoctor(String doctorId) {
        return appointmentMap.values().stream()
                .filter(a -> a.getDoctor().getId().equals(doctorId))
                .collect(Collectors.toList());
    }

    public List<Appointment> searchByPatient(String patientId) {
        return appointmentMap.values().stream()
                .filter(a -> a.getPatient().getId().equals(patientId))
                .collect(Collectors.toList());
    }

    public List<Appointment> searchByStatus(AppointmentStatus status) {
        return appointmentMap.values().stream()
                .filter(a -> a.getStatus() == status)
                .collect(Collectors.toList());
    }

    // ANALYTICS
    public long countConfirmedAppointments() {
        return appointmentMap.values().stream()
                .filter(a -> a.getStatus() == AppointmentStatus.CONFIRMED)
                .count();
    }

    public void saveToFile() {

        List<String> lines = appointmentMap.values().stream()
                .map(a -> a.getId() + "," +
                        a.getPatient().getId() + "," +
                        a.getDoctor().getId() + "," +
                        a.getAppointmentTime() + "," +
                        a.getStatus())
                .toList();

        CSVUtil.writeToFile(Constants.APPOINTMENT_FILE, lines);
        System.out.println("Appointments saved successfully!");
    }



}

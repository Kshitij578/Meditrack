package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Specialization;
import com.airtribe.meditrack.interfacee.Searchable;
import com.airtribe.meditrack.util.Validator;
import com.airtribe.meditrack.util.CSVUtil;
import com.airtribe.meditrack.constants.Constants;

import java.util.*;
import java.util.stream.Collectors;

public class DoctorService implements Searchable<Doctor> {

    private final Map<String, Doctor> doctorMap = new HashMap<>();
    private int doctorIdCounter = 1;

    // CREATE
    public void addDoctor(Doctor doctor) {
        Objects.requireNonNull(doctor, "Doctor cannot be null");

        Validator.validateName(doctor.getName());
        Validator.validateAge(doctor.getAge());

        if (doctorMap.containsKey(doctor.getId())) {
            throw new IllegalArgumentException("Doctor with this ID already exists.");
        }

        doctorMap.put(doctor.getId(), doctor);
        System.out.println("Doctor added successfully!");

    }

    // UPDATE
    public Doctor updateDoctorById(int id, String newName) {
        Doctor doctor = doctorMap.get(id);
        if (doctor != null) {
            doctor.setName(newName);
            System.out.println("Doctor updated successfully!");
        } else {
            System.out.println("Doctor not found!");
        }
        return doctor;
    }

    // READ
    public Doctor getDoctorById(String id) {
        return doctorMap.get(id);
    }
    public Collection<Doctor> getAllDoctors() {
        return doctorMap.values();
    }

    // DELETE
    public void removeDoctor(String id) {
        if (doctorMap.remove(id) != null) {
            System.out.println("Doctor removed successfully!");
        } else {
            System.out.println("Doctor not found!");
        }
    }

    // SEARCH by Name (Overriding from Searchable)
    @Override
    public List<Doctor> searchByName(String name) {
        return doctorMap.values().stream()
                .filter(d -> d.getName()
                        .toLowerCase()
                        .contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Filter by Specialization (Streams Bonus)
    public List<Doctor> filterBySpecialization(Specialization specialization) {
        return doctorMap.values().stream()
                .filter(d -> d.getSpecialization() == specialization)
                .collect(Collectors.toList());
    }

    // Analytics – Average Fee
    public double getAverageConsultationFee() {
        return doctorMap.values().stream()
                .mapToDouble(Doctor::getConsultationFee)
                .average()
                .orElse(0);
    }

    public Optional<Doctor> getHighestFeeDoctor() {

        return doctorMap.values().stream()
                .max(Comparator.comparingDouble(Doctor::getConsultationFee));
    }

    public void saveToFile() {

        List<String> lines = doctorMap.values().stream()
                .map(d -> d.getId() + "," +
                        d.getName() + "," +
                        d.getAge() + "," +
                        d.getSpecialization() + "," +
                        d.getConsultationFee())
                .toList();

        CSVUtil.writeToFile(Constants.DOCTOR_FILE, lines);
        System.out.println("Doctors saved to file successfully!");
    }

    public void loadFromFile() {

        List<String[]> data = CSVUtil.readFromFile(Constants.DOCTOR_FILE);
        for(String[] row : data){
        if (row.length < 5) continue;

        try {

            Doctor doctor = new Doctor(
                    row[0],
                    row[1],
                    Integer.parseInt(row[2]),
                    Specialization.valueOf(row[3]),
                    Double.parseDouble(row[4])
            );

            doctorMap.put(doctor.getId(), doctor);

        } catch (Exception e) {
            System.out.println("Skipping invalid row in CSV file.");
        }
    }

        System.out.println("Doctors loaded from file successfully!");
        }
    }


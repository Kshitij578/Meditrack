package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.interfacee.Searchable;
import com.airtribe.meditrack.util.Validator;
import com.airtribe.meditrack.util.CSVUtil;
import com.airtribe.meditrack.constants.Constants;

import java.util.*;
import java.util.stream.Collectors;

public class PatientService implements Searchable<Patient> {

    private Map<String, Patient> patientMap = new HashMap<>();

    // CREATE
    public void addPatient(Patient patient) {
        Objects.requireNonNull(patient, "Patient cannot be null");

        Validator.validateName(patient.getName());
        Validator.validateAge(patient.getAge());

        if (patientMap.containsKey(patient.getId())) {
            throw new IllegalArgumentException("Patient with this ID already exists.");
        }

        patientMap.put(patient.getId(), patient);
        System.out.println("Patient added successfully!");
    }

    // UPDATE
    public Patient updatePatientById(String id,
                                     String newName) {

        Patient patient = patientMap.get(id);

        if (patient == null) {
            System.out.println("Patient not found!");
            return null;
        }

        if (newName != null) {
            Validator.validateName(newName);
            patient.setName(newName);
        }

        System.out.println("Patient updated successfully!");
        return patient;
    }

        // READ by ID
    public Patient searchPatient(String id) {
        return patientMap.get(id);
    }
    public Collection<Patient> getAllPatients() {
        return patientMap.values();
    }

    // Overloaded – Search by Name
    @Override
    public List<Patient> searchByName(String name) {
        return patientMap.values().stream()
                .filter(p -> p.getName()
                        .toLowerCase()
                        .contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Overloaded – Search by Age
    public List<Patient> searchPatient(int age) {
        return patientMap.values().stream()
                .filter(p -> p.getAge() == age)
                .collect(Collectors.toList());
    }

    public void removePatient(String id) {
        if (patientMap.remove(id) != null) {
            System.out.println("Patient removed successfully!");
        } else {
            System.out.println("Patient not found!");
        }
    }

    // analytics
    public double getAverageAge() {
        return patientMap.values().stream()
                .mapToInt(Patient::getAge)
                .average()
                .orElse(0.0);
    }

    public void saveToFile() {

        List<String> lines = patientMap.values().stream()
                .map(p -> String.join(",",p.getId(),
                        p.getName()))
                .toList();

        CSVUtil.writeToFile(Constants.PATIENT_FILE, lines);

        System.out.println("Patients saved to file successfully!");
    }

    public void loadFromFile() {

        List<String[]> data = CSVUtil.readFromFile(Constants.PATIENT_FILE);

        for (String[] row : data) {

            if (row.length < 4) continue;

            try {
                Patient patient = new Patient(
                        row[0],
                        row[1],
                        Integer.parseInt(row[2])
                );

                patientMap.put(patient.getId(), patient);

            } catch (Exception e) {
                System.out.println("Skipping invalid row in Patient CSV.");
            }
        }

        System.out.println("Patients loaded successfully!");
    }

}

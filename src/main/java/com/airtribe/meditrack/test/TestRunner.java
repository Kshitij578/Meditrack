package com.airtribe.meditrack.test;

import com.airtribe.meditrack.entity.*;
import com.airtribe.meditrack.factory.BillFactory;
import com.airtribe.meditrack.service.*;
import com.airtribe.meditrack.util.IdGenerator;

import java.time.LocalDateTime;

public class TestRunner {

    public static void main(String[] args) {

        DoctorService doctorService = new DoctorService();
        PatientService patientService = new PatientService();
        AppointmentService appointmentService = new AppointmentService();

        // Create Doctor
        Doctor doctor = new Doctor(
                IdGenerator.getInstance().generateId(),
                "Dr. Sharma",
                45,
                Specialization.CARDIOLOGIST,
                1500
        );

        doctorService.addDoctor(doctor);

        // Create Patient
        Patient patient = new Patient(
                IdGenerator.getInstance().generateId(),
                "Rahul",
                30
        );

        patientService.addPatient(patient);

        // Create Appointment
        Appointment appointment = new Appointment(
                IdGenerator.getInstance().generateId(),
                patient,
                doctor,
                LocalDateTime.now()
        );

        appointmentService.createAppointment(appointment);

        // Generate Bill
        Bill bill = BillFactory.createBill(
                "insurance",
                IdGenerator.getInstance().generateId(),
                2000
        );

        System.out.println("Generated Bill: " + bill);

        System.out.println("Appointments per doctor: "
                + appointmentService.getAppointmentsPerDoctor());
    }
}

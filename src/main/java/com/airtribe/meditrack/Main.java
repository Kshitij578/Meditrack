package com.airtribe.meditrack;

import com.airtribe.meditrack.entity.*;
import com.airtribe.meditrack.factory.BillFactory;
import com.airtribe.meditrack.service.*;
import com.airtribe.meditrack.util.AIHelper;
import com.airtribe.meditrack.util.IdGenerator;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Scanner;

public class Main {


    private static DoctorService doctorService = new DoctorService();
    private static PatientService patientService = new PatientService();
    private static AppointmentService appointmentService = new AppointmentService();

    private static Scanner scanner = new Scanner(System.in);

    private static int doctorIdCounter = 1;
    private static int patientIdCounter = 1;
    private static int appointmentIdCounter = 1;
    private static int billIdCounter = 1;

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("--loadData")) {
            doctorService.loadFromFile();
            patientService.loadFromFile();
            System.out.println("Data loaded successfully.");
        }

        System.out.println("===== Welcome to MediTrack =====");

        while (true) {
            printMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            try {
                switch (choice) {
                    case 1 -> addDoctor();
                    case 2 -> addPatient();
                    case 3 -> createAppointment();
                    case 4 -> cancelAppointment();
                    case 5 -> generateBill();
                    case 6 -> showAnalytics();
                    case 7 -> recommendDoctor();
                    case 0 -> {
                        System.out.println("Exiting MediTrack...");
                        return;
                    }
                    default -> System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void printMenu() {
        System.out.println("""
                1. Add Doctor
                2. Add Patient
                3. Create Appointment
                4. Cancel Appointment
                5. Generate Bill
                6. View Analytics
                7. AI Doctor Recommendation
                0. Exit
                """);
        System.out.print("Choose option: ");
    }

    // ================= DOCTOR =================

    private static void addDoctor() {

        System.out.print("Doctor Name: ");
        String name = scanner.nextLine();

        System.out.print("Age: ");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.print("Specialization (CARDIOLOGIST/DERMATOLOGIST/ORTHOPEDIC/NEUROLOGIST/GENERAL_PHYSICIAN): ");
        Specialization specialization =
                Specialization.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Consultation Fee: ");
        double fee = Double.parseDouble(scanner.nextLine());

        String id = IdGenerator.getInstance().generateId();

        Doctor doctor = new Doctor(id, name, age, specialization, fee);
        doctorService.addDoctor(doctor);

        System.out.println("Doctor added successfully!");
    }

    private static void getDoctorById() {
        doctorService.getAllDoctors().forEach(Doctor::displayInfo);
    }

    private static void updateDoctorById() {
        System.out.print("Enter Doctor ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Doctor doctor = doctorService.getDoctorById(String.valueOf(id));
        if (doctor != null) {
            System.out.print("Enter New Name: ");
            doctor.name = scanner.nextLine();
            System.out.println("Doctor updated!");
        } else {
            System.out.println("Doctor not found!");
        }
    }

    // ================= PATIENT =================

    private static void addPatient() {

        System.out.print("Patient Name: ");
        String name = scanner.nextLine();

        System.out.print("Age: ");
        int age = Integer.parseInt(scanner.nextLine());

        String id = IdGenerator.getInstance().generateId();

        Patient patient = new Patient(id, name, age);
        patientService.addPatient(patient);

        System.out.println("Patient added successfully!");
    }

    // ================= APPOINTMENT =================

    private static void createAppointment() {

        System.out.print("Patient ID: ");
        String patientId = scanner.nextLine();

        System.out.print("Doctor ID: ");
        String doctorId = scanner.nextLine();

        Patient patient = patientService.searchPatient(patientId);
        Doctor doctor = doctorService.getDoctorById(String.valueOf(Integer.parseInt(doctorId)));

        if (patient == null || doctor == null) {
            System.out.println("Invalid Patient or Doctor ID.");
            return;
        }

        String id = IdGenerator.getInstance().generateId();

        Appointment appointment = new Appointment(
                id,
                patient,
                doctor,
                LocalDateTime.now()
        );

        appointmentService.createAppointment(appointment);

        System.out.println("Appointment created successfully!");
    }

    private static void cancelAppointment() {

        System.out.print("Appointment ID: ");
        String id = scanner.nextLine();

        appointmentService.cancelAppointment(id);
        System.out.println("Appointment cancelled successfully!");
    }

    // ================= BILLING =================

    private static void generateBill() {

        System.out.print("Enter base amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        System.out.print("Billing Type (standard/insurance/emergency): ");
        String type = scanner.nextLine();

        String billId = IdGenerator.getInstance().generateId();

        Bill bill = BillFactory.createBill(type, billId, amount);

        System.out.println(bill);

        BillSummary summary =
                new BillSummary(bill.getBillId(), bill.calculateTotal());

        System.out.println("Immutable Summary: " + summary);
    }

    // ================= ANALYTICS =================

    private static void showAnalytics() {

        System.out.println("Average Doctor Fee: "
                + doctorService.getAverageConsultationFee());

        Map<String, Long> map =
                appointmentService.getAppointmentsPerDoctor();

        System.out.println("Appointments Per Doctor:");
        map.forEach((doctor, count) ->
                System.out.println(doctor + " -> " + count));
    }

    // ================= AI =================

    private static void recommendDoctor() {

        System.out.print("Enter symptom: ");
        String symptom = scanner.nextLine();

        Specialization recommendation =
                AIHelper.recommendDoctor(symptom);

        System.out.println("Recommended Specialization: " + recommendation);
    }
}

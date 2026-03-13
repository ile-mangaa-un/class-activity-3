import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Doctor> doctors = new ArrayList<>();
    private static List<Nurse> nurses = new ArrayList<>();
    private static List<Patient> patients = new ArrayList<>();
    private static List<Ward> wards = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Pre-create some wards for demonstration
        wards.add(new Ward(101, 2, "ICU"));
        wards.add(new Ward(102, 3, "General"));

        boolean exit = false;
        while (!exit) {
            printMenu();
            int choice = getIntInput("Enter your choice: ");
            try {
                switch (choice) {
                    case 1:
                        addDoctor();
                        break;
                    case 2:
                        addNurse();
                        break;
                    case 3:
                        addPatient();
                        break;
                    case 4:
                        assignPatientToWard();
                        break;
                    case 5:
                        displayAll();
                        break;
                    case 6:
                        saveDataDemo();
                        break;
                    case 7:
                        deleteDataDemo();
                        break;
                    case 8:
                        exit = true;
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 8.");
                }
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n==== Hospital Management System ====");
        System.out.println("1. Add Doctor");
        System.out.println("2. Add Nurse");
        System.out.println("3. Add Patient");
        System.out.println("4. Assign Patient to Ward");
        System.out.println("5. Display All Records");
        System.out.println("6. Save Data (Demo)");
        System.out.println("7. Delete Data (Demo)");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");
    }

    // Helper method to safely read an integer with exception handling
    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = scanner.nextInt();
                scanner.nextLine(); // consume newline
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.nextLine(); // clear the invalid input
            }
        }
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static void addDoctor() {
        try {
            System.out.println("\n--- Add Doctor ---");
            String name = getStringInput("Name: ");
            int age = getIntInput("Age: ");
            String contact = getStringInput("Contact Info: ");
            String empId = getStringInput("Employee ID: ");
            String specialization = getStringInput("Specialization: ");

            Doctor doctor = new Doctor(name, age, contact, empId, specialization);
            doctors.add(doctor);
            System.out.println("Doctor added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error creating doctor: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    private static void addNurse() {
        try {
            System.out.println("\n--- Add Nurse ---");
            String name = getStringInput("Name: ");
            int age = getIntInput("Age: ");
            String contact = getStringInput("Contact Info: ");
            String empId = getStringInput("Employee ID: ");
            String department = getStringInput("Department: ");

            Nurse nurse = new Nurse(name, age, contact, empId, department);
            nurses.add(nurse);
            System.out.println("Nurse added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error creating nurse: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    private static void addPatient() {
        try {
            System.out.println("\n--- Add Patient ---");
            String name = getStringInput("Name: ");
            int age = getIntInput("Age: ");
            String contact = getStringInput("Contact Info: ");
            String patientId = getStringInput("Patient ID: ");
            String ailment = getStringInput("Ailment: ");

            Patient patient = new Patient(name, age, contact, patientId, ailment);
            patients.add(patient);
            System.out.println("Patient added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error creating patient: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    private static void assignPatientToWard() {
        try {
            if (patients.isEmpty()) {
                System.out.println("No patients available. Please add a patient first.");
                return;
            }
            if (wards.isEmpty()) {
                System.out.println("No wards available.");
                return;
            }

            System.out.println("\n--- Assign Patient to Ward ---");
            // Select patient
            System.out.println("Select patient:");
            for (int i = 0; i < patients.size(); i++) {
                System.out.println((i + 1) + ". " + patients.get(i).getName() + " (ID: " + patients.get(i).getPatientID() + ")");
            }
            int patientIndex = getIntInput("Enter patient number: ") - 1;
            if (patientIndex < 0 || patientIndex >= patients.size()) {
                System.out.println("Invalid patient selection.");
                return;
            }
            Patient selectedPatient = patients.get(patientIndex);

            // Select ward
            System.out.println("Select ward:");
            for (int i = 0; i < wards.size(); i++) {
                Ward w = wards.get(i);
                System.out.println((i + 1) + ". Ward " + w.getWardNumber() + " (" + w.getWardType() + ") Capacity: " + w.getCapacity());
            }
            int wardIndex = getIntInput("Enter ward number: ") - 1;
            if (wardIndex < 0 || wardIndex >= wards.size()) {
                System.out.println("Invalid ward selection.");
                return;
            }
            Ward selectedWard = wards.get(wardIndex);

            // Assign
            if (selectedWard.checkAvailability()) {
                selectedWard.assign();
                System.out.println("Patient " + selectedPatient.getName() + " assigned to ward " + selectedWard.getWardNumber());
            } else {
                System.out.println("Ward is full. Cannot assign.");
            }
        } catch (Exception e) {
            System.out.println("Error during assignment: " + e.getMessage());
        }
    }

    private static void displayAll() {
        System.out.println("\n--- All Doctors ---");
        if (doctors.isEmpty()) System.out.println("None");
        else for (Doctor d : doctors) {
            System.out.println(d.getName() + " (" + d.getEmployeeID() + ") - " + d.getSpecialization());
        }

        System.out.println("\n--- All Nurses ---");
        if (nurses.isEmpty()) System.out.println("None");
        else for (Nurse n : nurses) {
            System.out.println(n.getName() + " (" + n.getEmployeeID() + ") - " + n.getDepartment());
        }

        System.out.println("\n--- All Patients ---");
        if (patients.isEmpty()) System.out.println("None");
        else for (Patient p : patients) {
            System.out.println(p.getName() + " (" + p.getPatientID() + ") - " + p.getAilment());
        }

        System.out.println("\n--- Wards ---");
        for (Ward w : wards) {
            System.out.println("Ward " + w.getWardNumber() + " (" + w.getWardType() + ") - Available: " + w.checkAvailability());
        }
        System.out.println("Total people in system: " + Person.getTotalPeopleCount());
        System.out.println("Occupied beds: " + Ward.getOccupiedBeds());
    }

    private static void saveDataDemo() {
        System.out.println("\n--- Save Data Demo ---");
        for (Doctor d : doctors) {
            try {
                d.saveData();
            } catch (Exception e) {
                System.out.println("Error saving doctor " + d.getName() + ": " + e.getMessage());
            }
        }
    }

    private static void deleteDataDemo() {
        System.out.println("\n--- Delete Data Demo ---");
        for (Doctor d : doctors) {
            try {
                d.deleteData();
            } catch (Exception e) {
                System.out.println("Error deleting doctor " + d.getName() + ": " + e.getMessage());
            }
        }
    }
}
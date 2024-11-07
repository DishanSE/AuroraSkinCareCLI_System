import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class AppointmentManager {
    private static ArrayList<Appointment> appointments = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void makeAppointment() {
        String name = AppointmentHelper.getValidInput("Enter Patient Name: ", "[a-zA-Z ]+",
                "Invalid name. Please enter a valid name (letters and spaces only).");

        String nic = AppointmentHelper.getValidInput("Enter Patient NIC: ", "[0-9]{9}[vVxX]|[0-9]{12}",
                "Invalid NIC. Please enter a valid NIC (9 digits + 'V/v/X/x' or 12 digits).");

        String email = AppointmentHelper.getValidInput("Enter Patient Email: ", "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$",
                "Invalid email. Please enter a valid email address.");

        String contactNumber = AppointmentHelper.getValidInput("Enter Patient Contact Number: ", "\\d{10}",
                "Invalid contact number. Please enter a 10-digit contact number.");
                
        System.out.println("\nAvailable Consulation Days:");
        System.out.println("Monday: 10:00 - 13:00");
        System.out.println("Wednesday: 14:00 - 17:00");
        System.out.println("Friday: 16:00 - 20:00");
        System.out.println("Saturday: 09:00 - 13:00");

        Patient patient = new Patient(name, nic, email, contactNumber);

        // Date Validation
        LocalDate date = null;
        while (true) {
            try {
                System.out.print("\nEnter Appointment Date (YYYY-MM-DD): ");
                date = LocalDate.parse(sc.nextLine());
                if (date.isBefore(LocalDate.now())) {
                    System.out.println("Cannot book appointments on past dates. Please choose a future date.");
                } else if (!AppointmentHelper.isValidConsultationDay(date)) {
                    System.out.println("Enter a valid date based on given days.");
                } else {
                    break;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter a valid date.");
            }
        }

        // Slot selection
        ArrayList<LocalTime> availableSlots = AppointmentHelper.getAvailableSlots(date.getDayOfWeek().getValue());
        LocalTime chosenTime = null;
        while (true) {
            System.out.println("Available Slots:");
            for (int i = 0; i < availableSlots.size(); i++) {
                System.out.println((i + 1) + ". " + availableSlots.get(i));
            }

            System.out.print("Choose a slot: ");
            int slot = sc.nextInt();
            sc.nextLine();

            if (slot >= 1 && slot <= availableSlots.size()) {
                chosenTime = availableSlots.get(slot - 1);
                System.out.println("Select Dermatologist: 1. Derm. Dishan, 2. Derm. Raj");
                String dermatologist = sc.nextInt() == 1 ? "Derm. Dishan" : "Derm. Raj";
                sc.nextLine();

                // Check for duplicate appointments using isSlotBooked
                if (!AppointmentHelper.isSlotBooked(appointments, dermatologist, date, chosenTime)) {
                    Appointment appointment = new Appointment(patient, date, chosenTime, dermatologist);
                    appointments.add(appointment);
                    System.out.println("Appointment created with ID: " + appointment.getAppointmentId());
                    break;
                } else {
                    System.out.println("Selected dermatologist is unavailable at this slot. Please choose another time.");
                }
            } else {
                System.out.println("Invalid slot selected.");
            }
        }
    }

    public static void updateAppointment() {
        System.out.print("Enter Appointment ID to update: ");
        int appointmentId = sc.nextInt();
        sc.nextLine();

        Appointment appointmentToUpdate = null;
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId() == appointmentId) {
                appointmentToUpdate = appointment;
                break;
            }
        }

        if (appointmentToUpdate == null) {
            System.out.println("Appointment not found.");
            return;
        }

        System.out.println("\nAvailable consultation days:");
        System.out.println("Monday: 10:00 - 13:00");
        System.out.println("Wednesday: 14:00 - 17:00");
        System.out.println("Friday: 16:00 - 20:00");
        System.out.println("Saturday: 09:00 - 13:00");

        // Date Validation
        LocalDate newDate = null;
        while (true) {
            try {
                System.out.print("\nEnter new appointment date (YYYY-MM-DD): ");
                newDate = LocalDate.parse(sc.nextLine());

                if (newDate.isBefore(LocalDate.now())) {
                    System.out.println("Cannot set appointments on past dates. Please choose a valid future date.");
                } else if (!AppointmentHelper.isValidConsultationDay(newDate)) {
                    System.out.println("Enter a valid date based on given days.");
                } else {
                    break;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter a valid date.");
            }
        }

        ArrayList<LocalTime> availableSlots = AppointmentHelper.getAvailableSlots(newDate.getDayOfWeek().getValue());
        LocalTime newTime = null;
        while (true) {
            System.out.println("Available Slots:");
            for (int i = 0; i < availableSlots.size(); i++) {
                System.out.println((i + 1) + ". " + availableSlots.get(i));
            }

            System.out.print("Choose a slot: ");
            int slot = sc.nextInt();
            sc.nextLine();

            if (slot >= 1 && slot <= availableSlots.size()) {
                newTime = availableSlots.get(slot - 1);

                System.out.println("Select Dermatologist: 1. Derm. Dishan, 2. Derm. Raj");
                String newDermatologist = sc.nextInt() == 1 ? "Derm. Dishan" : "Derm. Raj";
                sc.nextLine();

                if (!AppointmentHelper.isSlotBooked(appointments, newDermatologist, newDate, newTime)) {
                    // Update the existing appointment details with new values
                    appointmentToUpdate.setDate(newDate);
                    appointmentToUpdate.setTime(newTime);
                    appointmentToUpdate.setDermatologist(newDermatologist);

                    System.out.println("Appointment updated successfully with ID: " + appointmentToUpdate.getAppointmentId());
                    return;
                } else {
                    System.out.println("Selected dermatologist is unavailable at this slot. Please choose another time.");
                }
            } else {
                System.out.println("Invalid slot selected.");
            }
        }
    }


    public static void viewAppointmentsByDate() {
        LocalDate date = null;
        while (true) {
            try {
                System.out.print("Enter date (YYYY-MM-DD): ");
                date = LocalDate.parse(sc.nextLine());
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date. Please enter a valid date.");
            }
        }

        ArrayList<Appointment> dateAppointments = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getDate().equals(date)) {
                dateAppointments.add(appointment);
            }
        }

        if (dateAppointments.isEmpty()) {
            System.out.println("No appointments on that date.");
        } else {
            for (Appointment appointment : dateAppointments) {
                System.out.println("\nAppointment ID: " + appointment.getAppointmentId() + ", Patient: "
                        + appointment.getPatient().getName() + ", Date: " + appointment.getDate() +
                        ", Time: " + appointment.getTime() + ", Dermatologist: " + appointment.getDermatologist());
            }
        }
    }

    public static void searchAppointment() {
        System.out.print("Enter Patient Name or Appointment ID: ");
        String input = sc.nextLine();
        boolean found = false;

        for (Appointment appointment : appointments) {
            if (appointment.getPatient().getName().equalsIgnoreCase(input) ||
                    String.valueOf(appointment.getAppointmentId()).equals(input)) {
                System.out.println("Appointment found - ID: " + appointment.getAppointmentId() + ", Patient: "
                        + appointment.getPatient().getName() + ", Dermatologist: " + appointment.getDermatologist() +
                        ", Date: " + appointment.getDate() + ", Time: " + appointment.getTime());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No appointment(s) found.");
        }
    }
    
    public static void generateInvoice() {
        System.out.print("Enter Appointment ID to complete: ");
        int id = sc.nextInt();
        sc.nextLine();

        Appointment appointment = findAppointmentByID(id);
        if (appointment != null) {
            // Check if appointment has already completed treatment
            if (appointment.isCompleted()) {
                System.out.println(
                        "This appointment has already completed the treatment. Cannot generate another invoice.");
                return;
            }

            System.out.println(
                    "Select Treatment: 1. Acne Treatment, 2. Skin Whitening, 3. Mole Removal, 4. Laser Treatment");
            int treatmentChoice = sc.nextInt();
            sc.nextLine();

            String treatmentType = "";
            double basePrice = 0.00;

            switch (treatmentChoice) {
                case 1:
                    treatmentType = "Acne Treatment";
                    basePrice = 2750.00;
                    break;
                case 2:
                    treatmentType = "Skin Whitening";
                    basePrice = 7650.00;
                    break;
                case 3:
                    treatmentType = "Mole Removal";
                    basePrice = 3850.00;
                    break;
                case 4:
                    treatmentType = "Laser Treatment";
                    basePrice = 12500.00;
                    break;
                default:
                    System.out.println("Invalid Treatment Selected.");
                    return;
            }

            Invoice invoice = new Invoice(appointment, treatmentType, basePrice);
            invoice.generateInvoice();

            appointment.setCompleted(true);
        } else {
            System.out.println("Appointment not found. :(");
        }
    }

    public static Appointment findAppointmentByID(int id) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId() == id) {
                return appointment;
            }
        }
        return null;
    }
}

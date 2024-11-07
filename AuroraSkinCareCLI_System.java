import java.util.Scanner;

public class AuroraSkinCareCLI_System {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n-- Hello There! Welcome to --");
            System.out.println("------ Aurora Skin Care -----");
            System.out.println("1. Make an Appointment");
            System.out.println("2. Update Appointment");
            System.out.println("3. View Appointments by Date");
            System.out.println("4. Search for Appointment");
            System.out.println("5. Complete Appointment and Generate Invoice");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            try {
                int choice = Integer.parseInt(sc.nextLine().trim()); // Using parseInt to catch non-numeric input

                switch (choice) {
                    case 1:
                        AppointmentManager.makeAppointment();
                        break;

                    case 2:
                        AppointmentManager.updateAppointment();
                        break;

                    case 3:
                        AppointmentManager.viewAppointmentsByDate();
                        break;

                    case 4:
                        AppointmentManager.searchAppointment();
                        break;

                    case 5:
                        AppointmentManager.generateInvoice();
                        break;

                    case 6:
                        System.out.println("Good Bye! & Take Care...");
                        System.exit(0);

                    default:
                        System.out.println("Invalid option, please enter a number between 1 and 6.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number.");
            }
        }
    }
}
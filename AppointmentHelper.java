import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppointmentHelper {
    private static Scanner sc = new Scanner(System.in);

    public static String getValidInput(String prompt, String regex, String errorMessage) {
        String input;
        do {
            System.out.print(prompt);
            input = sc.nextLine().trim();
            if (input.isEmpty() || !input.matches(regex)) {
                System.out.println(errorMessage);
            }
        } while (input.isEmpty() || !input.matches(regex));
        return input;
    }

    public static boolean isValidConsultationDay(LocalDate date) {
        switch (date.getDayOfWeek()) {
            case MONDAY:
            case WEDNESDAY:
            case FRIDAY:
            case SATURDAY:
                return true;
            default:
                return false;
        }
    }

    public static boolean isSlotBooked(List<Appointment> appointments, String dermatologist, LocalDate date, LocalTime time) {
        for (Appointment appointment : appointments) {
            if (appointment.getDermatologist().equals(dermatologist) && appointment.getDate().equals(date)
                    && appointment.getTime().equals(time)) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<LocalTime> getAvailableSlots(int dayChoice) {
        ArrayList<LocalTime> slots = new ArrayList<>();
        switch (dayChoice) {
            case 1:
                addSlotsForDay(slots, LocalTime.of(10, 0), LocalTime.of(13, 0));
                break;
            case 3:
                addSlotsForDay(slots, LocalTime.of(14, 0), LocalTime.of(17, 0));
                break;
            case 5:
                addSlotsForDay(slots, LocalTime.of(16, 0), LocalTime.of(20, 0));
                break;
            case 6:
                addSlotsForDay(slots, LocalTime.of(9, 0), LocalTime.of(13, 0));
                break;
        }
        return slots;
    }

    private static void addSlotsForDay(ArrayList<LocalTime> slots, LocalTime start, LocalTime end) {
        while (start.isBefore(end)) {
            slots.add(start);
            start = start.plusMinutes(15);
        }
    }
}

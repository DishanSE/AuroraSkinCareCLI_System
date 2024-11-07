public class Invoice {
    private Appointment appointment;
    private String treatmentType;
    private double basePrice;
    private  double totalAmount;

    public Invoice(Appointment appointment, String treatmentType, double basePrice) {
        this.appointment = appointment;
        this.treatmentType = treatmentType;
        this.basePrice = basePrice;
        this.totalAmount = Treatment.calculatingFinalAmount(basePrice);
    }

    public void generateInvoice(){
        System.out.println("---------- INVOICE ----------");
        System.out.println("Appointment ID: " + appointment.getAppointmentId());
        System.out.println("Patient Name: " + appointment.getPatient().getName());
        System.out.println("Patient NIC: " + appointment.getPatient().getNic());
        System.out.println("Patient Contact Number: " + appointment.getPatient().getContactNumber());
        System.out.println("Dermatologist: " + appointment.getDermatologist());
        System.out.println("Date: " + appointment.getDate());
        System.out.println("Time: " + appointment.getTime());
        System.out.println("Registration Fee: LKR. " + appointment.getRegistrationFees());
        System.out.println("Treatment Type: " + treatmentType);
        System.out.println("Base Price: LKR. " + basePrice);
        System.out.println("Total Amount (with tax): LKR. " + totalAmount);
        System.out.println("-----------------------------");
    }
}

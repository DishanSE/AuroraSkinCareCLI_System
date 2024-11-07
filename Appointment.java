import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
    private static int nextId = 1;
    private int appointmentId;
    private Patient patient;
    private LocalDate date;
    private LocalTime time;
    private String dermatologist;
    private double registrationFees = 500.00;
    private boolean completed = false;

    
    public Appointment(Patient patient, LocalDate date, LocalTime time, String dermatologist){
        this.appointmentId = nextId++;
        this.patient = patient;
        this.date = date;
        this.time = time;
        this.dermatologist = dermatologist;
    }

    public boolean isCompleted() {
        return completed;
    }

    //Getters
    public int getAppointmentId(){
        return appointmentId;
    }

    public Patient getPatient(){
        return patient;
    }

    public LocalDate getDate(){
        return date;
    }

    public LocalTime getTime(){
        return time;
    }

    public String getDermatologist(){
        return dermatologist;
    }

    public double getRegistrationFees(){
        return registrationFees;
    }

    public boolean getCompleted(){
        return completed;
    }


    //Setters
    public void setAppointmentId(int appointmentId){
        this.appointmentId = appointmentId;
    }

    public void setPatient(Patient patient){
        this.patient = patient;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }

    public void setTime(LocalTime time){
        this.time = time;
    }

    public void setDermatologist(String dermatologist){
        this.dermatologist = dermatologist;
    }

    public void setRegistrationFees(double registrationFees){
        this.registrationFees = registrationFees;
    }

    public void setCompleted(boolean completed){
        this.completed = completed;
    }
}

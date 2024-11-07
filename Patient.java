public class Patient{

    private String name;
    private String nic;
    private String email;
    private String contactNumber;

    //Constracture 
    public Patient(String name, String nic, String email, String contactNumber){
        this.name = name;
        this.nic = nic;
        this.email = email;
        this.contactNumber = contactNumber;
    }

    // Getters
    public String getName(){
        return name;
    }

    public String getNic(){
        return nic;
    }

    public String getEmail(){
        return email;
    }

    public String getContactNumber(){
        return contactNumber;
    }

    //Setters
    public void setName(String name){
        this.name = name;
    }

    public void setNic(String nic){
        this.nic = nic;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setContactNumber(String contactNumber){
        this.contactNumber = contactNumber;
    }
}
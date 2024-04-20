package entity.person;

public class Banker extends Person{
    private String jobTitle;
    
    public Banker(String name, String nid, int birthYear, String address, String mobileNumber, String email, String password) {
        super(name, nid, birthYear, address, mobileNumber, email, password);
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobTitle() {
        return this.jobTitle;
    }

}

package entity.person;

public abstract class Person implements IPerson {
    private final int YEAR = 2024;

    private String name;
    private String nid;
    private int birthYear;
    private int age;
    private String address;
    private String mobileNumber;
    private String email;
    private String password;

    public Person() {}

    public Person(String name, String nid, int birthYear, String address, String mobileNumber, String email, String password) {
        setName(name);
        setNid(nid);
        setBirthYear(birthYear);
        setAge();
        setAddress(address);
        setMobileNumber(mobileNumber);
        setEmail(email);
        setPassword(password);
    }
   
    public void setName(String name) {
        this.name = name;
    }
    
    public void setNid(String nid) {
        this.nid = nid;
    }
    
    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }
    
    public void setAge() {
        this.age = YEAR - birthYear;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getName() {
        return this.name;
    }

    public String setPassword(String password) {
        return this.password = password;
    }
    
    public String getNid() {
        return this.nid;
    }

    public int getAge() {
        return this.age;
    }

    public int getBirthYear() {
        return this.birthYear;
    }

    public String getAddress() {
        return this.address;
    }
    
    public String getMobileNumber() {
        return this.mobileNumber;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }
}
package models;

public class Patient {
    private String name;
    private String nid;
    private String gender;
    private String address;
    private int age;
    private int contactNumber;

    public Patient(String name, String nid, String gender, String address, int age, int contactNumber) {
        this.name = name;
        this.nid = nid;
        this.gender = gender;
        this.address = address;
        this.age = age;
        this.contactNumber = contactNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setContactNumber(int contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getName() {
        return this.name;
    }

    public String getNid() {
        return this.nid;
    }

    public String getGender() {
        return this.gender;
    }

    public String getAddress() {
        return this.address;
    }

    public int getAge() {
        return this.age;
    }

    public int getContactNumber() {
        return this.contactNumber;
    }

    @Override
    public String toString() {
        return "Patient {" + "name='" + name + "', " + "nid='" + nid + "', " + "gender='" + gender + "', " + "address='"
                + address + "', " + "age='" + age + "', " + "contactNumber='" + contactNumber + "'}";
    }
}

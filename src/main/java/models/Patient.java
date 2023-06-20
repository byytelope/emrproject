package models;

public class Patient {
    private String name;
    private String nid;
    private String gender;
    private String address;
    private String contactNumber;
    private int age;

    public Patient(String name, String nid, String gender, String address, String contactNumber, int age) {
        this.name = name;
        this.nid = nid;
        this.gender = gender;
        this.address = address;
        this.contactNumber = contactNumber;
        this.age = age;
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

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setAge(int age) {
        this.age = age;
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

    public String getContactNumber() {
        return this.contactNumber;
    }

    public int getAge() {
        return this.age;
    }

    @Override
    public String toString() {
        return '"' + this.name + "\"," + '"' + this.nid + "\"," + '"' + this.gender + "\"," + '"' + this.address + "\","
                + '"'
                + this.age + "\","
                + '"' + this.contactNumber + "\"\n";
    }
}

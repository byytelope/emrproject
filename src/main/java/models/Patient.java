package models;

import java.util.List;

public class Patient {
    private String name;
    private String nid;
    private String gender;
    private String address;
    private String contactNumber;
    private int age;
    private List<String> allergies;

    public Patient(String name, String nid, String gender, String address, String contactNumber, int age,
            List<String> allergies) {
        this.name = name;
        this.nid = nid;
        this.gender = gender;
        this.address = address;
        this.contactNumber = contactNumber;
        this.age = age;
        this.allergies = allergies;
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

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
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

    public List<String> getAllergies() {
        return this.allergies;
    }

    @Override
    public String toString() {
        return '"' + this.name + "\"," + '"' + this.nid + "\"," + '"' + this.gender + "\"," + '"' + this.address + "\","
                + '"'
                + this.age + "\","
                + '"' + this.contactNumber + '"' + this.allergies + "\"\n";
    }
}

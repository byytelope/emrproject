package models;

import java.util.List;

public class Patient extends BaseModel {
    private String nid;
    private String name;
    private String gender;
    private String address;
    private String nationality;
    private String email;
    private String contactNumber;
    private int age;
    private List<String> allergies;

    public Patient(String nid, String name, String gender, String address, String nationality, String email,
            String contactNumber,
            int age,
            List<String> allergies) {
        this.nid = nid;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.nationality = nationality;
        this.email = email;
        this.contactNumber = contactNumber;
        this.age = age;
        this.allergies = allergies;
    }

    public Patient() {
    }

    @Override
    public String getFileName() {
        return "patients.csv";
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

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getNationality() {
        return this.nationality;
    }

    public String getEmail() {
        return this.email;
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
}

package models;

import java.lang.reflect.Field;
import java.util.ArrayList;
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
            ArrayList<String> allergies) {
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

    public String toCsvString() {
        return '"' + this.name + "\"," + '"' + this.nid + "\"," + '"' + this.gender + "\"," + '"' + this.address + "\","
                + '"'
                + this.age + "\","
                + '"' + this.contactNumber +
                "\"," + '"' + String.join(",", this.allergies) + "\"\n";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName()).append("{");

        Field[] fields = getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            sb.append(fields[i].getName()).append("=");

            try {
                Object value = fields[i].get(this);
                if (value instanceof String) {
                    sb.append("\"").append(value).append("\"");
                } else {
                    sb.append(value);
                }
            } catch (IllegalAccessException e) {
                sb.append("N/A");
            }

            if (i < fields.length - 1) {
                sb.append(", ");
            }
        }

        sb.append("}");
        return sb.toString();
    }
}

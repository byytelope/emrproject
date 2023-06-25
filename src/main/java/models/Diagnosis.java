package models;

import java.util.Arrays;
import java.util.List;

public class Diagnosis extends BaseModel {
    private String patientNid;
    private String diagnosis;
    private String date;
    private String labId;
    private String labName;
    private String results;
    private List<String> allergies;

    public Diagnosis(String patientNid, String diagnosis, String date, String labId, String labName, String results,
            List<String> allergies) {
        this.patientNid = patientNid;
        this.diagnosis = diagnosis;
        this.date = date;
        this.labId = labId;
        this.labName = labName;
        this.results = results;
        this.allergies = allergies;
    }

    public Diagnosis() {
    }

    @Override
    public String getFileName() {
        return "diagnoses.csv";
    }

    public String getPatientNid() {
        return this.patientNid;
    }

    public String getDiagnosis() {
        return this.diagnosis;
    }

    public String getDate() {
        return this.date;
    }

    public String getLabId() {
        return this.labId;
    }

    public String getLabName() {
        return this.labName;
    }

    public String getResults() {
        return this.results;
    }

    public List<String> getAllergies() {
        return this.allergies;
    }

    public void setPatientNid(String patientNid) {
        this.patientNid = patientNid;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLabId(String labId) {
        this.labId = labId;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public void setAllergies(String allergiesString) {
        allergiesString = allergiesString.replace(" ", "");
        this.allergies = Arrays.asList(allergiesString.split(","));
    }
}

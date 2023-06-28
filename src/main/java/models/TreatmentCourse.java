package models;

public class TreatmentCourse extends BaseModel {
    private String uid;
    private String patientNid;
    private String diagnosis;
    private String treatmentType;
    private String startDate;
    private String endDate;
    private String results;

    public TreatmentCourse(String uid, String patientNid, String diagnosis, String treatmentType, String startDate,
            String endDate,
            String results) {
        this.uid = uid;
        this.patientNid = patientNid;
        this.diagnosis = diagnosis;
        this.treatmentType = treatmentType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.results = results;
    }

    public TreatmentCourse() {
    }

    @Override
    public String getFileName() {
        return "treatmentCourses.csv";
    }

    public String getUid() {
        return this.uid;
    }

    public String getPatientNid() {
        return this.patientNid;
    }

    public String getDiagnosis() {
        return this.diagnosis;
    }

    public String getTreatmentType() {
        return this.treatmentType;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public String getResults() {
        return this.results;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setPatientNid(String nid) {
        this.patientNid = nid;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void setTreatmentType(String treatmentType) {
        this.treatmentType = treatmentType;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setResults(String results) {
        this.results = results;
    }
}

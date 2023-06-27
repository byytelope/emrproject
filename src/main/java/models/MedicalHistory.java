package models;

public class MedicalHistory extends BaseModel {
    private String uid;
    private String patientNid;
    private double patientHeight;
    private double patientWeight;
    private String procedureName;
    private String attendingStaff;
    private String medicationName;
    private String wardNo;
    private String majorComplications;
    private String treatmentHistory;

    public MedicalHistory(
            String uid,
            String patientNid,
            double patientHeight,
            double patientWeight,
            String procedureName,
            String attendingStaff,
            String medicationName,
            String wardNo,
            String majorComplications,
            String treatmentHistory) {
        this.uid = uid;
        this.patientNid = patientNid;
        this.patientHeight = patientHeight;
        this.patientWeight = patientWeight;
        this.procedureName = procedureName;
        this.attendingStaff = attendingStaff;
        this.medicationName = medicationName;
        this.wardNo = wardNo;
        this.majorComplications = majorComplications;
        this.treatmentHistory = treatmentHistory;
    }

    public MedicalHistory() {
    }

    @Override
    public String getFileName() {
        return "medicalHistory.csv";
    }

    public String getUid() {
        return this.uid;
    }

    public String getPatientNid() {
        return patientNid;
    }

    public double getPatientHeight() {
        return patientHeight;
    }

    public double getPatientWeight() {
        return patientWeight;
    }

    public String getProcedureName() {
        return procedureName;
    }

    public String getAttendingStaff() {
        return attendingStaff;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public String getWardNo() {
        return wardNo;
    }

    public String getMajorComplications() {
        return majorComplications;
    }

    public String getTreatmentHistory() {
        return treatmentHistory;
    }

    public void setPatientNid(String patientNid) {
        this.patientNid = patientNid;
    }

    public void setPatientHeight(double patientHeight) {
        this.patientHeight = patientHeight;
    }

    public void setPatientWeight(double patientWeight) {
        this.patientWeight = patientWeight;
    }

    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }

    public void setAttendingStaff(String attendingStaff) {
        this.attendingStaff = attendingStaff;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public void setWardNo(String wardNo) {
        this.wardNo = wardNo;
    }

    public void setMajorComplications(String majorComplications) {
        this.majorComplications = majorComplications;
    }

    public void setTreatmentHistory(String treatmentHistory) {
        this.treatmentHistory = treatmentHistory;
    }
}

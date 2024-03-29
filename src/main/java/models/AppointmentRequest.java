package models;

public class AppointmentRequest extends BaseModel {
    private String uid;
    private String patientNid;
    private String preferredDoctor;
    private String requestedMedicalDept;
    private String preferredMedicalFacility;
    private String date;
    private String details;
    private boolean isFollowUp;

    public AppointmentRequest(String uid, String patientNid, String preferredDoctor, String requestedMedicalDept,
            String preferredMedicalFacility,
            String date,
            String details, boolean isFollowUp) {
        this.uid = uid;
        this.patientNid = patientNid;
        this.preferredDoctor = preferredDoctor;
        this.requestedMedicalDept = requestedMedicalDept;
        this.preferredMedicalFacility = preferredMedicalFacility;
        this.date = date;
        this.details = details;
        this.isFollowUp = isFollowUp;
    }

    public AppointmentRequest() {
    }

    @Override
    public String getFileName() {
        return "appointmentReqs.csv";
    }

    public String getUid() {
        return this.uid;
    }

    public String getPatientNid() {
        return this.patientNid;
    }

    public String getPreferredDoctor() {
        return this.preferredDoctor;
    }

    public String getRequestedMedicalDept() {
        return this.requestedMedicalDept;
    }

    public String getPreferredMedicalFacility() {
        return this.preferredMedicalFacility;
    }

    public String getDate() {
        return this.date;
    }

    public String getDetails() {
        return this.details;
    }

    public boolean getIsFollowUp() {
        return this.isFollowUp;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setPatientNid(String patientNid) {
        this.patientNid = patientNid;
    }

    public void setPreferredDoctor(String preferredDoctor) {
        this.preferredDoctor = preferredDoctor;
    }

    public void setRequestedMedicalDept(String requestedMedicalDept) {
        this.requestedMedicalDept = requestedMedicalDept;
    }

    public void setPreferredMedicalFacility(String preferredMedicalFacility) {
        this.preferredMedicalFacility = preferredMedicalFacility;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setIsFollowUp(boolean isFollowUp) {
        this.isFollowUp = isFollowUp;
    }
}

package models;

public class BaseAnalysis extends BaseModel {
    private String uid;
    private String patientNid;
    private String labId;
    private String labName;
    private String labAddress;
    private String date;
    protected AnalysisType analysisType;

    public BaseAnalysis(String uid, String patientNid, String labId, String labName, String labAddress, String date) {
        this.uid = uid;
        this.patientNid = patientNid;
        this.labId = labId;
        this.labName = labName;
        this.labAddress = labAddress;
        this.date = date;
    }

    public BaseAnalysis() {
    }

    public enum AnalysisType {
        BLOOD,
        BIOBLOOD,
        URINE,
    }

    @Override
    public String getFileName() {
        throw new UnsupportedOperationException("Unimplemented method 'getFileName'");
    }

    public String getUid() {
        return this.uid;
    }

    public String getPatientNid() {
        return this.patientNid;
    }

    public String getLabId() {
        return this.labId;
    }

    public String getLabName() {
        return this.labName;
    }

    public String getLabAddress() {
        return this.labAddress;
    }

    public String getDate() {
        return this.date;
    }

    public AnalysisType getAnalysisType() {
        return this.analysisType;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setPatientNid(String patientNid) {
        this.patientNid = patientNid;
    }

    public void setLabId(String labId) {
        this.labId = labId;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    public void setLabAddress(String labAddress) {
        this.labAddress = labAddress;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAnalysisType(AnalysisType analysisType) {
        this.analysisType = analysisType;
    }
}

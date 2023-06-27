package models;

public class BloodAnalysis extends BaseAnalysis {
    private double whiteBloodCell;
    private double redBloodCell;
    private double haemoglobin;
    private double lymphocyte;

    public BloodAnalysis(String uid, String patientNid, String labId, String labName, String labAddress, String date,
            double whiteBloodCell, double redBloodCell, double haemoglobin, double lymphocyte) {
        super(uid, patientNid, labId, labName, labAddress, date);

        this.analysisType = AnalysisType.BLOOD;
        this.whiteBloodCell = whiteBloodCell;
        this.redBloodCell = redBloodCell;
        this.haemoglobin = haemoglobin;
        this.lymphocyte = lymphocyte;
    }

    public BloodAnalysis() {
    }

    @Override
    public String getFileName() {
        return "bloodAnalyses.csv";
    }

    public double getWhiteBloodCell() {
        return this.whiteBloodCell;
    }

    public double getRedBloodCell() {
        return this.redBloodCell;
    }

    public double getHaemoglobin() {
        return this.haemoglobin;
    }

    public double getLymphocyte() {
        return this.lymphocyte;
    }

    public void setWhiteBloodCell(double whiteBloodCell) {
        this.whiteBloodCell = whiteBloodCell;
    }

    public void setRedBloodCell(double redBloodCell) {
        this.redBloodCell = redBloodCell;
    }

    public void setHaemoglobin(double haemoglobin) {
        this.haemoglobin = haemoglobin;
    }

    public void setLymphocyte(double lymphocyte) {
        this.lymphocyte = lymphocyte;
    }
}

package models;

public class BioBloodAnalysis extends BaseAnalysis {
    private double sodium;
    private double potassium;
    private double urea;
    private double creatinine;
    private double glucose;
    private double biluribin;
    private double ast;
    private double alt;

    public BioBloodAnalysis(String uid, String patientNid, String labId, String labName, String labAddress, String date,
            double sodium, double potassium, double urea, double creatinine, double glucose,
            double biluribin, double ast, double alt) {
        super(uid, patientNid, labId, labName, labAddress, date);

        this.analysisType = AnalysisType.BIOBLOOD;
        this.sodium = sodium;
        this.potassium = potassium;
        this.urea = urea;
        this.creatinine = creatinine;
        this.glucose = glucose;
        this.biluribin = biluribin;
        this.ast = ast;
        this.alt = alt;
    }

    public BioBloodAnalysis() {
    }

    @Override
    public String getFileName() {
        return "bioBloodAnalyses.csv";
    }

    public double getSodium() {
        return this.sodium;
    }

    public double getPotassium() {
        return this.potassium;
    }

    public double getUrea() {
        return this.urea;
    }

    public double getCreatinine() {
        return this.creatinine;
    }

    public double getGlucose() {
        return this.glucose;
    }

    public double getBiluribin() {
        return this.biluribin;
    }

    public double getAst() {
        return this.ast;
    }

    public double getAlt() {
        return this.alt;
    }

    public void setSodium(double sodium) {
        this.sodium = sodium;
    }

    public void setPotassium(double potassium) {
        this.potassium = potassium;
    }

    public void setUrea(double urea) {
        this.urea = urea;
    }

    public void setCreatinine(double creatinine) {
        this.creatinine = creatinine;
    }

    public void setGlucose(double glucose) {
        this.glucose = glucose;
    }

    public void setBiluribin(double biluribin) {
        this.biluribin = biluribin;
    }

    public void setAst(double ast) {
        this.ast = ast;
    }

    public void setAlt(double alt) {
        this.alt = alt;
    }
}

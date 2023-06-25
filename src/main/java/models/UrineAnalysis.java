package models;

public class UrineAnalysis extends BaseAnalysis {
    private String clarity;
    private String crystals;
    private String bacteria;
    private double ketone;
    private double protein;
    private double clinitest;
    private double whiteBloodCell;
    private double redBloodCell;

    public UrineAnalysis(String patientNid, String labId, String labName, String labAddress, String date,
            String clarity, String crystals, String bacteria, double ketone, double protein, double clinitest,
            double whiteBloodCell,
            double redBloodCell) {
        super(patientNid, labId, labName, labAddress, date);

        this.analysisType = AnalysisType.URINE;
        this.clarity = clarity;
        this.crystals = crystals;
        this.bacteria = bacteria;
        this.ketone = ketone;
        this.protein = protein;
        this.clinitest = clinitest;
        this.whiteBloodCell = whiteBloodCell;
        this.redBloodCell = redBloodCell;
    }

    @Override
    public String getFileName() {
        return "urineAnalyses.csv";
    }

    public String getClarity() {
        return this.clarity;
    }

    public String getCrystals() {
        return this.crystals;
    }

    public String getBacteria() {
        return this.bacteria;
    }

    public double getKetone() {
        return this.ketone;
    }

    public double getProtein() {
        return this.protein;
    }

    public double getClinitest() {
        return this.clinitest;
    }

    public double getWhiteBloodCell() {
        return this.whiteBloodCell;
    }

    public double getRedBloodCell() {
        return this.redBloodCell;
    }

    public void setClarity(String clarity) {
        this.clarity = clarity;
    }

    public void setCrystals(String crystals) {
        this.crystals = crystals;
    }

    public void setBacteria(String bacteria) {
        this.bacteria = bacteria;
    }

    public void setKetone(double ketone) {
        this.ketone = ketone;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public void setClinitest(double clinitest) {
        this.clinitest = clinitest;
    }

    public void setWhiteBloodCell(double whiteBloodCell) {
        this.whiteBloodCell = whiteBloodCell;
    }

    public void setRedBloodCell(double redBloodCell) {
        this.redBloodCell = redBloodCell;
    }
}

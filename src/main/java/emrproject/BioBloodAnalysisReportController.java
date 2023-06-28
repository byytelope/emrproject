package emrproject;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.BioBloodAnalysis;
import models.Patient;
import utils.CsvHandler;

public class BioBloodAnalysisReportController implements Initializable {
    @FXML
    private AnchorPane rootPane;

    @FXML
    private Text patientNameText;

    @FXML
    private Text patientNidText;

    @FXML
    private Text patientAgeText;

    @FXML
    private Text patientEmailText;

    @FXML
    private Text patientContactNumberText;

    @FXML
    private Text patientAddressText;

    @FXML
    private Text reportIdText;

    @FXML
    private Text labIdText;

    @FXML
    private Text labNameText;

    @FXML
    private Text labAddressText;

    @FXML
    private Text dateText;

    @FXML
    private Text sodiumText;

    @FXML
    private Text potassiumText;

    @FXML
    private Text ureaText;

    @FXML
    private Text creatinineText;

    @FXML
    private Text glucoseText;

    @FXML
    private Text biluribinText;

    @FXML
    private Text astText;

    @FXML
    private Text altText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            Stage stage = (Stage) rootPane.getScene().getWindow();
            Object data = stage.getUserData();
            if (data instanceof BioBloodAnalysis) {
                BioBloodAnalysis report = (BioBloodAnalysis) data;
                CsvHandler csvHandler = new CsvHandler();
                Patient patient = csvHandler.getPatient(report.getPatientNid());

                patientNameText.setText(patient.getName());
                patientNidText.setText(patient.getNid());
                patientAgeText.setText(String.valueOf(patient.getAge()));
                patientContactNumberText.setText(patient.getContactNumber());
                patientEmailText.setText(patient.getEmail());
                patientAddressText.setText(patient.getAddress());
                reportIdText.setText(report.getUid());
                labIdText.setText(report.getLabId());
                labNameText.setText(report.getLabName());
                labAddressText.setText(report.getLabAddress());
                dateText.setText(report.getDate());

                sodiumText.setText(String.valueOf(report.getSodium()));
                potassiumText.setText(String.valueOf(report.getPotassium()));
                ureaText.setText(String.valueOf(report.getUrea()));
                creatinineText.setText(String.valueOf(report.getCreatinine()));
                glucoseText.setText(String.valueOf(report.getGlucose()));
                biluribinText.setText(String.valueOf(report.getBiluribin()));
                astText.setText(String.valueOf(report.getAst()));
                altText.setText(String.valueOf(report.getAlt()));
            }
        });
    }
}

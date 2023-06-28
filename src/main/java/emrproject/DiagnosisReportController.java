package emrproject;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Diagnosis;
import models.Patient;
import utils.CsvHandler;

public class DiagnosisReportController implements Initializable {
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
    private Text diagnosisText;

    @FXML
    private Text resultsText;

    @FXML
    private Text allergiesText;

    @FXML
    private Text dateText;

    @FXML
    private Text labIdText;

    @FXML
    private Text labNameText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            Stage stage = (Stage) rootPane.getScene().getWindow();
            Object data = stage.getUserData();
            if (data instanceof Diagnosis) {
                Diagnosis report = (Diagnosis) data;
                CsvHandler csvHandler = new CsvHandler();
                Patient patient = csvHandler.getPatient(report.getPatientNid());

                patientNameText.setText(patient.getName());
                patientNidText.setText(patient.getNid());
                patientAgeText.setText(String.valueOf(patient.getAge()));
                patientContactNumberText.setText(patient.getContactNumber());
                patientEmailText.setText(patient.getEmail());
                patientAddressText.setText(patient.getAddress());
                reportIdText.setText(report.getUid());
                diagnosisText.setText(report.getDiagnosis());
                resultsText.setText(report.getResults());
                allergiesText.setText(String.join(", ", report.getAllergies()));
                dateText.setText(report.getDate());
                labIdText.setText(report.getLabId());
                labNameText.setText(report.getLabName());
            }
        });
    }
}

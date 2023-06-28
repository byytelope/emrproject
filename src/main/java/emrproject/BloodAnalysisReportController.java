package emrproject;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.BloodAnalysis;
import models.Patient;
import utils.CsvHandler;

public class BloodAnalysisReportController implements Initializable {
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
    private Text wbcText;

    @FXML
    private Text rbcText;

    @FXML
    private Text haemoglobinText;

    @FXML
    private Text lymphocytesText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            Stage stage = (Stage) rootPane.getScene().getWindow();
            Object data = stage.getUserData();
            if (data instanceof BloodAnalysis) {
                BloodAnalysis report = (BloodAnalysis) data;
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

                wbcText.setText(String.valueOf(report.getWhiteBloodCell()));
                rbcText.setText(String.valueOf(report.getRedBloodCell()));
                haemoglobinText.setText(String.valueOf(report.getHaemoglobin()));
                lymphocytesText.setText(String.valueOf(report.getLymphocyte()));
            }
        });
    }
}

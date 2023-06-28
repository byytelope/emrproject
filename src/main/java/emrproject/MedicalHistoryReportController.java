package emrproject;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.MedicalHistory;
import models.Patient;
import utils.CsvHandler;

public class MedicalHistoryReportController implements Initializable {
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
    private Text patientHeightText;

    @FXML
    private Text patientWeightText;

    @FXML
    private Text procedureNameText;

    @FXML
    private Text attendingStaffText;

    @FXML
    private Text medicationNameText;

    @FXML
    private Text wardNoText;

    @FXML
    private Text majorComplicationsText;

    @FXML
    private Text treatmentHistoryText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            Stage stage = (Stage) rootPane.getScene().getWindow();
            Object data = stage.getUserData();
            if (data instanceof MedicalHistory) {
                MedicalHistory report = (MedicalHistory) data;
                CsvHandler csvHandler = new CsvHandler();
                Patient patient = csvHandler.getPatient(report.getPatientNid());

                patientNameText.setText(patient.getName());
                patientNidText.setText(patient.getNid());
                patientAgeText.setText(String.valueOf(patient.getAge()));
                patientContactNumberText.setText(patient.getContactNumber());
                patientEmailText.setText(patient.getEmail());
                patientAddressText.setText(patient.getAddress());
                reportIdText.setText(report.getUid());
                patientHeightText.setText(String.valueOf(report.getPatientHeight()));
                patientWeightText.setText(String.valueOf(report.getPatientWeight()));
                procedureNameText.setText(report.getProcedureName());
                attendingStaffText.setText(report.getAttendingStaff());
                medicationNameText.setText(report.getMedicationName());
                wardNoText.setText(report.getWardNo());
                majorComplicationsText.setText(report.getMajorComplications());
                treatmentHistoryText.setText(report.getTreatmentHistory());
            }
        });
    }
}

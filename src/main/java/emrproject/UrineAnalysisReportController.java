package emrproject;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Patient;
import models.UrineAnalysis;
import utils.CsvHandler;

public class UrineAnalysisReportController implements Initializable {
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
    private Text clarityText;

    @FXML
    private Text crystalsText;

    @FXML
    private Text bacteriaText;

    @FXML
    private Text ketoneText;

    @FXML
    private Text proteinText;

    @FXML
    private Text clinitestText;

    @FXML
    private Text whiteBloodCellText;

    @FXML
    private Text redBloodCellText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            Stage stage = (Stage) rootPane.getScene().getWindow();
            Object data = stage.getUserData();
            if (data instanceof UrineAnalysis) {
                UrineAnalysis report = (UrineAnalysis) data;
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

                clarityText.setText(report.getClarity());
                crystalsText.setText(report.getCrystals());
                bacteriaText.setText(report.getBacteria());
                ketoneText.setText(String.valueOf(report.getKetone()));
                proteinText.setText(String.valueOf(report.getProtein()));
                clinitestText.setText(String.valueOf(report.getClinitest()));
                whiteBloodCellText.setText(String.valueOf(report.getWhiteBloodCell()));
                redBloodCellText.setText(String.valueOf(report.getRedBloodCell()));
            }
        });
    }
}

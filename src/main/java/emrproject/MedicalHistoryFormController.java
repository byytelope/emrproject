package emrproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.MedicalHistory;
import models.Patient;
import utils.CsvHandler;
import utils.MiscUtils;
import utils.UserSession;

public class MedicalHistoryFormController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button backButton;

    @FXML
    private Button addMedicalHistoryButton;

    @FXML
    private TextField patientWeightField;

    @FXML
    private TextField patientHeightField;

    @FXML
    private TextField procedureNameField;

    @FXML
    private TextField attendingStaffField;

    @FXML
    private TextField medicationNameField;

    @FXML
    private TextField wardNumberField;

    @FXML
    private TextField majorComplicationsField;

    @FXML
    private TextArea treatmentHistoryField;

    @FXML
    private Text patientNameText;

    @FXML
    private Text patientNidText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Patient currentPatient = UserSession.getInstance().getPatient();
        patientNameText.setText(currentPatient.getName());
        patientNidText.setText(currentPatient.getNid());
    }

    public void submitAction(ActionEvent e) throws IOException {
        if (formVerified()) {
            root = FXMLLoader.load(getClass().getResource("medicalHistory.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void backAction(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("medicalHistory.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private boolean formVerified() {
        String heightText = patientHeightField.getText();
        String weightText = patientWeightField.getText();
        String procedureName = procedureNameField.getText();
        String attendingStaff = attendingStaffField.getText();
        String medicationName = medicationNameField.getText();
        String wardNo = wardNumberField.getText();
        String majorComplications = majorComplicationsField.getText();
        String treatmentHistory = treatmentHistoryField.getText();

        boolean heightIsValid = MiscUtils.isDouble(heightText);
        boolean weightIsValid = MiscUtils.isDouble(weightText);
        boolean procedureNameIsValid = !procedureName.isBlank();
        boolean attendingStaffIsValid = !attendingStaff.isBlank();
        boolean medicationNameIsValid = !medicationName.isBlank();
        boolean wardNoIsValid = !wardNo.isBlank();
        boolean majorComplicationsIsValid = !majorComplications.isBlank();
        boolean treatmentHistoryIsValid = !treatmentHistory.isBlank();

        String errorText = "";

        if (!heightIsValid)
            errorText += "Height must be a decimal number.";
        if (!weightIsValid)
            errorText += "Weight must be a decimal number.";
        if (!attendingStaffIsValid)
            errorText += "Attending staff must not be blank.";
        if (!medicationNameIsValid)
            errorText += "Medication must be provided.";
        if (!wardNoIsValid)
            errorText += "Ward number must not be blank.";
        if (!treatmentHistoryIsValid)
            errorText += "Treatment history must be provided.";

        if (!procedureNameIsValid)
            procedureName = "";
        if (!majorComplicationsIsValid)
            majorComplications = "";

        if (errorText.isBlank()) {
            Patient patient = UserSession.getInstance().getPatient();
            MedicalHistory medicalHistory = new MedicalHistory(UUID.randomUUID().toString(), patient.getNid(),
                    Double.parseDouble(heightText), Double.parseDouble(weightText), procedureName, attendingStaff,
                    medicationName, wardNo, majorComplications,
                    treatmentHistory);

            CsvHandler csvHandler = new CsvHandler();
            csvHandler.addMedicalHistory(medicalHistory);

            return true;
        } else {
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setHeaderText("Invalid input");
            errorAlert.setContentText(errorText);
            errorAlert.showAndWait();

            return false;
        }
    }
}
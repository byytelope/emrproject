package emrproject;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

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
import models.Diagnosis;
import models.Patient;
import utils.CsvHandler;
import utils.MiscUtils;
import utils.UserSession;

public class DiagnosisFormController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button backButton;

    @FXML
    private Button addDiagnosisButton;

    @FXML
    private TextField diagnosisField;

    @FXML
    private TextField dateField;

    @FXML
    private TextField labIdField;

    @FXML
    private TextField labNameField;

    @FXML
    private TextField allergiesField;

    @FXML
    private TextArea resultsField;

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

    public void backAction(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("doctorHome.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void submitAction(ActionEvent e) throws IOException {
        if (formVerified()) {
            root = FXMLLoader.load(getClass().getResource("doctorHome.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    private boolean formVerified() {
        String diagnosisText = diagnosisField.getText();
        String date = dateField.getText();
        String labId = labIdField.getText();
        String labName = labNameField.getText();
        String allergiesString = allergiesField.getText();
        String results = resultsField.getText();

        boolean diagnosisIsValid = !diagnosisText.isBlank();
        boolean dateIsValid = MiscUtils.isDate(date);
        boolean labIdIsValid = !labId.isBlank();
        boolean labNameIsValid = !labName.isBlank();
        boolean resultsIsValid = !results.isBlank();

        String errorText = "";

        if (!diagnosisIsValid)
            errorText += "Please enter a diagnosis.\n";
        if (!dateIsValid)
            errorText += "Date must be in the form dd/mm/yyyy.\n";
        if (!labIdIsValid)
            errorText += "Lab ID cannot be empty.\n";
        if (!labNameIsValid)
            errorText += "Lab name cannot be empty.\n";
        if (!resultsIsValid)
            errorText += "Please provide results.\n";

        if (allergiesString.isBlank())
            allergiesString = "";

        allergiesString.replace(" ", "");
        List<String> allergies = Arrays.asList(allergiesString.split(","));

        if (errorText.isBlank()) {
            Patient patient = UserSession.getInstance().getPatient();
            Diagnosis diagnosis = new Diagnosis(patient.getNid(), diagnosisText, date,
                    labId, labName, results, allergies);

            allergies.addAll(patient.getAllergies());
            patient.setAllergies(allergies);

            CsvHandler csvHandler = new CsvHandler();
            csvHandler.addDiagnosis(diagnosis);
            csvHandler.updatePatient(patient);

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

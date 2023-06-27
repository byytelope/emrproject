package emrproject;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.AppointmentRequest;
import models.Patient;
import utils.CsvHandler;
import utils.MiscUtils;
import utils.UserSession;

public class PatientHomeController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Patient currentPatient = null;

    @FXML
    private TextField preferredDocField;

    @FXML
    private TextField reqMedicalDepField;

    @FXML
    private TextField preferredMedFacilityField;

    @FXML
    private TextField dateField;

    @FXML
    private TextArea detailsField;

    @FXML
    private CheckBox isFollowUpCheckBox;

    @FXML
    private Button requestAppointmentButton;

    @FXML
    private Button signOutButton;

    @FXML
    private Button phoneButton;

    @FXML
    private Button emailButton;

    @FXML
    private Button viewAnalysisReportsButton;

    @FXML
    private Button viewDiagnosisReportsButton;

    @FXML
    private Button viewTreatmentCoursesButton;

    @FXML
    private Button updateInfoButton;

    @FXML
    private Text avatarText;

    @FXML
    private Text patientNameText;

    @FXML
    private Text nidText;

    @FXML
    private Text genderText;

    @FXML
    private Text ageText;

    @FXML
    private Text nationalityText;

    @FXML
    private Text allergiesText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String currentUserNid = UserSession.getInstance().getUser().getNid();
        CsvHandler csvHandler = new CsvHandler();
        this.currentPatient = csvHandler.getPatient(currentUserNid);

        avatarText.setText(String.valueOf(this.currentPatient.getName().charAt(0)));
        patientNameText.setText(this.currentPatient.getName());
        nidText.setText(this.currentPatient.getNid());
        genderText.setText(this.currentPatient.getGender());
        ageText.setText(String.valueOf(this.currentPatient.getAge()));
        nationalityText.setText(this.currentPatient.getNationality());
        allergiesText.setText(String.join(", ", this.currentPatient.getAllergies()));
    }

    public void signOutAction(ActionEvent e) throws IOException {
        UserSession.getInstance().clearInstance();

        root = FXMLLoader.load(getClass().getResource("signIn.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void reqAppointmentAction(ActionEvent e) throws IOException {
        String preferredDoc = preferredDocField.getText();
        String reqMedicalDep = reqMedicalDepField.getText();
        String preferredMedFacility = preferredMedFacilityField.getText();
        String date = dateField.getText();
        String details = detailsField.getText();
        boolean isFollowUp = isFollowUpCheckBox.isSelected();
        boolean preferredDocIsValid = preferredDoc.length() > 0;
        boolean reqMedicalDepIsValid = reqMedicalDep.length() > 0;
        boolean preferredMedFacilityIsValid = preferredMedFacility.length() > 0;
        boolean dateIsValid = MiscUtils.isDate(date);
        boolean detailsIsValid = details.length() > 0;

        String errorText = "";

        if (!preferredDocIsValid)
            errorText += "Enter your preferred doctor.\n";
        if (!reqMedicalDepIsValid)
            errorText += "Request a medical department.\n";
        if (!preferredMedFacilityIsValid)
            errorText += "Request a medical facility.\n";
        if (!dateIsValid)
            errorText += "Date should be in the format dd/mm/yyyy.\n";
        if (!detailsIsValid)
            errorText += "Please provide some details about your case.\n";

        if (errorText.isBlank()) {
            String patientNid = this.currentPatient.getNid();
            AppointmentRequest appointmentReq = new AppointmentRequest(UUID.randomUUID().toString(), patientNid,
                    preferredDoc, reqMedicalDep,
                    preferredMedFacility,
                    date, details, isFollowUp);
            CsvHandler csvHandler = new CsvHandler();
            csvHandler.addAppointmentReq(appointmentReq);

            preferredDocField.clear();
            reqMedicalDepField.clear();
            preferredMedFacilityField.clear();
            dateField.clear();
            detailsField.clear();
            isFollowUpCheckBox.setSelected(false);

            Alert successAlert = new Alert(AlertType.INFORMATION);
            successAlert.setHeaderText("Appointment request successfull");
            successAlert.setContentText("Please check your mail periodically for a follow-up mail from us.");
            successAlert.showAndWait();
        } else {
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setHeaderText("Invalid input");
            errorAlert.setContentText(errorText);
            errorAlert.showAndWait();
        }
    }

    public void phoneAction(ActionEvent e) throws IOException {
        try {
            URI telUri = new URI("tel:" + "+60" + this.currentPatient.getContactNumber());
            Desktop.getDesktop().browse(telUri);
        } catch (URISyntaxException | IOException ex) {
            ex.printStackTrace();
        }
    }

    public void emailAction(ActionEvent e) throws IOException {
        try {
            URI mailUri = new URI("mailto:" + this.currentPatient.getEmail());
            Desktop.getDesktop().mail(mailUri);
        } catch (URISyntaxException | IOException ex) {
            ex.printStackTrace();
        }
    }

    public void viewAnalysisReportsAction(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("analysisReports.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void viewDiagnosisReportsAction(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("diagnosisReports.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void viewTreatmentCoursesAction(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("treatmentCourses.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void updateInfoAction(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("updateInfoForm.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

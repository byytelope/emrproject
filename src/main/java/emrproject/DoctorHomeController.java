package emrproject;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Patient;
import utils.CsvHandler;
import utils.UserSession;

public class DoctorHomeController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField searchField;

    @FXML
    private Button signOutButton;

    @FXML
    private Button phoneButton;

    @FXML
    private Button emailButton;

    @FXML
    private Button newTreatmentCourseButton;

    @FXML
    private Button viewAllReportsButton;

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

    @FXML
    private VBox sideBox;

    @FXML
    private VBox centerBox;

    @FXML
    private ListView<String> appointmentRequests;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Patient currentPatient = UserSession.getInstance().getPatient();
        if (currentPatient != null) {
            setPatient(currentPatient);
        } else {
            centerBox.setVisible(false);
            sideBox.setVisible(false);
        }
    }

    public void signOutAction(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("signIn.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void phoneAction(ActionEvent e) throws IOException {
        try {
            Patient patient = UserSession.getInstance().getPatient();
            URI telUri = new URI("tel:" + "+60" + patient.getContactNumber());
            Desktop.getDesktop().browse(telUri);
        } catch (URISyntaxException | IOException ex) {
            ex.printStackTrace();
        }
    }

    public void emailAction(ActionEvent e) throws IOException {
        try {
            Patient patient = UserSession.getInstance().getPatient();
            URI mailUri = new URI("mailto:" + patient.getEmail());
            Desktop.getDesktop().mail(mailUri);
        } catch (URISyntaxException | IOException ex) {
            ex.printStackTrace();
        }
    }

    public void viewReportsAction(ActionEvent e) throws IOException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setHeaderText("Select Report Type");
        alert.setContentText("Please select which type of report you want to view.");

        ButtonType analysisButton = new ButtonType("Analysis Reports");
        ButtonType diagnosisButton = new ButtonType("Diagnosis Reports");

        alert.getButtonTypes().setAll(analysisButton, diagnosisButton);
        alert.showAndWait().ifPresent(response -> {
            try {
                // TODO: change form to list
                root = FXMLLoader.load(getClass()
                        .getResource(response == analysisButton ? "analysisForm.fxml" : "diagnosisList.fxml"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        });
    }

    public void newTreatmentCourseAction(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("treatmentCourseForm.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void searchAction(ActionEvent e) throws IOException {
        CsvHandler csvHandler = new CsvHandler();
        Patient patient = csvHandler.getPatient(searchField.getText());

        if (patient != null) {
            setPatient(patient);
        } else {
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setHeaderText("Patient not found");
            errorAlert.setContentText("Please enter a valid NID for a patient who has signed up for the service.");
            errorAlert.showAndWait();
        }
    }

    private void setPatient(Patient patient) {
        UserSession.getInstance().setPatient(patient);
        avatarText.setText(String.valueOf(patient.getName().charAt(0)));
        patientNameText.setText(patient.getName());
        nidText.setText(patient.getNid());
        genderText.setText(patient.getGender());
        ageText.setText(String.valueOf(patient.getAge()));
        nationalityText.setText(patient.getNationality());
        allergiesText.setText(String.join(", ", patient.getAllergies()));

        sideBox.setVisible(true);
        centerBox.setVisible(true);

    }
}

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
import models.Patient;
import models.TreatmentCourse;
import utils.CsvHandler;
import utils.MiscUtils;
import utils.UserSession;

public class TreatmentCourseFormController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button backButton;

    @FXML
    private Button addTreatmentCourseButton;

    @FXML
    private TextField diagnosisField;

    @FXML
    private TextField treatmentTypeField;

    @FXML
    private TextField startDateField;

    @FXML
    private TextField endDateField;

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

    public void submitAction(ActionEvent e) throws IOException {
        if (infoVerified()) {
            root = FXMLLoader.load(getClass().getResource("treatmentCourses.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    private boolean infoVerified() {
        String diagnosis = diagnosisField.getText();
        String treatmentType = treatmentTypeField.getText();
        String startDate = startDateField.getText();
        String endDate = endDateField.getText();
        String results = resultsField.getText();

        CsvHandler csvHandler = new CsvHandler();

        boolean diagnosisIsValid = !diagnosis.isEmpty();
        boolean treatmentTypeIsValid = !treatmentType.isEmpty();
        boolean startDateIsValid = MiscUtils.isDate(startDate);
        boolean endDateIsValid = MiscUtils.isDate(endDate);
        boolean resultsIsValid = !results.isEmpty();

        String errorText = "";

        if (!diagnosisIsValid)
            errorText += "Provide a diagnosis.\n";
        if (!treatmentTypeIsValid)
            errorText += "Provide a treatment type.\n";
        if (!startDateIsValid)
            errorText += "Start date must be in the form dd/mm/yyyy.\n";
        if (!endDateIsValid)
            errorText += "End date must be in the form dd/mm/yyyy.\n";
        if (!resultsIsValid)
            errorText += "Provide a results.\n";

        if (errorText.isBlank()) {
            TreatmentCourse treatmentCourse = new TreatmentCourse(UUID.randomUUID().toString(),
                    UserSession.getInstance().getPatient().getNid(),
                    diagnosis, treatmentType, startDate, endDate, results);
            csvHandler.addTreatmentCourse(treatmentCourse);

            return true;
        } else {
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setHeaderText("Invalid input");
            errorAlert.setContentText(errorText);
            errorAlert.showAndWait();

            return false;
        }
    }

    public void backAction(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("treatmentCourses.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

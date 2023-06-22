package emrproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.CsvHandler;
import utils.UserSession;

public class PatientHomeController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField preferredDocField;

    @FXML
    private TextField reqMedicalDepField;

    @FXML
    private TextField reqMedFacilityField;

    @FXML
    private TextField dateAndTimeField;

    @FXML
    private TextArea detailsField;

    @FXML
    private CheckBox isFollowUp;

    @FXML
    private Button requestAppointmentButton;

    @FXML
    private Button signOutButton;

    @FXML
    private Button phoneButton;

    @FXML
    private Button emailButton;

    @FXML
    private Button viewReportsButton;

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
        System.out.println("Current user NID: " + UserSession.getInstance().getUser().getNid());
        CsvHandler csvHandler = new CsvHandler();
        csvHandler.getPatient("");
    }

    public void signOutAction(ActionEvent e) throws IOException {
        System.out.println("Logged out");
        root = FXMLLoader.load(getClass().getResource("signIn.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void reqAppointmentAction(ActionEvent e) throws IOException {
    }

    public void phoneAction(ActionEvent e) throws IOException {
    }

    public void emailAction(ActionEvent e) throws IOException {
    }

    public void viewReportsAction(ActionEvent e) throws IOException {
    }

    public void updateInfoAction(ActionEvent e) throws IOException {
    }
}

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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
    private Button newAnalysisButton;

    @FXML
    private Button newDiagnosisButton;

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
    private ListView<String> analysisReports;

    @FXML
    private ListView<String> diagnosisReports;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void signOutAction(ActionEvent e) throws IOException {
        System.out.println("Logged out");
        root = FXMLLoader.load(getClass().getResource("signIn.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void phoneAction(ActionEvent e) throws IOException {
    }

    public void emailAction(ActionEvent e) throws IOException {
    }

    public void viewReportsAction(ActionEvent e) throws IOException {
    }

    public void newTreatmentCourseAction(ActionEvent e) throws IOException {
    }

    public void searchAction(ActionEvent e) throws IOException {
    }
}

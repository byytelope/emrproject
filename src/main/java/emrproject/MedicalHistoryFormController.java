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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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

    }

    public void submitAction(ActionEvent e) throws IOException {

    }

    public void backAction(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("signIn.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

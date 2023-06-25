package emrproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.BaseAnalysis.AnalysisType;
import models.Patient;
import utils.UserSession;

public class AnalysisFormController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button backButton;

    @FXML
    private Button addAnalysisButton;

    @FXML
    private ComboBox<AnalysisType> analysisTypeBox;

    @FXML
    private Text patientNameText;

    @FXML
    private Text patientNidText;

    // Common fields

    @FXML
    private TextField dateField;

    @FXML
    private TextField labIdField;

    @FXML
    private TextField labNameField;

    @FXML
    private TextField labAddressField;

    // Bio-blood analysis fields

    @FXML
    private TextField sodiumField;

    @FXML
    private TextField potassiumField;

    @FXML
    private TextField ureaField;

    @FXML
    private TextField creatinineField;

    @FXML
    private TextField glucoseField;

    @FXML
    private TextField biluribinField;

    @FXML
    private TextField astField;

    @FXML
    private TextField altField;

    // Urine analysis fields

    @FXML
    private TextField clarityField;

    @FXML
    private TextField crystalsField;

    @FXML
    private TextField bacteriaField;

    @FXML
    private TextField ketoneField;

    @FXML
    private TextField proteinField;

    @FXML
    private TextField clinitestField;

    @FXML
    private TextField whiteBloodCellUField;

    @FXML
    private TextField redBloodCellUField;

    // Blood analysis fields

    @FXML
    private TextField whiteBloodCellBField;

    @FXML
    private TextField redBloodCellBField;

    @FXML
    private TextField haemoglobinField;

    @FXML
    private TextField lymphocyteField;

    @FXML
    private VBox bloodAnalysisVBox;

    @FXML
    private VBox bioBloodAnalysisVBox;

    @FXML
    private VBox urineAnalysisVBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<AnalysisType> analysisTypes = FXCollections.observableArrayList(AnalysisType.values());
        analysisTypeBox.setItems(analysisTypes);

        Patient currentPatient = UserSession.getInstance().getPatient();
        patientNameText.setText(currentPatient.getName());
        patientNidText.setText(currentPatient.getNid());

        bioBloodAnalysisVBox.setVisible(false);
        bioBloodAnalysisVBox.setManaged(false);
        bloodAnalysisVBox.setVisible(false);
        bloodAnalysisVBox.setManaged(false);
        urineAnalysisVBox.setVisible(false);
        urineAnalysisVBox.setManaged(false);
    }

    public void backAction(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("doctorHome.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void analysisTypeAction(ActionEvent e) throws IOException {
        if (analysisTypeBox.getValue() == AnalysisType.BLOOD) {
            bloodAnalysisVBox.setVisible(true);
            bloodAnalysisVBox.setManaged(true);
            bioBloodAnalysisVBox.setVisible(false);
            bioBloodAnalysisVBox.setManaged(false);
            urineAnalysisVBox.setVisible(false);
            urineAnalysisVBox.setManaged(false);
        } else if (analysisTypeBox.getValue() == AnalysisType.BIOBLOOD) {
            bloodAnalysisVBox.setVisible(false);
            bloodAnalysisVBox.setManaged(false);
            bioBloodAnalysisVBox.setVisible(true);
            bioBloodAnalysisVBox.setManaged(true);
            urineAnalysisVBox.setVisible(false);
            urineAnalysisVBox.setManaged(false);
        } else if (analysisTypeBox.getValue() == AnalysisType.URINE) {
            bloodAnalysisVBox.setVisible(false);
            bloodAnalysisVBox.setManaged(false);
            bioBloodAnalysisVBox.setVisible(false);
            bioBloodAnalysisVBox.setManaged(false);
            urineAnalysisVBox.setVisible(true);
            urineAnalysisVBox.setManaged(true);
        }
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
        return true;
    }
}

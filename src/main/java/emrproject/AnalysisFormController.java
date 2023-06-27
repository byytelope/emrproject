package emrproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.BaseAnalysis.AnalysisType;
import models.BioBloodAnalysis;
import models.BloodAnalysis;
import models.Patient;
import models.UrineAnalysis;
import utils.CsvHandler;
import utils.MiscUtils;
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
        root = FXMLLoader.load(getClass().getResource("analysisReports.fxml"));
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
            root = FXMLLoader.load(getClass().getResource("analysisReports.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    private boolean formVerified() {
        String date = dateField.getText();
        String labId = labIdField.getText();
        String labName = labNameField.getText();
        String labAddress = labAddressField.getText();
        AnalysisType analysisType = analysisTypeBox.getValue();

        // Bio-blood analysis fields
        String sodiumText = sodiumField.getText();
        String potassiumText = potassiumField.getText();
        String ureaText = ureaField.getText();
        String creatinineText = creatinineField.getText();
        String glucoseText = glucoseField.getText();
        String biluribinText = biluribinField.getText();
        String astText = astField.getText();
        String altText = altField.getText();

        // Urine analysis fields
        String clarityText = clarityField.getText();
        String crystalsText = crystalsField.getText();
        String bacteriaText = bacteriaField.getText();
        String ketoneText = ketoneField.getText();
        String proteinText = proteinField.getText();
        String clinitestText = clinitestField.getText();
        String whiteBloodCellUText = whiteBloodCellUField.getText();
        String redBloodCellUText = redBloodCellUField.getText();

        // Blood analysis fields
        String whiteBloodCellBText = whiteBloodCellBField.getText();
        String redBloodCellBText = redBloodCellBField.getText();
        String haemoglobinText = haemoglobinField.getText();
        String lymphocyteText = lymphocyteField.getText();

        Patient currentPatient = UserSession.getInstance().getPatient();
        CsvHandler csvHandler = new CsvHandler();

        if (analysisType == AnalysisType.BIOBLOOD) {
            String errorText = "";

            boolean sodiumIsValid = MiscUtils.isDouble(sodiumText);
            boolean potassiumIsValid = MiscUtils.isDouble(potassiumText);
            boolean ureaIsValid = MiscUtils.isDouble(ureaText);
            boolean creatinineIsValid = MiscUtils.isDouble(creatinineText);
            boolean glucoseIsValid = MiscUtils.isDouble(glucoseText);
            boolean biluribinIsValid = MiscUtils.isDouble(biluribinText);
            boolean astIsValid = MiscUtils.isDouble(astText);
            boolean altIsValid = MiscUtils.isDouble(altText);

            if (!sodiumIsValid)
                errorText += "Enter a number value for sodium.";
            if (!potassiumIsValid)
                errorText += "Enter a number value for potassium.";
            if (!ureaIsValid)
                errorText += "Enter a number value for urea.";
            if (!creatinineIsValid)
                errorText += "Enter a number value for creatinine.";
            if (!glucoseIsValid)
                errorText += "Enter a number value for glucose.";
            if (!biluribinIsValid)
                errorText += "Enter a number value for biluribin.";
            if (!astIsValid)
                errorText += "Enter a number value for AST.";
            if (!altIsValid)
                errorText += "Enter a number value for ALT.";

            if (errorText.isBlank()) {
                BioBloodAnalysis bioBloodAnalysis = new BioBloodAnalysis(UUID.randomUUID().toString(),
                        currentPatient.getNid(), labId, labName,
                        labAddress, date, Double.parseDouble(sodiumText),
                        Double.parseDouble(potassiumText),
                        Double.parseDouble(ureaText),
                        Double.parseDouble(creatinineText),
                        Double.parseDouble(glucoseText),
                        Double.parseDouble(biluribinText),
                        Double.parseDouble(astText),
                        Double.parseDouble(altText));

                csvHandler.addAnalysis(bioBloodAnalysis);
                return true;
            } else {
                Alert errorAlert = new Alert(AlertType.ERROR);
                errorAlert.setHeaderText("Invalid input");
                errorAlert.setContentText(errorText);
                errorAlert.showAndWait();

                return false;
            }

        } else if (analysisType == AnalysisType.BLOOD) {
            String errorText = "";

            boolean whiteBloodCellBIsValid = MiscUtils.isDouble(whiteBloodCellBText);
            boolean redBloodCellBIsValid = MiscUtils.isDouble(redBloodCellBText);
            boolean haemoglobinIsValid = MiscUtils.isDouble(haemoglobinText);
            boolean lymphocyteIsValid = MiscUtils.isDouble(lymphocyteText);

            if (!whiteBloodCellBIsValid)
                errorText += "Enter a number value for WBC.";
            if (!redBloodCellBIsValid)
                errorText += "Enter a number value for RBC.";
            if (!haemoglobinIsValid)
                errorText += "Enter a number value for haemoglobin.";
            if (!lymphocyteIsValid)
                errorText += "Enter a number value for lymphocytes.";

            if (errorText.isBlank()) {
                BloodAnalysis bloodAnalysis = new BloodAnalysis(UUID.randomUUID().toString(), currentPatient.getNid(),
                        labId, labName, labAddress,
                        date, Double.parseDouble(whiteBloodCellBText),
                        Double.parseDouble(redBloodCellBText),
                        Double.parseDouble(haemoglobinText),
                        Double.parseDouble(lymphocyteText));

                csvHandler.addAnalysis(bloodAnalysis);
                return true;
            } else {
                Alert errorAlert = new Alert(AlertType.ERROR);
                errorAlert.setHeaderText("Invalid input");
                errorAlert.setContentText(errorText);
                errorAlert.showAndWait();

                return false;
            }
        } else if (analysisType == AnalysisType.URINE) {
            String errorText = "";

            boolean clarityIsValid = !clarityText.isBlank();
            boolean crystalsIsValid = !crystalsText.isBlank();
            boolean bacteriaIsValid = !bacteriaText.isBlank();
            boolean ketoneIsValid = MiscUtils.isDouble(ketoneText);
            boolean proteinIsValid = MiscUtils.isDouble(proteinText);
            boolean clinitestIsValid = MiscUtils.isDouble(clinitestText);
            boolean whiteBloodCellUIsValid = MiscUtils.isDouble(whiteBloodCellUText);
            boolean redBloodCellUIsValid = MiscUtils.isDouble(redBloodCellUText);

            if (clarityIsValid)
                errorText += "Clarity description cannot be empty.";
            if (crystalsIsValid)
                errorText += "Crystals description cannot be empty.";
            if (bacteriaIsValid)
                errorText += "Bacteria field cannot be empty.";
            if (ketoneIsValid)
                errorText += "Enter a number value for ketone.";
            if (proteinIsValid)
                errorText += "Enter a number value for protein.";
            if (clinitestIsValid)
                errorText += "Enter a number value for clinitest.";
            if (whiteBloodCellUIsValid)
                errorText += "Enter a number value for WBC.";
            if (redBloodCellUIsValid)
                errorText += "Enter a number value for RBC.";

            if (errorText.isBlank()) {
                UrineAnalysis urineAnalysis = new UrineAnalysis(UUID.randomUUID().toString(), currentPatient.getNid(),
                        labId, labName,
                        labAddress, date,
                        clarityText, crystalsText, bacteriaText, Double.parseDouble(ketoneText),
                        Double.parseDouble(proteinText),
                        Double.parseDouble(clinitestText),
                        Double.parseDouble(whiteBloodCellUText),
                        Double.parseDouble(redBloodCellUText));

                csvHandler.addAnalysis(urineAnalysis);
                return true;
            } else {
                Alert errorAlert = new Alert(AlertType.ERROR);
                errorAlert.setHeaderText("Invalid input");
                errorAlert.setContentText(errorText);
                errorAlert.showAndWait();

                return false;
            }
        }

        return false;
    }
}

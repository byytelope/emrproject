package emrproject;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Diagnosis;
import models.Patient;
import models.User;
import utils.CsvHandler;
import utils.MiscUtils;
import utils.UserSession;

public class DiagnosisReportsController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Text patientNameText;

    @FXML
    private Text patientNidText;

    @FXML
    private Button backButton;

    @FXML
    private Button newDiagnosisButton;

    @FXML
    private TableView<ObservableList<String>> diagnosisReportsTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CsvHandler csvHandler = new CsvHandler();

        diagnosisReportsTable.setPlaceholder(new Text("No diagnoses have been made."));
        diagnosisReportsTable.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                ObservableList<String> list = diagnosisReportsTable.getSelectionModel().getSelectedItem();
                Diagnosis report = csvHandler.getDiagnosis(list.get(0));

                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("diagnosisReport.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setUserData(report);
                    stage.setScene(new Scene(root1));
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        User currentUser = UserSession.getInstance().getUser();
        if (currentUser.getIsPatient())
            newDiagnosisButton.setVisible(false);

        Patient currentPatient = UserSession.getInstance()
                .getPatient();
        patientNameText.setText(currentPatient.getName());
        patientNidText.setText(currentPatient.getNid());

        List<String> columnNames = Arrays.asList(new Diagnosis().toCsvHeader().split(","));

        MiscUtils.initializeTableColumns(columnNames, diagnosisReportsTable);
        List<Diagnosis> diagnoses = csvHandler
                .getAllDiagnoses(currentPatient.getNid());
        MiscUtils.initializeTableRows(diagnoses, diagnosisReportsTable);
    }

    public void backAction(ActionEvent e) throws IOException {
        User currentUser = UserSession.getInstance().getUser();

        root = FXMLLoader
                .load(getClass().getResource(currentUser.getIsPatient() ? "patientHome.fxml" : "doctorHome.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void newDiagnosisAction(ActionEvent e) throws IOException {
        root = FXMLLoader
                .load(getClass().getResource("diagnosisForm.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

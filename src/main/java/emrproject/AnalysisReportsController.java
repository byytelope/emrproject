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
import models.BaseAnalysis;
import models.BioBloodAnalysis;
import models.BloodAnalysis;
import models.Patient;
import models.UrineAnalysis;
import models.User;
import utils.CsvHandler;
import utils.MiscUtils;
import utils.UserSession;

public class AnalysisReportsController implements Initializable {
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
    private Button newAnalysisButton;

    @FXML
    private TableView<ObservableList<String>> analysisReportsTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CsvHandler csvHandler = new CsvHandler();

        analysisReportsTable.setPlaceholder(new Text("No analyses have been made."));
        analysisReportsTable.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                ObservableList<String> list = analysisReportsTable.getSelectionModel().getSelectedItem();
                String analysisType = list.get(6);

                if (analysisType.equals("BLOOD")) {
                    BloodAnalysis report = csvHandler.getBloodAnalysis(list.get(0));
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("bloodAnalysisReport.fxml"));
                        Parent root1 = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        stage.setUserData(report);
                        stage.setScene(new Scene(root1));
                        stage.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (analysisType.equals("BIOBLOOD")) {
                    BioBloodAnalysis report = csvHandler.getBioBloodAnalysis(list.get(0));
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("bioBloodAnalysisReport.fxml"));
                        Parent root1 = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        stage.setUserData(report);
                        stage.setScene(new Scene(root1));
                        stage.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (analysisType.equals("URINE")) {
                    UrineAnalysis report = csvHandler.getUrineAnalysis(list.get(0));
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("urineAnalysisReport.fxml"));
                        Parent root1 = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        stage.setUserData(report);
                        stage.setScene(new Scene(root1));
                        stage.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        User currentUser = UserSession.getInstance().getUser();
        if (currentUser.getIsPatient())
            newAnalysisButton.setVisible(false);

        Patient currentPatient = UserSession.getInstance().getPatient();
        patientNameText.setText(currentPatient.getName());
        patientNidText.setText(currentPatient.getNid());

        List<String> columnNames = Arrays.asList(new BaseAnalysis().toCsvHeader().split(","));

        MiscUtils.initializeTableColumns(columnNames, analysisReportsTable);
        List<BaseAnalysis> analyses = csvHandler.getAllAnalyses(currentPatient.getNid());
        MiscUtils.initializeTableRows(analyses, analysisReportsTable);
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

    public void newAnalysisAction(ActionEvent e) throws IOException {
        root = FXMLLoader
                .load(getClass().getResource("analysisForm.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

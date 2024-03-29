package emrproject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import models.Patient;
import models.User;
import utils.CsvHandler;
import utils.MiscUtils;
import utils.UserSession;

public class SignUpController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button backButton;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField confirmPasswordField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField ageField;

    @FXML
    private TextField contactNumberField;

    @FXML
    private TextField nationalIdField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField nationalityField;

    @FXML
    private ComboBox<String> genderBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> genderList = FXCollections.observableArrayList("Male", "Female");
        genderBox.setItems(genderList);
        ageField.setTextFormatter(
                new TextFormatter<>(change -> (change.getControlNewText().matches("([1-9][0-9]*)?")) ? change : null));
        contactNumberField.setTextFormatter(
                new TextFormatter<>(change -> (change.getControlNewText().matches("([1-9][0-9]*)?")) ? change : null));
    }

    public void backAction(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("signIn.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void signUpAction(ActionEvent e) throws IOException {
        if (infoVerified()) {
            root = FXMLLoader.load(getClass().getResource("patientHome.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    private boolean infoVerified() {
        String email = emailField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        String name = nameField.getText();
        String age = ageField.getText();
        String contactNumber = contactNumberField.getText();
        String nid = nationalIdField.getText();
        String address = addressField.getText();
        String nationality = nationalityField.getText();
        String gender = genderBox.getValue();

        boolean emailIsValid = !email.isBlank() && email.contains("@");
        boolean passwordIsValid = !password.isBlank() && password.length() > 6;
        boolean confirmPasswordIsValid = password.contentEquals(confirmPassword);
        boolean nameIsValid = !name.isBlank() && name.length() > 6;
        boolean ageIsValid = MiscUtils.isInteger(age);
        boolean contactNumberIsValid = MiscUtils.isInteger(contactNumber);
        boolean nidIsValid = !nid.isBlank();
        boolean addressIsValid = !address.isBlank();
        boolean nationalityIsValid = !nationality.isBlank();
        boolean genderIsValid = gender != null;

        String errorText = "";

        if (!emailIsValid)
            errorText += "Email is invalid.\n";
        if (!passwordIsValid)
            errorText += "Password should be longer than 6 characters.\n";
        if (!confirmPasswordIsValid)
            errorText += "Passwords do not match.\n";
        if (!nameIsValid)
            errorText += "Please enter your full name.\n";
        if (!ageIsValid)
            errorText += "Age should be an integer.\n";
        if (!contactNumberIsValid)
            errorText += "Contact number should only include integers.\n";
        if (!nidIsValid)
            errorText += "Enter valid NID.\n";
        if (!addressIsValid)
            errorText += "Current address cannot be blank.\n";
        if (!nationalityIsValid)
            errorText += "Enter a valid nationality. Eg: Malaysian\n";
        if (!genderIsValid)
            errorText += "Please select a gender.\n";

        if (errorText.isBlank()) {
            User user = new User(nid, name, email, password, true);
            Patient patient = new Patient(nid, name, gender, address, nationality, email, contactNumber,
                    Integer.parseInt(age),
                    new ArrayList<String>());

            UserSession.getInstance().setUser(user);

            CsvHandler csvHandler = new CsvHandler();
            csvHandler.addUser(user);
            csvHandler.addPatient(patient);

            return true;
        } else {
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setHeaderText("Invalid input");
            errorAlert.setContentText(errorText);
            errorAlert.showAndWait();

            return false;
        }
    }
}

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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import models.Patient;
import models.User;
import utils.CsvHandler;
import utils.MiscUtils;
import utils.UserSession;

public class UpdateInfoController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button backButton;

    @FXML
    private Button updateInfoButton;

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField confirmPasswordField;

    @FXML
    private TextField contactNumberField;

    @FXML
    private TextField addressField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        contactNumberField.setTextFormatter(
                new TextFormatter<>(change -> (change.getControlNewText().matches("([1-9][0-9]*)?")) ? change : null));
    }

    public void backAction(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("patientHome.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void updateInfoAction(ActionEvent e) throws IOException {
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
        String contactNumber = contactNumberField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        String address = addressField.getText();

        CsvHandler csvHandler = new CsvHandler();
        User currentUser = UserSession.getInstance().getUser();
        Patient currentPatient = UserSession.getInstance().getPatient();

        if (email.isBlank())
            email = currentPatient.getEmail();
        if (contactNumber.isBlank())
            contactNumber = currentPatient.getContactNumber();
        if (password.isBlank() && confirmPassword.isBlank()) {
            password = currentUser.getPassword();
            confirmPassword = currentUser.getPassword();
        }
        if (address.isBlank())
            address = currentPatient.getAddress();

        boolean emailIsValid = email.contains("@");
        boolean contactNumberIsValid = MiscUtils.isInteger(contactNumber);
        boolean passwordIsValid = !password.isBlank() && password.length() > 6;
        boolean confirmPasswordIsValid = password.contentEquals(confirmPassword);

        String errorText = "";

        if (!emailIsValid)
            errorText += "Email is invalid.\n";
        if (!contactNumberIsValid)
            errorText += "Contact number should only include integers.\n";
        if (!passwordIsValid)
            errorText += "Password should be longer than 6 characters.\n";
        if (!confirmPasswordIsValid)
            errorText += "Passwords do not match.\n";

        if (errorText.isBlank()) {
            currentUser.setEmail(email);
            currentUser.setPassword(password);
            currentPatient.setEmail(email);
            currentPatient.setContactNumber(contactNumber);

            UserSession.getInstance().setUser(currentUser);
            csvHandler.updateUser(currentUser);
            csvHandler.updatePatient(currentPatient);

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

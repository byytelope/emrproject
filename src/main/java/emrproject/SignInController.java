package emrproject;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import models.Patient;
import models.User;
import utils.CsvHandler;
import utils.UserSession;

public class SignInController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField emailField;

    @FXML
    private TextFlow signUpTextFlow;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signInButton;

    @FXML
    private Hyperlink signUpLink;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void switchToSignUp(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("signUpForm.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void signInAction(ActionEvent e) throws IOException {
        CsvHandler csvHandler = new CsvHandler();
        List<User> users = csvHandler.getAllUsers();
        User currentUser = null;

        for (User user : users) {
            if (user.getEmail().equals(emailField.getText()) && user.getPassword().equals(passwordField.getText())) {
                currentUser = user;
                break;
            } else {
                continue;
            }
        }

        if (currentUser == null) {
            Alert loginAlert = new Alert(AlertType.ERROR);
            loginAlert.setHeaderText("User not found");
            loginAlert.setContentText("Please enter valid email and password or sign up");
            loginAlert.showAndWait();
        } else {
            Patient currentPatient = csvHandler.getPatient(currentUser.getNid());
            UserSession.getInstance().setUser(currentUser);
            UserSession.getInstance().setPatient(currentPatient);
            System.out.println("Logged in");
            System.out.println(currentUser);
            System.out.println(currentPatient);

            root = currentUser.getIsPatient()
                    ? FXMLLoader.load(getClass().getResource("patientHome.fxml"))
                    : FXMLLoader.load(getClass().getResource("doctorHome.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
}

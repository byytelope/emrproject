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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

public class SignInController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ToggleButton doctorToggle;

    @FXML
    private ToggleButton patientToggle;

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

    public void toggleAction(ActionEvent e) throws IOException {

    }

    public void signInAction(ActionEvent e) throws IOException {
        if (validateLogin()) {
            System.out.println("Logged in");
            root = doctorToggle.isSelected() && !patientToggle.isSelected()
                    ? FXMLLoader.load(getClass().getResource("doctorHome.fxml"))
                    : FXMLLoader.load(getClass().getResource("patientHome.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    private boolean validateLogin() {
        return true;
    }
}

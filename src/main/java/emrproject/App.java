package emrproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent signInRoot = FXMLLoader.load(getClass().getResource("signIn.fxml"));
        Scene signInScene = new Scene(signInRoot);

        stage.setResizable(false);
        stage.setTitle("EMR");
        // stage.getIcons().add(new Image(getClass().getResourceAsStream("globe.png")));
        stage.setScene(signInScene);
        stage.show();
    }
}

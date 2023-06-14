module emrproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens emrproject to javafx.fxml;

    exports emrproject;
}

module emrproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires transitive javafx.graphics;

    opens emrproject to javafx.fxml;

    exports emrproject;
    exports utils;
    exports models;
}

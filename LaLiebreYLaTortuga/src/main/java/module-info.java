module com.svhuerta.laliebreylatortuga {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.svhuerta.laliebreylatortuga to javafx.fxml;
    exports com.svhuerta.laliebreylatortuga;
}

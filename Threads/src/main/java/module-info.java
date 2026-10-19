module com.svhuerta.threads {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.svhuerta.threads to javafx.fxml;
    exports com.svhuerta.threads;
}

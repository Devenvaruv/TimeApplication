module com.example.timeapplication {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.timeapplication to javafx.fxml;
    exports com.example.timeapplication;
}
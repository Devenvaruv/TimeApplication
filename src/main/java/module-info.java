module com.example.timeapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;


    opens com.example.timeapplication to javafx.fxml;
    exports com.example.timeapplication;
}
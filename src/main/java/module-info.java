module com.project.inventorymanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires java.sql;


    opens com.project.inventorymanagement to javafx.fxml;
    exports com.project.inventorymanagement;
}
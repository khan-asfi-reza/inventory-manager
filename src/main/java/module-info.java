module com.project.inventorymanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;


    opens com.project.inventorymanagement to javafx.fxml;
    exports com.project.inventorymanagement;
}
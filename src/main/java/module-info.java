module com.project.inventorymanagement {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.project.inventorymanagement to javafx.fxml;
    exports com.project.inventorymanagement;
}
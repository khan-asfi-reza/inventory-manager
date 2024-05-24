package com.project.inventorymanagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Objects;


public class Main extends Application {
    static {
        Font.loadFont(Objects.requireNonNull(Tasks.class.getResource("styles/static/OpenSans-Regular.ttf")).toExternalForm(), 12);
        Font.loadFont(Objects.requireNonNull(Tasks.class.getResource("styles/static/OpenSans-Medium.ttf")).toExternalForm(), 12);
        Font.loadFont(Objects.requireNonNull(Tasks.class.getResource("styles/static/OpenSans-Bold.ttf")).toExternalForm(), 12);
    }
    public static void main(String[] args) {
        Tasks.mainMethod();
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Login.fxml")));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles/styles.css")).toExternalForm());
        primaryStage.setTitle("Inventory Management");
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("Sprite.png")).toExternalForm()));
        primaryStage.setScene(scene);
        primaryStage.show();

        //method to created to call logout method below
        primaryStage.setOnCloseRequest(event -> {
            event.consume();  //b|c of this line, the app will not close if you pressed cancel
            logout(primaryStage);
        });

    }

    public void logout(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("You're about to exit!");
        if(alert.showAndWait().get() == ButtonType.OK) {
            stage.close();
        }
    }
}

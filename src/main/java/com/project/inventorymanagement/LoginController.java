package com.project.inventorymanagement;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import java.io.IOException;

public class LoginController {


    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    Label invalidLabel;

    @FXML
    TextField usernameField;

    @FXML
    TextField passwordField;


    public void login(ActionEvent event) throws IOException {

        String username = usernameField.getText();
        String password = passwordField.getText();

        if(User.authenticate(username, password)){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
            root = loader.load();
            HomeController controller = loader.getController();
            controller.setUsernameLabel(username);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            scene.setRoot(root);
            stage.setScene(scene);
            stage.show();
        }
        else{
            invalidLabel.setText("Invalid Username or Password");
        }

    }

    public void moveToSignup(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Signup.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}

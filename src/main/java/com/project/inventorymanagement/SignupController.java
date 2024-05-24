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

public class SignupController {


    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    Label invalidLabel;

    @FXML
    TextField usernameField;

    @FXML
    TextField passwordField;


    public void signup(ActionEvent event) throws IOException {

        String username = usernameField.getText();
        String password = passwordField.getText();

        if(username.isEmpty() || password.isEmpty()){
            invalidLabel.setText("Username and Password is empty");
        }
        else{
            try {
                User.createUser(username, password);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
                root = loader.load();
                HomeController homeController = loader.getController();
                homeController.setUsernameLabel(username);
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            catch (UserAlreadyExistsException e){
                invalidLabel.setText("Username already exists");
            }
        }
    }

    public void moveToLogin(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}

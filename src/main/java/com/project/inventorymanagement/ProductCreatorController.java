package com.project.inventorymanagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ProductCreatorController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    ObservableList<String> categories = FXCollections.observableArrayList("Game", "Music", "Movie");

    @FXML
    private TextField nameField ;
    @FXML
    private TextField priceField;
    @FXML
    private TextField genreField;
    @FXML
    private TextField discountField;
    @FXML
    private ChoiceBox<String> category;

    @FXML
    private TextField creatorField;

    @FXML
    private TextField yearPublishedField;
    @FXML
    private Label errorLabel;

    @FXML
    private Label successLabel;

    @FXML
    private Label usernameLabel;

    private Inventory inventory;
    private Invoice invoice;

    public void setUsernameLabel(String username) {
        usernameLabel.setText(username);
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        category.setItems(categories);
    }

    public void goToProduct(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
        root = loader.load();
        HomeController controller = loader.getController();
        controller.setInvoice(invoice);
        controller.setInventory(inventory);
        controller.setUsernameLabel(usernameLabel.getText());
        controller.refreshTables();
        if(invoice != null){
            controller.setItemCountLabel("Items: " + invoice.getItemCount());
            controller.setTotalCountLabel("Total: " + invoice.calculateDiscountedPrice());
        }
        else{
            controller.setItemCountLabel("Items: ");
            controller.setTotalCountLabel("Total: ");
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void createProduct(ActionEvent event) throws IOException {
        String name = nameField.getText();
        String price = priceField.getText();
        String genre = genreField.getText();
        String discount = discountField.getText();
        String creator = creatorField.getText();
        String yearPublished = yearPublishedField.getText();
        String categoryValue = category.getValue();
        if(name.trim().isEmpty() || price.trim().isEmpty() || genre.trim().isEmpty() || discount.trim().isEmpty() || creator.trim().isEmpty() || yearPublished.trim().isEmpty() || categoryValue.trim().isEmpty()){
            errorLabel.setText("Please input all fields");
        }
        else{
            try {
                double priceInt = Double.parseDouble(price);
                double discountInt = Double.parseDouble(discount);
                int yearPublishedInt = Integer.parseInt(yearPublished);

                switch (categoryValue) {
                    case "Game" -> {
                        inventory.addItem(new Game(name, 0, priceInt, genre, yearPublishedInt, discountInt, 0, creator));
                        successLabel.setText("Successfully created Game ");
                    }
                    case "Music" -> {
                        inventory.addItem(new Music(name, 0, priceInt, genre, yearPublishedInt, discountInt, 0, creator));
                        successLabel.setText("Successfully created Music ");
                    }
                    case "Movie" -> {
                        inventory.addItem(new Movie(name, 0, priceInt, genre, yearPublishedInt, discountInt, 0, creator));
                        successLabel.setText("Successfully created Movie ");
                    }
                    default -> errorLabel.setText("Invalid category value");
                }
            }
            catch(NumberFormatException e){
                errorLabel.setText("Please input a valid number");
            }
        }

    }


}

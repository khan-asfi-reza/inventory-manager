package com.project.inventorymanagement;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Time;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class InvoiceController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label invoiceSave;

    @FXML
    private Label itemCountLabel;
    @FXML
    private Label totalCountLabel;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox vboxContainer;

    private Inventory inventory;
    private Invoice invoice;

    public void setUsernameLabel(String username) {
        usernameLabel.setText(username);
    }

    public void setItemCountLabel(String itemCount) {
        itemCountLabel.setText(itemCount);
    }

    public void setTotalCountLabel(String totalCount) {
        totalCountLabel.setText(totalCount);
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
        setupProductTable();
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scrollPane.setFitToWidth(true);
        setupProductTable();
    }

    private void setupProductTable() {
        if(invoice != null){
            TableView<StockableProduct<?>> tableView = new TableView<>();

            TableColumn<StockableProduct<?>, String> nameCol = new TableColumn<>("Name");
            nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

            TableColumn<StockableProduct<?>, Integer> idCol = new TableColumn<>("Product ID");
            idCol.setCellValueFactory(new PropertyValueFactory<>("productId"));

            TableColumn<StockableProduct<?>, Double> priceCol = new TableColumn<>("Price");
            priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

            tableView.getColumns().add(nameCol);
            tableView.getColumns().add(idCol);
            tableView.getColumns().add(priceCol);

            invoice.getItems().forEach(tableView.getItems()::add);

            // Invoice Label
            Label label = new Label("Invoice");
            label.setStyle("-fx-font-weight: bold;");
            label.setStyle("-fx-font-size: 20px;");
            // Date label
            Label dateLabel = new Label(invoice.getLocalDateTime());
            // Total Price without discount label
            Label totalPriceWithoutDiscountLabel = new Label("Total Price Without Discount: " + String.format("%.2f", invoice.getPriceWithoutDiscount()));
            totalPriceWithoutDiscountLabel.setStyle("-fx-font-size: 18px");
            // Total price with discount label
            Label totalPriceLabel = new Label("Total Price : " +  String.format("%.2f", invoice.calculateDiscountedPrice()));
            totalPriceLabel.setStyle("-fx-font-size: 18px");
            vboxContainer.getChildren().addAll(label, dateLabel, tableView, totalPriceWithoutDiscountLabel, totalPriceLabel);
        }
    }

    public void goToProduct(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
        root = loader.load();
        HomeController controller = loader.getController();
        controller.setInvoice(invoice);
        controller.setInventory(inventory);
        controller.setUsernameLabel(usernameLabel.getText());
        controller.setItemCountLabel(itemCountLabel.getText());
        controller.setTotalCountLabel(totalCountLabel.getText());
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void saveInvoice(ActionEvent event) throws IOException {
        String path = "data/invoices/";
        File dir = new File(path);
        invoiceSave.setText("Invoice saved successfully");
        if(!dir.exists()){
            boolean _ = dir.mkdirs();
        }
        try {
            PrintWriter printWriter = new PrintWriter(path+"/invoice"+invoice.getUnixTimestamp()+".txt");
            printWriter.println(invoice.getInvoice());
            printWriter.close();
            Invoice invoice = new Invoice();
            setTotalCountLabel("Total: ");
            setItemCountLabel("Items: ");
            setInvoice(invoice);
            Thread.sleep(5000);
            goToProduct(event);
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}

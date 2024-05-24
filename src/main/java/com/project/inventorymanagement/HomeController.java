package com.project.inventorymanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
public class HomeController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox vboxContainer;

    @FXML
    private Label usernameLabel;
    @FXML
    private Label itemCountLabel;
    @FXML
    private Label totalCountLabel;

    private Inventory inventory;
    private Invoice invoice;

    ArrayList<TableView<?>> tableViews = new ArrayList<>();

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

    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scrollPane.setFitToWidth(true); // Make sure the scroll pane fits the content to its width
        setTables();
    }

    public void setTables(){
        VBox vbox = new VBox(10); // Create a VBox with spacing
        vbox.setFillWidth(true);
        if(inventory == null){
            inventory = new Inventory();
        }
        if(invoice == null){
            invoice = new Invoice();
        }
        setupTableForType(inventory.getProductByClass(Game.class), Game.class, "Games");
        setupTableForType(inventory.getProductByClass(Movie.class), Movie.class, "Movies");
        setupTableForType(inventory.getProductByClass(Music.class), Music.class, "Music");
    }

    void refreshTables(){
        vboxContainer.getChildren().clear();
        System.out.println("A");
        System.out.println(inventory.getProductByClass(Game.class));
        setTables();
    }

    private <T extends StockableProduct<?>> void setupTableForType(ArrayList<T> items, Class<T> type, String labelName) {
        TableView<T> tableView = new TableView<>();
        setupCommonColumns(tableView);
        addTypeSpecificColumn(tableView, type);

        // Update cart for invoice
        TableColumn<T, Void> actionCol = new TableColumn<>("Cart");
        actionCol.setCellFactory(col -> new TableCell<T, Void>() {
            private final Button addButton = new Button("+");
            private final Button removeButton = new Button("-");

            {
                addButton.setOnAction(event -> {
                    T item = getTableView().getItems().get(getIndex());
                    invoice.addProduct(item);
                    itemCountLabel.setText("Items:" + invoice.getItemCount());
                    totalCountLabel.setText("Total:" + String.format("%.2f", invoice.calculateDiscountedPrice()));
                    getTableView().refresh();
                });
                removeButton.setOnAction(event -> {
                    T item = getTableView().getItems().get(getIndex());
                    invoice.removeProduct(item);
                    itemCountLabel.setText("Items:" + invoice.getItemCount());
                    totalCountLabel.setText("Total:" + String.format("%.2f", invoice.calculateDiscountedPrice()));
                    getTableView().refresh();
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox buttons = new HBox(addButton, removeButton);
                    setGraphic(buttons);
                    T currentItem = getTableView().getItems().get(getIndex());
                    addButton.setDisable(currentItem.getNumberOfItemsStocked() <= 0);
                }
            }
        });
        tableView.getColumns().add(actionCol);

        // Update stock
        TableColumn<T, Void> stockCol = new TableColumn<>("Stock Meter");
        stockCol.setCellFactory(col -> new TableCell<T, Void>() {
            private final Button addButton = new Button("+");
            private final Button removeButton = new Button("-");

            {
                addButton.setOnAction(event -> {
                    T item = getTableView().getItems().get(getIndex());
                    item.addStock(1);
                    item.save();
                    getTableView().refresh();
                });
                removeButton.setOnAction(event -> {
                    T item = getTableView().getItems().get(getIndex());
                    item.removeStock(1);
                    item.save();
                    getTableView().refresh();
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox buttons = new HBox(addButton, removeButton);
                    setGraphic(buttons);
                    T currentItem = getTableView().getItems().get(getIndex());
                    removeButton.setDisable(currentItem.getNumberOfItemsStocked() <= 0);
                }
            }
        });
        tableView.getColumns().add(stockCol);


        items.forEach(tableView.getItems()::add);
        Label label = new Label(labelName);
        vboxContainer.getChildren().addAll(label, tableView);
        tableViews.add(tableView);
    }

    private <T extends StockableProduct<?>> void setupCommonColumns(TableView<T> tableView) {

        // Name Column
        TableColumn<T, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableView.getColumns().add(nameCol);
        // Price Column
        TableColumn<T, Double> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        tableView.getColumns().add(priceCol);
        // Genre Column
        TableColumn<T, String> genreCol = new TableColumn<>("Genre");
        genreCol.setCellValueFactory(new PropertyValueFactory<>("genre"));
        tableView.getColumns().add(genreCol);
        // Year Published
        TableColumn<T, Integer> yearPublishedCol = new TableColumn<>("Year Published");
        yearPublishedCol.setCellValueFactory(new PropertyValueFactory<>("yearPublished"));
        tableView.getColumns().add(yearPublishedCol);
        // Discount Column
        TableColumn<T, Double> discountCol = new TableColumn<>("Discount");
        discountCol.setCellValueFactory(new PropertyValueFactory<>("discount"));
        tableView.getColumns().add(discountCol);
        // Stock Column
        TableColumn<T, Integer> stockCol = new TableColumn<>("Stock");
        stockCol.setCellValueFactory(new PropertyValueFactory<>("numberOfItemsStocked"));
        tableView.getColumns().add(stockCol);


    }

    private <T extends StockableProduct<?>> void addTypeSpecificColumn(TableView<T> tableView, Class<T> type) {
        // Specific columns as described before
        if (Game.class.equals(type)) {
            TableColumn<T, String> developerCol = new TableColumn<>("Developer");
            developerCol.setCellValueFactory(new PropertyValueFactory<>("developer"));
            tableView.getColumns().add(developerCol);
        } else if (Movie.class.equals(type)) {
            TableColumn<T, String> directorCol = new TableColumn<>("Director");
            directorCol.setCellValueFactory(new PropertyValueFactory<>("director"));
            tableView.getColumns().add(directorCol);
        } else if (Music.class.equals(type)) {
            TableColumn<T, String> artistNameCol = new TableColumn<>("Artist Name");
            artistNameCol.setCellValueFactory(new PropertyValueFactory<>("artistName"));
            tableView.getColumns().add(artistNameCol);
        }
    }

    public void goToInvoice(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Invoice.fxml"));
        root = loader.load();
        InvoiceController controller = loader.getController();
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

    public void goToCreate(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ProductCreator.fxml"));
        root = loader.load();
        ProductCreatorController controller = loader.getController();
        controller.setInvoice(invoice);
        controller.setInventory(inventory);
        controller.setUsernameLabel(usernameLabel.getText());
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
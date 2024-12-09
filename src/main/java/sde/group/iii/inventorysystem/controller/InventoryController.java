package sde.group.iii.inventorysystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sde.group.iii.inventorysystem.model.Inventory;
import sde.group.iii.inventorysystem.service.InventoryService;

public class InventoryController {
    @FXML
    private TableView<Inventory> inventoryTable;
    @FXML
    private TableColumn<Inventory, String> itemNameColumn;
    @FXML
    private TableColumn<Inventory, Double> itemPriceColumn;
    @FXML
    private TableColumn<Inventory, String> itemDescriptionColumn;
    @FXML
    private TextField nameField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField descriptionField;
    @FXML
    private Button addButton;
    @FXML
    private Button removeButton;
    @FXML
    private Button updateButton;

    private final InventoryService inventoryService = new InventoryService();
    private final ObservableList<Inventory> inventoryList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Bind table columns to model properties
        itemNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        itemPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        itemDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        // Set table data
        inventoryTable.setItems(inventoryList);
        loadInventoryData();
    }

    private void loadInventoryData() {
        inventoryList.setAll(inventoryService.getAllItems());
    }

    @FXML
    private void handleAddItem() {
        String name = nameField.getText().trim();
        String priceText = priceField.getText().trim();
        String description = descriptionField.getText().trim();

        if (name.isEmpty() || priceText.isEmpty() || description.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please fill in all fields.");
            return;
        }

        try {
            double price = Double.parseDouble(priceText);
            Inventory newItem = new Inventory(name, price, description);
            inventoryService.addItem(newItem);
            loadInventoryData();
            clearFields();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Item added successfully.");
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid price format. Please enter a valid number.");
        }
    }

    @FXML
    private void handleRemoveItem() {
        Inventory selectedItem = inventoryTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            inventoryService.removeItem(selectedItem);
            loadInventoryData();
            clearFields();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Item removed successfully.");
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Please select an item to remove.");
        }
    }

    @FXML
    private void handleUpdateItem() {
        Inventory selectedItem = inventoryTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            String name = nameField.getText().trim();
            String priceText = priceField.getText().trim();
            String description = descriptionField.getText().trim();

            if (name.isEmpty() || priceText.isEmpty() || description.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error", "Please fill in all fields.");
                return;
            }

            try {
                double price = Double.parseDouble(priceText);
                selectedItem.setName(name);
                selectedItem.setPrice(price);
                selectedItem.setDescription(description);
                inventoryService.updateItem(selectedItem);
                loadInventoryData();
                clearFields();
                showAlert(Alert.AlertType.INFORMATION, "Success", "Item updated successfully.");
            } catch (NumberFormatException e) {
                showAlert(Alert.AlertType.ERROR, "Error", "Invalid price format. Please enter a valid number.");
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Please select an item to update.");
        }
    }

    private void clearFields() {
        nameField.clear();
        priceField.clear();
        descriptionField.clear();
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

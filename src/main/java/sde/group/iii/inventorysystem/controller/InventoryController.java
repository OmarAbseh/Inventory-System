package sde.group.iii.inventorysystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import sde.group.iii.inventorysystem.model.Inventory;
import sde.group.iii.inventorysystem.service.InventoryService;

import java.util.List;

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

    private InventoryService inventoryService = new InventoryService();

    @FXML
    public void initialize() {
        itemNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        itemPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        itemDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        loadInventoryData();
    }

    private void loadInventoryData() {
        List<Inventory> inventoryList = inventoryService.getAllItems();
        inventoryTable.getItems().setAll(inventoryList);
    }

    @FXML
    private void handleAddItem() {
        String name = nameField.getText().trim();
        String priceText = priceField.getText().trim();
        String description = descriptionField.getText().trim();

        if (name.isEmpty() || priceText.isEmpty() || description.isEmpty()) {
            showAlert("Error", "Please fill in all fields.");
            return;
        }

        try {
            double price = Double.parseDouble(priceText);
            Inventory newItem = new Inventory(name, price, description);
            inventoryService.addItem(newItem);
            loadInventoryData();
            showAlert("Success", "Item added successfully.");
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid price format.");
        }
    }

    @FXML
    private void handleRemoveItem() {
        Inventory selectedItem = inventoryTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            inventoryService.removeItem(selectedItem);
            loadInventoryData();
            showAlert("Success", "Item removed successfully.");
        } else {
            showAlert("Error", "Please select an item to remove.");
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
                showAlert("Error", "Please fill in all fields.");
                return;
            }

            try {
                double price = Double.parseDouble(priceText);
                selectedItem.setName(name);
                selectedItem.setPrice(price);
                selectedItem.setDescription(description);
                inventoryService.updateItem(selectedItem);
                loadInventoryData();
                showAlert("Success", "Item updated successfully.");
            } catch (NumberFormatException e) {
                showAlert("Error", "Invalid price format.");
            }
        } else {
            showAlert("Error", "Please select an item to update.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

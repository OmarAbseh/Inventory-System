package sde.group.iii.inventorysystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomepageController {
    @FXML
    private Button inventoryButton;
    @FXML
    private Button customersButton;
    @FXML
    private Button salesButton;
    @FXML
    private Button ordersButton;
    @FXML
    private Button settingsButton;

    @FXML
    private void navigateToInventory() {
        // Load inventory.fxml
        // Example: FXMLLoader.load(getClass().getResource("/sde/group/iii/inventorysystem/inventory.fxml"));
    }

    @FXML
    private void navigateToCustomers() {
        // Load customers.fxml
    }

    @FXML
    private void navigateToSales() {
        // Load sales.fxml
    }

    @FXML
    private void navigateToOrders() {
        // Load orders.fxml
    }

    @FXML
    private void navigateToSettings() {
        // Load settings.fxml
    }
}

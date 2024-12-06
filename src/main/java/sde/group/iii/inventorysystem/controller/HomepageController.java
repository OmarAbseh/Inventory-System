package sde.group.iii.inventorysystem.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

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
    private VBox mainContent; // Container for the main content section of the page

    @FXML
    public void initialize() {
        // Initialization logic (unchanged)
        addButtonHoverEffect(inventoryButton);
        addButtonHoverEffect(customersButton);
        addButtonHoverEffect(salesButton);
        addButtonHoverEffect(ordersButton);
        addButtonHoverEffect(settingsButton);
    }

    @FXML
    private void navigateToInventory() {
        loadPage("/sde/group/iii/inventorysystem/inventory.fxml");
    }

    @FXML
    private void navigateToCustomers() {
        loadPage("/sde/group/iii/inventorysystem/customers.fxml");
    }

    @FXML
    private void navigateToSales() {
        loadPage("/sde/group/iii/inventorysystem/sales.fxml");
    }

    @FXML
    private void navigateToOrders() {
        loadPage("/sde/group/iii/inventorysystem/orders.fxml");
    }

    @FXML
    private void navigateToSettings() {
        loadPage("/sde/group/iii/inventorysystem/settings.fxml");
    }

    private void loadPage(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            VBox newPage = loader.load();

            // Clear the current main content and add the new page
            mainContent.getChildren().clear();
            mainContent.getChildren().add(newPage);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading the FXML file: " + fxmlPath);
        }
    }

    private void addButtonHoverEffect(Button button) {
        button.setOnMouseEntered(event -> button.setStyle("-fx-background-color: #3E8E41; -fx-text-fill: white;"));
        button.setOnMouseExited(event -> button.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: black;"));
    }
}

package sde.group.iii.inventorysystem.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
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
    private VBox mainContent;

    @FXML
    public void initialize() {
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

            if (mainContent != null) {
                mainContent.getChildren().clear();
                mainContent.getChildren().add(newPage);
            }
        } catch (IOException e) {
            System.err.println("Error loading the FXML file: " + fxmlPath);
            e.printStackTrace();
        }
    }

    private void addButtonHoverEffect(Button button) {
        button.setOnMouseEntered(event -> button.setStyle("-fx-background-color: #3E8E41; -fx-text-fill: white;"));
        button.setOnMouseExited(event -> button.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: black;"));
    }
}

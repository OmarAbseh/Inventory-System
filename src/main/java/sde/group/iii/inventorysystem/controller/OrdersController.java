package sde.group.iii.inventorysystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sde.group.iii.inventorysystem.model.Orders;
import sde.group.iii.inventorysystem.service.OrdersService;

import java.time.LocalDate;

public class OrdersController {

    @FXML
    private TableView<Orders> orderTable;
    @FXML
    private TableColumn<Orders, Integer> orderIdColumn;
    @FXML
    private TableColumn<Orders, Integer> customerNameColumn;
    @FXML
    private TableColumn<Orders, String> statusColumn;
    @FXML
    private TableColumn<Orders, String> shippingAddressColumn;

    @FXML
    private TextField itemIdField;
    @FXML
    private TextField customerIdField;
    @FXML
    private TextField trackingNumberField;
    @FXML
    private TextField shippingAddressField;
    @FXML
    private DatePicker orderDateField;
    @FXML
    private DatePicker shippingDateField;

    @FXML
    private Button addOrderButton;
    @FXML
    private Button updateOrderButton;
    @FXML
    private Button removeOrderButton;

    private final OrdersService orderService = new OrdersService();
    private final ObservableList<Orders> orderList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Link columns to model properties
        orderIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("trackingNumber"));
        shippingAddressColumn.setCellValueFactory(new PropertyValueFactory<>("shippingAddress"));

        // Load data into the table
        orderTable.setItems(orderList);
        loadOrderData();
    }

    private void loadOrderData() {
        orderList.setAll(orderService.getAllOrders());
    }

    @FXML
    private void handleAddOrder() {
        try {
            int itemId = Integer.parseInt(itemIdField.getText().trim());
            int customerId = Integer.parseInt(customerIdField.getText().trim());
            String trackingNumber = trackingNumberField.getText().trim();
            String shippingAddress = shippingAddressField.getText().trim();
            LocalDate orderDate = orderDateField.getValue();
            LocalDate shippingDate = shippingDateField.getValue();

            if (trackingNumber.isEmpty() || shippingAddress.isEmpty() || orderDate == null || shippingDate == null) {
                showAlert(Alert.AlertType.ERROR, "Error", "Please fill in all fields.");
                return;
            }

            Orders newOrder = new Orders(0, itemId, customerId, trackingNumber, shippingAddress, orderDate, shippingDate);
            orderService.addOrder(newOrder);
            loadOrderData();
            clearFields();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Order added successfully.");
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please enter valid numeric values for IDs.");
        }
    }

    @FXML
    private void handleUpdateOrder() {
        Orders selectedOrder = orderTable.getSelectionModel().getSelectedItem();
        if (selectedOrder != null) {
            try {
                int itemId = Integer.parseInt(itemIdField.getText().trim());
                int customerId = Integer.parseInt(customerIdField.getText().trim());
                String trackingNumber = trackingNumberField.getText().trim();
                String shippingAddress = shippingAddressField.getText().trim();
                LocalDate orderDate = orderDateField.getValue();
                LocalDate shippingDate = shippingDateField.getValue();

                if (trackingNumber.isEmpty() || shippingAddress.isEmpty() || orderDate == null || shippingDate == null) {
                    showAlert(Alert.AlertType.ERROR, "Error", "Please fill in all fields.");
                    return;
                }

                selectedOrder.setItemId(itemId);
                selectedOrder.setCustomerId(customerId);
                selectedOrder.setTrackingNumber(trackingNumber);
                selectedOrder.setShippingAddress(shippingAddress);
                selectedOrder.setOrderDate(orderDate);
                selectedOrder.setShippingDate(shippingDate);

                orderService.updateOrder(selectedOrder);
                loadOrderData();
                clearFields();
                showAlert(Alert.AlertType.INFORMATION, "Success", "Order updated successfully.");
            } catch (NumberFormatException e) {
                showAlert(Alert.AlertType.ERROR, "Error", "Please enter valid numeric values for IDs.");
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Please select an order to update.");
        }
    }

    @FXML
    private void handleRemoveOrder() {
        Orders selectedOrder = orderTable.getSelectionModel().getSelectedItem();
        if (selectedOrder != null) {
            orderService.deleteOrder(selectedOrder.getId());
            loadOrderData();
            clearFields();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Order removed successfully.");
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Please select an order to remove.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void clearFields() {
        itemIdField.clear();
        customerIdField.clear();
        trackingNumberField.clear();
        shippingAddressField.clear();
        orderDateField.setValue(null);
        shippingDateField.setValue(null);
    }
}

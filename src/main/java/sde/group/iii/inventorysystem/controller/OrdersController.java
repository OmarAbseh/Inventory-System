package sde.group.iii.inventorysystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import sde.group.iii.inventorysystem.model.Orders;
import sde.group.iii.inventorysystem.service.OrdersService;

import java.util.List;

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

    private final OrdersService orderService = new OrdersService();

    @FXML
    public void initialize() {
        // Link columns to model properties
        orderIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("trackingNumber"));
        shippingAddressColumn.setCellValueFactory(new PropertyValueFactory<>("shippingAddress"));

        // Load data into the table
        loadOrderData();
    }

    private void loadOrderData() {
        List<Orders> orderList = orderService.getAllOrders();
        orderTable.getItems().setAll(orderList);
    }
}

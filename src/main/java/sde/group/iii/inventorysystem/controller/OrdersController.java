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
    private TableColumn<Orders, String> orderIdColumn;
    @FXML
    private TableColumn<Orders, String> customerNameColumn;
    @FXML
    private TableColumn<Orders, String> statusColumn;
    @FXML
    private TableColumn<Orders, String> shippingAddressColumn;

    private OrdersService orderService = new OrdersService();

    @FXML
    public void initialize() {
        orderIdColumn.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        shippingAddressColumn.setCellValueFactory(new PropertyValueFactory<>("shippingAddress"));

        loadOrderData();
    }

    private void loadOrderData() {
        List<Orders> orderList = orderService.getAllOrders();
        orderTable.getItems().setAll(orderList);
    }
}

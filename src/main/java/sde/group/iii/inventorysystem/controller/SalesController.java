package sde.group.iii.inventorysystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sde.group.iii.inventorysystem.model.Sales;
import sde.group.iii.inventorysystem.service.SalesService;

import java.util.List;

public class SalesController {

    @FXML
    private TableView<Sales> salesTable;
    @FXML
    private TableColumn<Sales, Integer> saleIdColumn;
    @FXML
    private TableColumn<Sales, Integer> customerIdColumn; // Customer ID
    @FXML
    private TableColumn<Sales, Integer> itemIdColumn;     // Item ID
    @FXML
    private TableColumn<Sales, String> saleDateColumn;
    @FXML
    private TableColumn<Sales, Double> saleAmountColumn;

    private final SalesService salesService = new SalesService();

    @FXML
    public void initialize() {
        // Map table columns to the Sales model properties
        saleIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        itemIdColumn.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        saleDateColumn.setCellValueFactory(new PropertyValueFactory<>("saleDate"));
        saleAmountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));

        loadSalesData();
    }

    private void loadSalesData() {
        List<Sales> salesList = salesService.getAllSales();
        salesTable.getItems().setAll(salesList);
    }
}

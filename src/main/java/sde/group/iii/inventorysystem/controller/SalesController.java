package sde.group.iii.inventorysystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import sde.group.iii.inventorysystem.model.Sales;
import sde.group.iii.inventorysystem.service.SalesService;

import java.util.List;

public class SalesController {
    @FXML
    private TableView<Sales> salesTable;
    @FXML
    private TableColumn<Sales, String> itemColumn;
    @FXML
    private TableColumn<Sales, Double> priceColumn;
    @FXML
    private TableColumn<Sales, Integer> quantityColumn;
    @FXML
    private TableColumn<Sales, Double> totalColumn;

    private SalesService salesService = new SalesService();

    @FXML
    public void initialize() {
        itemColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));

        loadSalesData();
    }

    private void loadSalesData() {
        List<Sales> salesList = salesService.getAllSales();
        salesTable.getItems().setAll(salesList);
    }
}

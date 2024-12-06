package sde.group.iii.inventorysystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import sde.group.iii.inventorysystem.model.Customer;
import sde.group.iii.inventorysystem.service.CustomerService;

import java.util.List;

public class CustomersController {
    @FXML
    private TableView<Customer> customerTable;
    @FXML
    private TableColumn<Customer, String> nameColumn;
    @FXML
    private TableColumn<Customer, String> phoneColumn;
    @FXML
    private TableColumn<Customer, String> emailColumn;
    @FXML
    private TextField nameField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField emailField;
    @FXML
    private Button addCustomerButton;
    @FXML
    private Button updateCustomerButton;
    @FXML
    private Button removeCustomerButton;

    private CustomerService customerService = new CustomerService();

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        loadCustomerData();
    }

    private void loadCustomerData() {
        List<Customer> customerList = customerService.getAllCustomers();
        customerTable.getItems().setAll(customerList);
    }

    @FXML
    private void handleAddCustomer() {
        String name = nameField.getText().trim();
        String phone = phoneField.getText().trim();
        String email = emailField.getText().trim();

        if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            showAlert("Error", "Please fill in all fields.");
            return;
        }

        Customer newCustomer = new Customer(name, phone, email);
        customerService.addCustomer(newCustomer);
        loadCustomerData();
        showAlert("Success", "Customer added successfully.");
    }

    @FXML
    private void handleUpdateCustomer() {
        Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            String name = nameField.getText().trim();
            String phone = phoneField.getText().trim();
            String email = emailField.getText().trim();

            if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
                showAlert("Error", "Please fill in all fields.");
                return;
            }

            selectedCustomer.setName(name);
            selectedCustomer.setPhone(phone);
            selectedCustomer.setEmail(email);
            customerService.updateCustomer(selectedCustomer);
            loadCustomerData();
            showAlert("Success", "Customer updated successfully.");
        } else {
            showAlert("Error", "Please select a customer to update.");
        }
    }

    @FXML
    private void handleRemoveCustomer() {
        Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            customerService.deleteCustomer(selectedCustomer.getId());
            loadCustomerData();
            showAlert("Success", "Customer removed successfully.");
        } else {
            showAlert("Error", "Please select a customer to remove.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

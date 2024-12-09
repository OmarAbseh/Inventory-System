package sde.group.iii.inventorysystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import sde.group.iii.inventorysystem.model.Customer;
import sde.group.iii.inventorysystem.service.CustomerService;

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
    private ObservableList<Customer> customerList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        customerTable.setItems(customerList);
        loadCustomerData();
    }

    private void loadCustomerData() {
        customerList.setAll(customerService.getAllCustomers());
    }

    @FXML
    private void handleAddCustomer() {
        String name = nameField.getText().trim();
        String phone = phoneField.getText().trim();
        String email = emailField.getText().trim();

        if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            showAlert(AlertType.ERROR, "Error", "Please fill in all fields.");
            return;
        }

        Customer newCustomer = new Customer(name, phone, email);
        customerService.addCustomer(newCustomer);
        loadCustomerData();
        clearFields();
        showAlert(AlertType.INFORMATION, "Success", "Customer added successfully.");
    }

    @FXML
    private void handleUpdateCustomer() {
        Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            String name = nameField.getText().trim();
            String phone = phoneField.getText().trim();
            String email = emailField.getText().trim();

            if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
                showAlert(AlertType.ERROR, "Error", "Please fill in all fields.");
                return;
            }

            selectedCustomer.setName(name);
            selectedCustomer.setPhone(phone);
            selectedCustomer.setEmail(email);
            customerService.updateCustomer(selectedCustomer);
            loadCustomerData();
            clearFields();
            showAlert(AlertType.INFORMATION, "Success", "Customer updated successfully.");
        } else {
            showAlert(AlertType.ERROR, "Error", "Please select a customer to update.");
        }
    }

    @FXML
    private void handleRemoveCustomer() {
        Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            customerService.deleteCustomer(selectedCustomer.getId());
            loadCustomerData();
            clearFields();
            showAlert(AlertType.INFORMATION, "Success", "Customer removed successfully.");
        } else {
            showAlert(AlertType.ERROR, "Error", "Please select a customer to remove.");
        }
    }

    private void showAlert(AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void clearFields() {
        nameField.clear();
        phoneField.clear();
        emailField.clear();
    }
}

package sde.group.iii.inventorysystem.service;

import sde.group.iii.inventorysystem.dao.CustomerDAO;
import sde.group.iii.inventorysystem.model.Customer;

import java.util.List;

public class CustomerService {
    private final CustomerDAO customerDao;

    public CustomerService() {
        this.customerDao = new CustomerDAO();
    }

    public List<Customer> getAllCustomers() {
        return customerDao.getAllCustomers();
    }

    public Customer getCustomerById(int id) {
        return customerDao.getCustomerById(id);
    }

    public void addCustomer(Customer customer) {
        customerDao.addCustomer(customer);
    }

    public void updateCustomer(Customer customer) {
        customerDao.updateCustomer(customer);
    }

    public void deleteCustomer(int id) {
        customerDao.deleteCustomer(id);
    }
}

package sde.group.iii.inventorysystem.service;

import sde.group.iii.inventorysystem.dao.OrderDAO;
import sde.group.iii.inventorysystem.model.Orders;

import java.util.List;

public class OrdersService {
    private final OrderDAO ordersDao;

    public OrdersService() {
        this.ordersDao = new OrderDAO();
    }

    public List<Orders> getAllOrders() {
        return ordersDao.getAllOrders();
    }

    public Orders getOrderById(int id) {
        return ordersDao.getOrderById(id);
    }

    public void addOrder(Orders order) {
        ordersDao.addOrder(order);
    }

    public void updateOrder(Orders order) {
        ordersDao.updateOrder(order);
    }

    public void deleteOrder(int id) {
        ordersDao.deleteOrder(id);
    }
}

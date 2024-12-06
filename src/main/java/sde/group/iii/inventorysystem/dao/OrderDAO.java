package sde.group.iii.inventorysystem.dao;

import sde.group.iii.inventorysystem.model.Orders;
import sde.group.iii.inventorysystem.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    public List<Orders> getAllOrders() {
        List<Orders> orders = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM orders";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Orders order = new Orders();
                order.setId(resultSet.getInt("id"));
                order.setItemId(resultSet.getInt("item_id"));
                order.setCustomerId(resultSet.getInt("customer_id"));
                order.setTrackingNumber(resultSet.getString("tracking_number"));
                order.setShippingAddress(resultSet.getString("shipping_address"));
                order.setOrderDate(resultSet.getObject("order_date", java.time.LocalDate.class));
                order.setShippingDate(resultSet.getObject("shipping_date", java.time.LocalDate.class));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public Orders getOrderById(int orderId) {
        Orders order = null;
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM orders WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                order = new Orders();
                order.setId(resultSet.getInt("id"));
                order.setItemId(resultSet.getInt("item_id"));
                order.setCustomerId(resultSet.getInt("customer_id"));
                order.setTrackingNumber(resultSet.getString("tracking_number"));
                order.setShippingAddress(resultSet.getString("shipping_address"));
                order.setOrderDate(resultSet.getObject("order_date", java.time.LocalDate.class));
                order.setShippingDate(resultSet.getObject("shipping_date", java.time.LocalDate.class));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    public void addOrder(Orders order) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO orders (item_id, customer_id, tracking_number, shipping_address, order_date, shipping_date) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, order.getItemId());
            statement.setInt(2, order.getCustomerId());
            statement.setString(3, order.getTrackingNumber());
            statement.setString(4, order.getShippingAddress());
            statement.setObject(5, order.getOrderDate());
            statement.setObject(6, order.getShippingDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateOrder(Orders order) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "UPDATE orders SET item_id = ?, customer_id = ?, tracking_number = ?, shipping_address = ?, order_date = ?, shipping_date = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, order.getItemId());
            statement.setInt(2, order.getCustomerId());
            statement.setString(3, order.getTrackingNumber());
            statement.setString(4, order.getShippingAddress());
            statement.setObject(5, order.getOrderDate());
            statement.setObject(6, order.getShippingDate());
            statement.setInt(7, order.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteOrder(int orderId) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "DELETE FROM orders WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, orderId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

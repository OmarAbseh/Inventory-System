package sde.group.iii.inventorysystem.dao;

import sde.group.iii.inventorysystem.model.Inventory;
import sde.group.iii.inventorysystem.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {

    // Method to get all items from the database
    public List<Inventory> getAllItems() {
        List<Inventory> items = new ArrayList<>();
        String query = "SELECT * FROM items";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Inventory item = new Inventory();
                item.setId(resultSet.getInt("id"));
                item.setName(resultSet.getString("name"));
                item.setPrice(resultSet.getDouble("price"));
                item.setDescription(resultSet.getString("description"));
                item.setStockQuantity(resultSet.getInt("stock_quantity"));
                item.setReadyToShipQuantity(resultSet.getInt("ready_to_ship_quantity"));
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    // Method to get a single item by its ID
    public Inventory getItemById(int itemId) {
        Inventory item = null;
        String query = "SELECT * FROM items WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, itemId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                item = new Inventory();
                item.setId(resultSet.getInt("id"));
                item.setName(resultSet.getString("name"));
                item.setPrice(resultSet.getDouble("price"));
                item.setDescription(resultSet.getString("description"));
                item.setStockQuantity(resultSet.getInt("stock_quantity"));
                item.setReadyToShipQuantity(resultSet.getInt("ready_to_ship_quantity"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return item;
    }

    // Method to add a new item to the database
    public boolean addItem(Inventory item) {
        String query = "INSERT INTO items (name, price, description, stock_quantity, ready_to_ship_quantity) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, item.getName());
            preparedStatement.setDouble(2, item.getPrice());
            preparedStatement.setString(3, item.getDescription());
            preparedStatement.setInt(4, item.getStockQuantity());
            preparedStatement.setInt(5, item.getReadyToShipQuantity());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to update an existing item in the database
    public boolean updateItem(Inventory item) {
        String query = "UPDATE items SET name = ?, price = ?, description = ?, stock_quantity = ?, ready_to_ship_quantity = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, item.getName());
            preparedStatement.setDouble(2, item.getPrice());
            preparedStatement.setString(3, item.getDescription());
            preparedStatement.setInt(4, item.getStockQuantity());
            preparedStatement.setInt(5, item.getReadyToShipQuantity());
            preparedStatement.setInt(6, item.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to delete an item from the database by ID
    public boolean deleteItem(int itemId) {
        String query = "DELETE FROM items WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, itemId);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

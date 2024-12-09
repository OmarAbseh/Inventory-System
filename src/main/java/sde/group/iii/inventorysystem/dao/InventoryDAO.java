package sde.group.iii.inventorysystem.dao;

import sde.group.iii.inventorysystem.model.Inventory;
import sde.group.iii.inventorysystem.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAO {

    public List<Inventory> getAllItems() {
        List<Inventory> items = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM inventory";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Inventory item = new Inventory();
                item.setId(resultSet.getInt("id"));
                item.setName(resultSet.getString("name"));
                item.setPrice(resultSet.getDouble("price"));
                item.setDescription(resultSet.getString("description"));
                item.setStockQuantity(resultSet.getInt("stock_quantity"));
                item.setReadyToShipQuantity(resultSet.getInt("ready_to_ship_quantity"));
                item.setDateAdded(resultSet.getTimestamp("date_added"));
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public Inventory getItemById(int id) {
        Inventory item = null;
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM inventory WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                item = new Inventory();
                item.setId(resultSet.getInt("id"));
                item.setName(resultSet.getString("name"));
                item.setPrice(resultSet.getDouble("price"));
                item.setDescription(resultSet.getString("description"));
                item.setStockQuantity(resultSet.getInt("stock_quantity"));
                item.setReadyToShipQuantity(resultSet.getInt("ready_to_ship_quantity"));
                item.setDateAdded(resultSet.getTimestamp("date_added"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    public void addItem(Inventory item) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO inventory (name, price, description, stock_quantity, ready_to_ship_quantity, date_added) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, item.getName());
            statement.setDouble(2, item.getPrice());
            statement.setString(3, item.getDescription());
            statement.setInt(4, item.getStockQuantity());
            statement.setInt(5, item.getReadyToShipQuantity());
            statement.setTimestamp(6, item.getDateAdded());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateItem(Inventory item) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "UPDATE inventory SET name = ?, price = ?, description = ?, stock_quantity = ?, ready_to_ship_quantity = ?, date_added = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, item.getName());
            statement.setDouble(2, item.getPrice());
            statement.setString(3, item.getDescription());
            statement.setInt(4, item.getStockQuantity());
            statement.setInt(5, item.getReadyToShipQuantity());
            statement.setTimestamp(6, item.getDateAdded());
            statement.setInt(7, item.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeItem(Inventory item) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "DELETE FROM inventory WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, item.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

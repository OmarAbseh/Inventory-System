package sde.group.iii.inventorysystem.dao;

import sde.group.iii.inventorysystem.model.Sales;
import sde.group.iii.inventorysystem.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalesDAO {

    public List<Sales> getAllSales() {
        List<Sales> salesList = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM sales";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Sales sale = new Sales();
                sale.setId(resultSet.getInt("id"));
                sale.setItemId(resultSet.getInt("item_id"));
                sale.setCustomerId(resultSet.getInt("customer_id"));
                sale.setSaleDate(resultSet.getDate("sale_date").toLocalDate());
                sale.setAmount(resultSet.getDouble("amount"));
                salesList.add(sale);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salesList;
    }

    public Sales getSaleById(int id) {
        Sales sale = null;
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM sales WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                sale = new Sales();
                sale.setId(resultSet.getInt("id"));
                sale.setItemId(resultSet.getInt("item_id"));
                sale.setCustomerId(resultSet.getInt("customer_id"));
                sale.setSaleDate(resultSet.getDate("sale_date").toLocalDate());
                sale.setAmount(resultSet.getDouble("amount"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sale;
    }

    public void addSale(Sales sale) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO sales (item_id, customer_id, sale_date, amount) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, sale.getItemId());
            statement.setInt(2, sale.getCustomerId());
            statement.setDate(3, java.sql.Date.valueOf(sale.getSaleDate()));
            statement.setDouble(4, sale.getAmount());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSale(Sales sale) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "UPDATE sales SET item_id = ?, customer_id = ?, sale_date = ?, amount = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, sale.getItemId());
            statement.setInt(2, sale.getCustomerId());
            statement.setDate(3, java.sql.Date.valueOf(sale.getSaleDate()));
            statement.setDouble(4, sale.getAmount());
            statement.setInt(5, sale.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSale(int id) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "DELETE FROM sales WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

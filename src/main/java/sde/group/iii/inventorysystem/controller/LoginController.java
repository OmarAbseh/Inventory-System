package sde.group.iii.inventorysystem.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sde.group.iii.inventorysystem.utils.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private AnchorPane loginPane;

    @FXML
    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Please enter both username and password.");
            return;
        }

        if (validateLogin(username, password)) {
            showAlert("Success", "Login successful!");
            navigateToHomePage();
        } else {
            showAlert("Error", "Invalid username or password.");
        }
    }

    private boolean validateLogin(String username, String password) {
        boolean isValid = false;

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                isValid = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "An error occurred while connecting to the database.");
        }

        return isValid;
    }

    private void navigateToHomePage() {
        try {
            // Load the homepage FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sde/group/iii/inventorysystem/homepage.fxml"));
            AnchorPane homePage = loader.load();

            // Get the current stage (window)
            Stage stage = (Stage) loginPane.getScene().getWindow();
            Scene scene = new Scene(homePage);

            // Set the scene and show the new stage
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load the homepage.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

package sde.group.iii.inventorysystem.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sde.group.iii.inventorysystem.utils.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private VBox loginPane; // Root element of loginpage.fxml
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;

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

    public void navigateToHomePage() {
        if (loginPane == null || loginPane.getScene() == null) {
            System.out.println("loginPane or Scene is null. Please check the FXML linkage.");
            return;
        }

        Stage stage = (Stage) loginPane.getScene().getWindow();
        try {
            // Correct the path here according to your project structure
            Parent root = FXMLLoader.load(getClass().getResource("/sde/group/iii/inventorysystem/homepage.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load the home page.");
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

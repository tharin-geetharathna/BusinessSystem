package gripsystem.grip.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import gripsystem.grip.service.DataService;

import java.io.File;
import java.io.IOException;

public class LoginController {
    @FXML
    private TextField usernameField;
    
    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if ("Tharin".equals(username) && "THARINpro12345".equals(password)) {
            // Test file operations first
            DataService.testFileOperations();
            
            // Initialize default inventory only if needed
            if (DataService.shouldInitializeDefaultInventory()) {
                DataService.initializeDefaultInventory();
            }
            
            // Open main interface
            openMainInterface();
        } else {
            showAlert("Login Failed", "Invalid username or password. Please try again.");
        }
    }

    private void openMainInterface() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gripsystem/grip/gripInterface.fxml"));
            Parent root = loader.load();
            
            Stage stage = (Stage) usernameField.getScene().getWindow();
            Scene scene = new Scene(root, 1200, 800);
            stage.setTitle("Grip System - Main Interface");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Could not open main interface.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
} 
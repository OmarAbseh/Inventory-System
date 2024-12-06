package sde.group.iii.inventorysystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;
import sde.group.iii.inventorysystem.utils.Settings;
import javafx.collections.FXCollections;

public class SettingsController {

    @FXML
    private ComboBox<String> languageComboBox;
    @FXML
    private ComboBox<String> currencyComboBox;
    @FXML
    private ToggleGroup themeToggleGroup;
    @FXML
    private RadioButton lightThemeRadio;
    @FXML
    private RadioButton darkThemeRadio;

    @FXML
    public void initialize() {
        // Add items to the combo boxes
        languageComboBox.setItems(FXCollections.observableArrayList("English", "Spanish", "French"));
        currencyComboBox.setItems(FXCollections.observableArrayList("USD", "Euro", "HUF"));

        // Load the current settings and set them in the UI
        Settings.loadSettings(languageComboBox, currencyComboBox, themeToggleGroup);

        // Set the correct theme radio button based on the loaded theme
        String currentTheme = Settings.getCurrentTheme();
        if (currentTheme != null) {
            if ("Light".equalsIgnoreCase(currentTheme)) {
                themeToggleGroup.selectToggle(lightThemeRadio);
            } else if ("Dark".equalsIgnoreCase(currentTheme)) {
                themeToggleGroup.selectToggle(darkThemeRadio);
            }
        }
    }

    @FXML
    private void handleSaveSettings() {
        // Get the selected values from the UI
        String selectedLanguage = languageComboBox.getValue();
        String selectedCurrency = currencyComboBox.getValue();

        // Ensure a valid theme is selected
        String selectedTheme = null;
        if (themeToggleGroup.getSelectedToggle() != null) {
            selectedTheme = ((RadioButton) themeToggleGroup.getSelectedToggle()).getText();
        }

        // Validate the selected values before saving
        if (selectedLanguage != null && selectedCurrency != null && selectedTheme != null) {
            // Save the settings using the Settings utility class
            Settings.saveSettings(selectedLanguage, selectedCurrency, selectedTheme);
            System.out.println("Settings saved successfully!");
        } else {
            System.err.println("Please select all settings before saving.");
        }
    }
}

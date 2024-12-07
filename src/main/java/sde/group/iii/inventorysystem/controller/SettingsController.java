package sde.group.iii.inventorysystem.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import sde.group.iii.inventorysystem.utils.Settings;

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
        // Populate language and currency ComboBoxes with options
        languageComboBox.setItems(FXCollections.observableArrayList("English", "Spanish", "French"));
        currencyComboBox.setItems(FXCollections.observableArrayList("USD", "Euro", "HUF"));

        // Load the current settings and set them in the UI
        Settings.loadSettings(languageComboBox, currencyComboBox, themeToggleGroup);

        // Ensure the correct theme is selected
        if (themeToggleGroup != null) {
            String currentTheme = Settings.getCurrentTheme();
            if ("Light".equalsIgnoreCase(currentTheme)) {
                themeToggleGroup.selectToggle(lightThemeRadio);
            } else if ("Dark".equalsIgnoreCase(currentTheme)) {
                themeToggleGroup.selectToggle(darkThemeRadio);
            }
        }
    }

    @FXML
    private void handleSaveSettings() {
        try {
            // Retrieve selected values from UI elements
            String selectedLanguage = languageComboBox.getValue();
            String selectedCurrency = currencyComboBox.getValue();

            // Ensure a valid theme is selected
            String selectedTheme = null;
            if (themeToggleGroup.getSelectedToggle() != null) {
                selectedTheme = ((RadioButton) themeToggleGroup.getSelectedToggle()).getText();
            }

            // Validate selections before saving
            if (selectedLanguage != null && selectedCurrency != null && selectedTheme != null) {
                // Save settings using the utility class
                Settings.saveSettings(selectedLanguage, selectedCurrency, selectedTheme);
                System.out.println("Settings saved successfully!");
            } else {
                System.err.println("Error: Please select all options before saving.");
            }
        } catch (Exception e) {
            System.err.println("Error saving settings: " + e.getMessage());
        }
    }
}

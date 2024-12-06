package sde.group.iii.inventorysystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;
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
        languageComboBox.getItems().addAll("English", "Spanish", "French");
        currencyComboBox.getItems().addAll("USD", "Euro", "HUF");

        // Load the current settings and set them in the UI
        Settings.loadSettings(languageComboBox, currencyComboBox, themeToggleGroup);
    }

    @FXML
    private void handleSaveSettings() {
        String selectedLanguage = languageComboBox.getValue();
        String selectedCurrency = currencyComboBox.getValue();
        String selectedTheme = ((RadioButton) themeToggleGroup.getSelectedToggle()).getText();

        Settings.saveSettings(selectedLanguage, selectedCurrency, selectedTheme);
    }
}

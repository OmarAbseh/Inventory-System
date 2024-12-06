package sde.group.iii.inventorysystem.utils;

import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Toggle;
import javafx.scene.control.RadioButton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Settings {
    private static final String SETTINGS_FILE = "settings.properties";

    public static void loadSettings(ComboBox<String> languageComboBox, ComboBox<String> currencyComboBox, ToggleGroup themeToggleGroup) {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(SETTINGS_FILE)) {
            properties.load(fis);

            // Set the language
            String language = properties.getProperty("language", "English");
            languageComboBox.setValue(language);

            // Set the currency
            String currency = properties.getProperty("currency", "USD");
            currencyComboBox.setValue(currency);

            // Set the theme
            String theme = properties.getProperty("theme", "Light");
            for (Toggle toggle : themeToggleGroup.getToggles()) {
                if (toggle instanceof RadioButton && ((RadioButton) toggle).getText().equalsIgnoreCase(theme)) {
                    themeToggleGroup.selectToggle(toggle);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveSettings(String language, String currency, String theme) {
        Properties properties = new Properties();
        properties.setProperty("language", language);
        properties.setProperty("currency", currency);
        properties.setProperty("theme", theme);

        try (FileOutputStream fos = new FileOutputStream(SETTINGS_FILE)) {
            properties.store(fos, "User Settings");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package sde.group.iii.inventorysystem.utils;

import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Toggle;
import javafx.scene.control.RadioButton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import java.util.Properties;

public class Settings {
    private static final String SETTINGS_FILE = "settings.properties";

    /**
     * Loads settings from the properties file and sets them into the provided UI components.
     *
     * @param languageComboBox The ComboBox for selecting the language.
     * @param currencyComboBox  The ComboBox for selecting the currency.
     * @param themeToggleGroup  The ToggleGroup for selecting the theme.
     */
    public static void loadSettings(ComboBox<String> languageComboBox, ComboBox<String> currencyComboBox, ToggleGroup themeToggleGroup) {
        Properties properties = new Properties();
        File settingsFile = new File(SETTINGS_FILE);

        if (!settingsFile.exists()) {
            System.out.println("Settings file not found. Creating with default settings.");
            // Create the file with default settings if it doesn't exist
            saveSettings("English", "USD", "Light");
            return; // Skip further loading if a new settings file is created
        }

        try (FileInputStream fis = new FileInputStream(SETTINGS_FILE)) {
            properties.load(fis);

            // Set the language
            String language = properties.getProperty("language", "English");
            if (language != null && !language.isEmpty()) {
                languageComboBox.setValue(language);
            }

            // Set the currency
            String currency = properties.getProperty("currency", "USD");
            if (currency != null && !currency.isEmpty()) {
                currencyComboBox.setValue(currency);
            }

            // Set the theme
            String theme = properties.getProperty("theme", "Light");
            if (theme != null && !theme.isEmpty()) {
                for (Toggle toggle : themeToggleGroup.getToggles()) {
                    if (toggle instanceof RadioButton && ((RadioButton) toggle).getText().equalsIgnoreCase(theme)) {
                        themeToggleGroup.selectToggle(toggle);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading settings: " + e.getMessage());
        }
    }

    /**
     * Saves the provided settings to the properties file.
     *
     * @param language The selected language.
     * @param currency The selected currency.
     * @param theme    The selected theme.
     */
    public static void saveSettings(String language, String currency, String theme) {
        Properties properties = new Properties();
        properties.setProperty("language", language);
        properties.setProperty("currency", currency);
        properties.setProperty("theme", theme);

        try (FileOutputStream fos = new FileOutputStream(SETTINGS_FILE)) {
            properties.store(fos, "User Settings");
            System.out.println("Settings saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error saving settings: " + e.getMessage());
        }
    }

    /**
     * Retrieves the current theme from the properties file.
     *
     * @return The current theme as a String. Defaults to "Light" if not found.
     */
    public static String getCurrentTheme() {
        Properties properties = new Properties();
        File settingsFile = new File(SETTINGS_FILE);

        if (!settingsFile.exists()) {
            System.out.println("Settings file not found. Returning default theme 'Light'.");
            return "Light"; // Return default theme if the file doesn't exist
        }

        try (FileInputStream fis = new FileInputStream(SETTINGS_FILE)) {
            properties.load(fis);
            String theme = properties.getProperty("theme", "Light");
            if (theme != null && !theme.isEmpty()) {
                return theme;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error retrieving current theme: " + e.getMessage());
        }
        return "Light"; // Return default theme if there is an error
    }
}

package sde.group.iii.inventorysystem.utils;

import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

import java.io.*;
import java.util.Properties;

public class Settings {

    private static final String SETTINGS_FILE = "settings.properties";

    /**
     * Loads settings from the properties file and sets them into the provided UI components.
     *
     * @param languageComboBox The ComboBox for selecting the language.
     * @param currencyComboBox The ComboBox for selecting the currency.
     * @param themeToggleGroup The ToggleGroup for selecting the theme.
     */
    public static void loadSettings(ComboBox<String> languageComboBox, ComboBox<String> currencyComboBox, ToggleGroup themeToggleGroup) {
        Properties properties = new Properties();
        File settingsFile = new File(SETTINGS_FILE);

        if (!settingsFile.exists()) {
            System.out.println("Settings file not found. Creating with default settings.");
            // Create the file with default settings if it doesn't exist
            saveSettings("English", "USD", "Light");
            applyDefaults(languageComboBox, currencyComboBox, themeToggleGroup);
            return; // Skip further loading
        }

        try (FileInputStream fis = new FileInputStream(settingsFile)) {
            properties.load(fis);

            // Set language
            String language = properties.getProperty("language", "English");
            languageComboBox.setValue(language);

            // Set currency
            String currency = properties.getProperty("currency", "USD");
            currencyComboBox.setValue(currency);

            // Set theme
            String theme = properties.getProperty("theme", "Light");
            if (themeToggleGroup != null) {
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
            return "Light"; // Default theme
        }

        try (FileInputStream fis = new FileInputStream(settingsFile)) {
            properties.load(fis);
            return properties.getProperty("theme", "Light");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error retrieving current theme: " + e.getMessage());
        }
        return "Light"; // Default theme in case of an error
    }

    /**
     * Applies default settings to the UI components.
     *
     * @param languageComboBox The ComboBox for selecting the language.
     * @param currencyComboBox The ComboBox for selecting the currency.
     * @param themeToggleGroup The ToggleGroup for selecting the theme.
     */
    private static void applyDefaults(ComboBox<String> languageComboBox, ComboBox<String> currencyComboBox, ToggleGroup themeToggleGroup) {
        languageComboBox.setValue("English");
        currencyComboBox.setValue("USD");
        if (themeToggleGroup != null) {
            for (Toggle toggle : themeToggleGroup.getToggles()) {
                if (toggle instanceof RadioButton && ((RadioButton) toggle).getText().equalsIgnoreCase("Light")) {
                    themeToggleGroup.selectToggle(toggle);
                    break;
                }
            }
        }
    }
}

package com.example.wallethub.driver;

import org.openqa.selenium.WebDriver;

import static com.example.wallethub.config.ConfigurationManager.configuration;

/**
 * This class provides a singleton instance of WebDriver.
 *
 * @author tahanima
 */
public final class DriverManager {
    private static WebDriver driver = null;

    private DriverManager() {}

    /**
     * @return an instance of browser driver implementation
     */
    public static WebDriver createDriver() {
        if (driver == null) {
            driver =
                    BrowserFactory.valueOf(configuration().browser().toUpperCase())
                            .initializeDriver();
        }

        return driver;
    }
}
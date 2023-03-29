package com.example.wallethub.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.util.concurrent.TimeUnit;

import static com.example.wallethub.config.ConfigurationManager.configuration;

public enum BrowserFactory {
    /** Contains all the initialization logic for the chrome driver. */
    CHROME {
        @Override
        public WebDriver initializeDriver() {
            WebDriverManager.chromedriver().setup();
            WebDriver driver = new ChromeDriver(getOptions());

            driver.manage().timeouts().implicitlyWait(configuration().timeout(), TimeUnit.SECONDS);
            driver.manage().window().maximize();

            return driver;
        }

        private ChromeOptions getOptions() {
            ChromeOptions options = new ChromeOptions();

            options.setAcceptInsecureCerts(true);
            options.setHeadless(configuration().headless());

            return options;
        }
    },

    /** Contains all the initialization logic for the firefox driver. */
    FIREFOX {
        @Override
        public WebDriver initializeDriver() {
            WebDriverManager.firefoxdriver().setup();
            WebDriver driver = new FirefoxDriver(getOptions());

            driver.manage().timeouts().implicitlyWait(configuration().timeout(), TimeUnit.SECONDS);
            driver.manage().window().maximize();

            return driver;
        }

        private FirefoxOptions getOptions() {
            FirefoxOptions options = new FirefoxOptions();

            options.setAcceptInsecureCerts(true);
            options.setHeadless(configuration().headless());

            return options;
        }
    };

    /**
     * @return an instance of browser driver implementation
     */
    public abstract WebDriver initializeDriver();
}
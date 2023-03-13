package com.example.wallethub.pages;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static com.example.wallethub.config.ConfigurationManager.configuration;

public class BasePage {
    private WebDriver driver;

    public void initialize(final WebDriver webdriver) {
        this.driver = webdriver;

        PageFactory.initElements(driver, this);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void captureScreenshot(String fileName) {
        Shutterbug.shootPage(driver)
                .withName(fileName)
                .save(configuration().baseReportPath() + configuration().baseScreenshotPath());
    }
}

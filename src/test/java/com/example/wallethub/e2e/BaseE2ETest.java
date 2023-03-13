package com.example.wallethub.e2e;

import com.example.wallethub.driver.DriverManager;
import com.example.wallethub.pages.BasePage;
import com.example.wallethub.pages.BasePageFactory;
import com.example.wallethub.utils.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;


@Listeners(TestListener.class)
public abstract class BaseE2ETest {
    private final WebDriver driver = DriverManager.createDriver();

    public abstract void initialize();

    protected <T extends BasePage> T createInstance(final Class<T> page) {
        return BasePageFactory.createInstance(driver, page);
    }

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeClass(alwaysRun = true)
    public void setup() {
        initialize();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
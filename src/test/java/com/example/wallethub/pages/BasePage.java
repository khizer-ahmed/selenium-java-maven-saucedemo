package com.example.wallethub.pages;

import com.assertthat.selenium_shutterbug.core.Shutterbug;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.example.wallethub.config.ConfigurationManager.configuration;

import java.security.SecureRandom;
import java.util.Random;

public class BasePage {
    private WebDriver driver;
    private JavascriptExecutor executor = (JavascriptExecutor) driver;
    
    
    public void initialize(final WebDriver webdriver) {
        this.driver = webdriver;
        PageFactory.initElements(driver, this);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait initializeWait(){
        WebDriverWait wait = new WebDriverWait(driver, 15);
        return wait;
    }

    public JavascriptExecutor initializeJSExecutor(){
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        return executor;
    }

    public void addWait() throws InterruptedException {
        Thread.sleep(2000);
    }
    

    public WebElement waitUntilpresent(WebElement element) {
        try {
            WebDriverWait wait  = initializeWait();
            element = wait.until(ExpectedConditions.elementToBeClickable(element));
            return element;
        } catch (StaleElementReferenceException e) {
            WebDriverWait wait  = initializeWait();
            element = wait.until(ExpectedConditions.elementToBeClickable(element));
            return element;
        }
    }

    public void waitUntilTitle(String title) {
        WebDriverWait wait  = initializeWait();
        wait.until(ExpectedConditions.titleIs(title));
    }

    public void click(WebElement element){
        waitUntilpresent(element).click();
    }

    public String getText(WebElement element){
        WebDriverWait wait  = initializeWait();
        return wait.until(ExpectedConditions.visibilityOf(element)).getText();
    }

    public WebElement elementVisible(String locator){
        WebDriverWait wait  = initializeWait();
        By elementLocator = By.xpath(locator);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
    }

    public String getAttributeValue(WebElement element, String attribute){
        WebDriverWait wait  = initializeWait();
        return wait.until(ExpectedConditions.visibilityOf(element)).getAttribute(attribute);
    }

    public void scrollToElement(WebElement element){
        executor = initializeJSExecutor();
        executor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void jsClick(WebElement element){
        executor = initializeJSExecutor();
        executor.executeScript("arguments[0].click();", element);
    }

    public void hoverToElement(WebElement element){
        Actions actions = new Actions(getDriver());
        actions.moveToElement(waitUntilpresent(element));
        actions.pause(1000); 
        actions.build().perform();
    }

    public void captureScreenshot(String fileName) {
        Shutterbug.shootPage(driver)
                .withName(fileName)
                .save(configuration().baseReportPath() + configuration().baseScreenshotPath());
    }
        
    public String WriteRandomReview(){
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int LENGTH = 195;
        StringBuilder sb = new StringBuilder();
        Random random = new SecureRandom();
        for (int i = 0; i < LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }
    
}

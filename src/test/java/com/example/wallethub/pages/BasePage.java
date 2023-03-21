package com.example.wallethub.pages;

import com.assertthat.selenium_shutterbug.core.Shutterbug;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
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
        WebDriverWait wait = new WebDriverWait(driver, configuration().timeout());
        return wait;
    }

    public JavascriptExecutor initializeJSExecutor(){
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        return executor;
    }

    public void addWait() throws InterruptedException {
        Thread.sleep(2000);
    }
    
    public void type(String locator, String text){
        WebDriverWait wait  = initializeWait();
        By elementLocator = By.xpath(locator);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(elementLocator));
        element.clear();
        element.sendKeys(text);
    }

    public WebElement waitUntilClickable(String locator) {
        By elementLocator = By.xpath(locator);
        try {
            WebDriverWait wait  = initializeWait();
            return wait.until(ExpectedConditions.elementToBeClickable(elementLocator));
            
        } catch (StaleElementReferenceException e) {
            WebDriverWait wait  = initializeWait();
            return wait.until(ExpectedConditions.elementToBeClickable(elementLocator));
        }
    }

    public void waitUntilTitle(String title) {
        WebDriverWait wait  = initializeWait();
        wait.until(ExpectedConditions.titleIs(title));
    }

    public void click(String locator){
        waitUntilClickable(locator).click();
    }

    public String getText(String locator){
        By elementLocator = By.xpath(locator);
        WebDriverWait wait  = initializeWait();
        String text;
        try{
            text =  wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator)).getText();
            return text;
        }
        catch (TimeoutException e){
            text =  wait.until(ExpectedConditions.presenceOfElementLocated(elementLocator)).getText();
            return text;
        }
    }

    public WebElement waitUntilChildPresent(String parent, String child){
        WebDriverWait wait  = initializeWait();
        By parentLocator = By.xpath(parent);
        By childLocator = By.xpath(child);
        return wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(parentLocator, childLocator));
    }

    public WebElement waitUntilChildVisible(String parent, String child){
        By childLocator = By.xpath(child);
        waitUntilVisible(parent).findElement(childLocator);
        return waitUntilVisible(parent).findElement(childLocator);
    }

    public WebElement waitUntilVisible(String locator){
        WebDriverWait wait  = initializeWait();
        By elementLocator = By.xpath(locator);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
    }

    public WebElement waitElementPresent(String locator){
        WebDriverWait wait  = initializeWait();
        By elementLocator = By.xpath(locator);
        return wait.until(ExpectedConditions.presenceOfElementLocated(elementLocator));
    }

    public String getAttributeValue(String locator, String attribute){
        By elementLocator = By.xpath(locator);
        WebDriverWait wait  = initializeWait();
        String text;
        try{
            text =  wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator)).getAttribute(attribute);
            return text;
        }
        catch (TimeoutException e){
            text =  wait.until(ExpectedConditions.presenceOfElementLocated(elementLocator)).getAttribute(attribute);
            return text;
        }
    }

    public void scrollToElement(String locator){
        By elementLocator = By.xpath(locator);
        WebDriverWait wait  = initializeWait();
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
        executor = initializeJSExecutor();
        executor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void hoverToElement(String locator){
        Actions actions = new Actions(getDriver());
        actions.moveToElement(waitUntilClickable(locator));
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
        int LENGTH = 200;
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

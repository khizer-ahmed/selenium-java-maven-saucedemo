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
        WebElement element = waitUntilClickable(locator);
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
        return waitUntilVisible(locator).getText();
    }

    public String getHiddenElementText(String locator){
        return waitElementPresent(locator).getAttribute("innerHTML");
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
        try{
            return waitUntilVisible(locator).getAttribute(attribute);
            
        }
        catch (TimeoutException e){
            return waitElementPresent(locator).getAttribute(attribute);
        }
    }

    public void scrollToElement(String locator){
        executor = initializeJSExecutor();
        executor.executeScript("arguments[0].scrollIntoView(true);", waitUntilVisible(locator));
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
        
    public String WriteRandomString(){
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

    public static String WriteRandomReview() {
        String[] adjectives = {"amazing", "awesome", "fantastic", "wonderful", "outstanding", "excellent", "superb", "great", "terrific", "incredible"};
        String[] nouns = {"restaurant", "hotel", "movie", "book", "product", "service", "store", "experience", "vacation", "destination"};
    
        Random random = new Random();
        String adjective = adjectives[random.nextInt(adjectives.length)];
        String noun = nouns[random.nextInt(nouns.length)];
    
        String message = "I recently had an " + adjective + " experience at " + noun + ". The " + noun + " was " + adjective + " in every way possible. The staff was friendly and attentive, the ambiance was perfect, and the " + noun + " itself was top-notch. I highly recommend anyone who is looking for a " + adjective + " " + noun + " to visit this place. Trust me, you won't be disappointed! I've been to many " + nouns[random.nextInt(nouns.length)] + " before, but this one really stood out. I can't wait to go back and experience it all over again. Overall, it was an " + adjective + " experience that I'll never forget.";
        return message;
    }
}
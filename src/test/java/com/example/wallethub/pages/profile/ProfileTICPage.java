package com.example.wallethub.pages.profile;
import com.example.wallethub.pages.BasePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.example.wallethub.config.ConfigurationManager.configuration;


public final class ProfileTICPage extends BasePage {
    
    private String writeReview =  WriteRandomReview();
    private String profileName = "Test Insurance Company";
    private String reviewConfirmTitle = "WalletHub - Review Confirmation";
    private String expectedConfirmReviewMessage = "Awesome!Your review has been posted.";
    private String fourStarRating = "4 Star Rating";
    private static WebElement reviewAuthor;
    private static WebElement reviewArticle;
    private static WebElement reviewAuthorNick;
    private static String reviewXpath;
    private static WebElement reviewRating;
    private static WebElement reviewMessage;

    private String url = configuration().baseUrl() + "/profile/13732055i";

    @FindBy(xpath = "//div[@class='rv review-action ng-enter-element']")
    private WebElement reviewSection;

    @FindBy(xpath = "//h3[text()=\"What's Your Rating?\"]//following::div[1]//child::*[@aria-label='1 star rating']")
    private WebElement ratingFirstStar;
    
    @FindBy(xpath = "//h3[text()=\"What's Your Rating?\"]//following::div[1]//child::*[@aria-label='2 star rating']")
    private WebElement ratingSecondStar;

    @FindBy(xpath = "//h3[text()=\"What's Your Rating?\"]//following::div[1]//child::*[@aria-label='3 star rating']")
    private WebElement ratingThirdStar;

    @FindBy(xpath = "//h3[text()=\"What's Your Rating?\"]//following::div[1]//child::*[@aria-label='4 star rating']")
    private WebElement ratingFourthStar;

    @FindBy(xpath = "//span[text()='Select...']//parent::div")
    private WebElement policyDropdown;

    @FindBy(xpath = "//li[text()='Health Insurance']")
    private WebElement healthInsurancepolicy;

    @FindBy(xpath = "//textarea[@placeholder='Write your review...']")
    private WebElement writeReviewField;

    @FindBy(xpath = "//div[text()=' Submit ']")
    private WebElement submitReviewButton;
   
    @FindBy(xpath = "//div[@class='rvc-header']//h2")
    private WebElement reviewMessageOne;

    @FindBy(xpath = "//div[@class='rvc-header']//h4")
    private WebElement reviewMessageTwo;

    @FindBy(xpath = "//div[@class='rvc-body-middle']//p")
    private WebElement confirmPageReview;

    private By reviewAuthorLocator  = By.xpath("//span[@itemprop='author']");
    private By reviewAuthorNickLocator  = By.xpath("//span[@itemprop='author']//following-sibling::span");
    private By reviewRatingLocator  = By.xpath("//div[@class='a11y-hidden-label']");
    private By reviewMessageLocator  = By.xpath("//div[@itemprop='description']");
  
   
    public ProfileTICPage goTo() {
        getDriver().get(url);
        return this;
    }

    public ProfileTICPage hoverAndClickFourthStar() {
        scrollToElement(waitUntilpresent(reviewSection));
        hoverToElement(ratingFirstStar);
        hoverToElement(ratingSecondStar);
        hoverToElement(ratingThirdStar);
        hoverToElement(ratingFourthStar);
        click(ratingFourthStar);
        return this;
    }

    public ProfileTICPage selectHealthInsurancePolicy() throws InterruptedException {
        addWait();
        click(policyDropdown);
        addWait();
        click(healthInsurancepolicy);
        addWait();
        return this;
    }

    public ProfileTICPage enterReview() throws InterruptedException {
        addWait();
        writeReviewField.clear();
        writeReviewField.sendKeys(writeReview);
        addWait();
        return this;
    }

    public ProfileTICPage submitReview() throws InterruptedException {
        addWait();
        click(submitReviewButton);
        waitUntilTitle(reviewConfirmTitle);
        return this;
    }

    public String getConfirmReviewMessage() {
        return getText(reviewMessageOne) + getText(reviewMessageTwo);
    }

    public String getExpectedConfirmReviewMessage() {
        return expectedConfirmReviewMessage;
    }
    
    public String getConfirmPageReview() {
        return getText(confirmPageReview);
    }

    public String getExpectedReview() {
        return writeReview;
    }

    public String getProfileName() {
        return profileName;
    }

    public String reviewAuthor(){
        return getText(reviewAuthor);
    }

    public String reviewAuthorNick(){
        return getText(reviewAuthorNick);
    }

    public String reviewRating(){
        return getText(reviewRating);
    }

    public String reviewMessage(){
        return getText(reviewMessage);
    }

    public String fourStarRating(){
        return fourStarRating;
    }
    
    public ProfileTICPage findReview(String reviewId){
        reviewXpath = "//article[@data-rvid='"+reviewId+"']";
        reviewArticle= elementVisible(reviewXpath);
        reviewAuthor = waitElementVisible(reviewArticle.findElement(reviewAuthorLocator));
        reviewAuthorNick = waitElementVisible(reviewArticle.findElement(reviewAuthorNickLocator));
        reviewRating = waitElementVisible(reviewArticle.findElement(reviewRatingLocator));
        reviewMessage = waitElementVisible(reviewArticle.findElement(reviewMessageLocator));
        return this;
    }
}

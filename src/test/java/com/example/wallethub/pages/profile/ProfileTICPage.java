package com.example.wallethub.pages.profile;
import com.example.wallethub.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.example.wallethub.config.ConfigurationManager.configuration;


public final class ProfileTICPage extends BasePage {
    
    private String writeReview =  WriteRandomReview();
    private String profileName = "Test Insurance Company";
    private String reviewConfirmTitle = "WalletHub - Review Confirmation";
    private String expectedReviewMessage = "Awesome!Your review has been posted.";
    //div[@class='rvc-body-middle']//p

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
    private WebElement review;
    
    
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

    public String getActualReviewMessage() {
        return getText(reviewMessageOne) + getText(reviewMessageTwo);
    }

    public String getExpectedReviewMessage() {
        return expectedReviewMessage;
    }

    public String getActualReview() {
        return getText(review);
    }

    public String getExpectedReview() {
        return writeReview;
    }

    public String getProfileName() {
        return profileName;
    }

    public ProfileTICPage findReview(String reviewId){
        WebElement reviewArticle = elementVisible(reviewId);
        return this;
    }
    
}

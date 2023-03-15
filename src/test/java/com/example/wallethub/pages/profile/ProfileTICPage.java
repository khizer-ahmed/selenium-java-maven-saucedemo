package com.example.wallethub.pages.profile;
import com.example.wallethub.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import static com.example.wallethub.config.ConfigurationManager.configuration;


public final class ProfileTICPage extends BasePage {
    
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

    @FindBy(xpath = " //div[text()=' Submit ']")
    private WebElement submitReviewButton;
   
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
        String review =  "Test Review : " +  WriteRandomReview();
        writeReviewField.sendKeys(review);
        addWait();
        return this;
    }

    public ProfileTICPage submitReview() throws InterruptedException {
        addWait();
        click(submitReviewButton);
        addWait();
        return this;
    }

    
}

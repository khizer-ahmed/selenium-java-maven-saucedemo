package com.example.wallethub.pages.profile;
import com.example.wallethub.pages.BasePage;

import static com.example.wallethub.config.ConfigurationManager.configuration;

public final class ProfileTICPage extends BasePage {
    
    private String writeReview =  WriteRandomReview();
    private String profileName = "Test Insurance Company";
    private String reviewConfirmTitle = "WalletHub - Review Confirmation";
    private String expectedConfirmReviewMessage = "Awesome!Your review has been posted.";
    private String fourStarRating = "4 Star Rating";
    private static String reviewAuthorText;
    private static String reviewAuthorNickText;
    private static String reviewXpath;
    private static String reviewRatingText;
    private static String reviewMessageText;
    private String url = configuration().baseUrl() + "/profile/13732055i";
    private String reviewSection = "//div[@class='rv review-action ng-enter-element']";
    private String ratingFirstStar = "//h3[text()=\"What's Your Rating?\"]//following::div[1]//child::*[@aria-label='1 star rating']";
    private String ratingSecondStar = "//h3[text()=\"What's Your Rating?\"]//following::div[1]//child::*[@aria-label='2 star rating']";
    private String ratingThirdStar = "//h3[text()=\"What's Your Rating?\"]//following::div[1]//child::*[@aria-label='3 star rating']";
    private String ratingFourthStar = "//h3[text()=\"What's Your Rating?\"]//following::div[1]//child::*[@aria-label='4 star rating']";
    private String policyDropdown = "//span[text()='Select...']//parent::div";
    private String healthInsurancepolicy = "//li[text()='Health Insurance']";
    private String writeReviewField = "//textarea[@placeholder='Write your review...']";
    private String submitReviewButton = "//div[text()=' Submit ']";
    private String reviewMessageOne = "//div[@class='rvc-header']//h2";
    private String reviewMessageTwo = "//div[@class='rvc-header']//h4";
    private String confirmPageReview = "//div[@class='rvc-body-middle']//p";
    private String reviewAuthorLocator  = "//span[@itemprop='author']";
    private String reviewAuthorNickLocator  = "//span[@itemprop='author']//following-sibling::span";
    private String reviewRatingLocator  = "//div[@class='a11y-hidden-label']";
    private String reviewMessageLocator  = "//div[@itemprop='description']";
    private String shareReviewButton = "//div[text()='Share Review ']";
    private String reviewUrlLocator = "//div[@class='mask-url']";
  
   
    public ProfileTICPage goTo() {
        getDriver().get(url);
        return this;
    }

    public ProfileTICPage hoverAndClickFourthStar() {
        scrollToElement(reviewSection);
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
        type(writeReviewField, writeReview );
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
        return reviewAuthorText;
    }

    public String reviewAuthorNick(){
        return reviewAuthorNickText;
    }

    public String reviewRating(){
        return reviewRatingText;
    }

    public String reviewMessage(){
        return reviewMessageText;
    }

    public String fourStarRating(){
        return fourStarRating;
    }
    
    public ProfileTICPage findReview(String reviewUrl){
        String[] reviewIds = reviewUrl.split("review=", 2);
        String reviewId = reviewIds[1];
        reviewXpath = "//article[@data-rvid='"+reviewId+"']";
        reviewAuthorText = getText(reviewXpath+reviewAuthorLocator);
        reviewAuthorNickText = getText(reviewXpath+reviewAuthorNickLocator);
        reviewRatingText = getHiddenElementText(reviewXpath+reviewRatingLocator);
        reviewMessageText = getText(reviewXpath+reviewMessageLocator);
        return this;
    }

    public String getReview() throws InterruptedException{
        click(shareReviewButton);
        addWait();
        String reviewUrl = getText(reviewUrlLocator);
        return reviewUrl;
        }
    }


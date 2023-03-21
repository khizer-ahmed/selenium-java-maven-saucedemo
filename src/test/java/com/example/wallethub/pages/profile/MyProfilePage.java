package com.example.wallethub.pages.profile;

import static com.example.wallethub.config.ConfigurationManager.configuration;

import com.example.wallethub.pages.BasePage;

public class MyProfilePage extends BasePage {
    
    private String reviewAuthorName = "Your Review";

    private String profileList = "//span[text()='"+configuration().name()+"']//parent::div";
    private String Myprofile = "//a[starts-with(@href,'/profile') and @class='brgm-list-it']";
    private String reviewfeedCompany = "(//div[@class='pr-rec-texts-container']//a)[2]";
    
    public MyProfilePage goTo() throws InterruptedException {
        addWait();
        hoverToElement(profileList);
        addWait();
        click(Myprofile);
        return this;
    }

    public String getReviewFeedCompany(String url) {
        String review = "//a[@href='"+url+"']";
        return getText(review);
    }

    public MyProfilePage goToReviewProfile(String url) throws InterruptedException{
        addWait();
        String review = "//a[@href='"+url+"']";
        click(review);
        addWait();
        return this;
    }

    public String getReviewId() throws InterruptedException{
        addWait();
        String reviewId = getAttributeValue(reviewfeedCompany, "href");
        String[] reviewIds = reviewId.split("review=", 2);
        reviewId = reviewIds[1];
        addWait();
        return reviewId;
    }

    public String getReviewAuthorName() {
        return reviewAuthorName;
    }

    public String getReviewNickName() {
        return "@"+configuration().username();
    }
}

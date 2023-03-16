package com.example.wallethub.pages.profile;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.example.wallethub.pages.BasePage;

public class MyProfilePage extends BasePage {
    
    private String reviewAuthorName = "Your Review";

    @FindBy(xpath = "//span[text()='Syed Khizer']//parent::div")
    private WebElement profileList;

    @FindBy(xpath = "//a[starts-with(@href,'/profile') and @class='brgm-list-it']")
    private WebElement Myprofile;

    @FindBy(xpath = "//div[@class='pr-rec-texts-container']//a")
    private WebElement reviewfeedCompany;
    
    public MyProfilePage goTo() throws InterruptedException {
        addWait();
        hoverToElement(profileList);
        addWait();
        click(Myprofile);
        return this;
    }

    public String getReviewFeedCompany() {
        return getText(reviewfeedCompany);
    }

    public MyProfilePage goToReviewProfile() throws InterruptedException{
        addWait();
        click(reviewfeedCompany);
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
        return "@khizerahmed5116SSS";
    }
}

package com.example.wallethub.pages.profile;

import static com.example.wallethub.config.ConfigurationManager.configuration;

import com.example.wallethub.pages.BasePage;

public class MyProfilePage extends BasePage {
    
    private String reviewAuthorName = "Your Review";
    private String profileList = "//span[text()='"+configuration().name()+"']//parent::div";
    private String Myprofile = "//a[starts-with(@href,'/profile') and @class='brgm-list-it']";
    
    public MyProfilePage goTo()  {
        hoverToElement(profileList);
        click(Myprofile);
        return this;
    }

    public String getReviewFeedCompany(String url) {
        String review = "//a[@href='"+url+"']";
        return getText(review);
    }

    public MyProfilePage goToReviewProfile(String url) {
        String review = "//a[@href='"+url+"']";
        click(review);
        return this;
    }

    public String getReviewAuthorName() {
        return reviewAuthorName;
    }

    public String getReviewNickName() {
        return "@"+configuration().username();
    }
}

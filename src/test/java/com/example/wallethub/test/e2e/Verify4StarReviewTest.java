package com.example.wallethub.test.e2e;

import com.example.wallethub.pages.login.LoginPage;
import com.example.wallethub.pages.profile.MyProfilePage;
import com.example.wallethub.pages.profile.ProfileTICPage;
import com.example.wallethub.test.BaseE2ETest;
import com.example.wallethub.data.BaseData;

import static com.example.wallethub.config.ConfigurationManager.configuration;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.example.wallethub.utils.DataProviderUtil.processCsv;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Verify4StarReviewTest extends BaseE2ETest {
    private static final String FILE_PATH = "testCaseData.csv";
    private LoginPage loginPage;
    private ProfileTICPage profileTICPage;
    private MyProfilePage myProfile;
    
    @DataProvider(name = "BaseData")
    public static Object[][] getLoginData(final Method testMethod) {
        String testCaseId = testMethod.getAnnotation(Test.class).testName();
        return processCsv(BaseData.class, FILE_PATH, testCaseId);
    }

    @Override
    public void initialize() {
        loginPage = createInstance(LoginPage.class);
        profileTICPage = createInstance(ProfileTICPage.class);
        myProfile = createInstance(MyProfilePage.class);
    }

    @AfterMethod
    public void captureScreenshot(ITestResult result) {
        ITestNGMethod method = result.getMethod();

        if (ITestResult.FAILURE == result.getStatus()) {
            loginPage.captureScreenshot(method.getMethodName());
        }
    }

    @Test   (testName = "TC-1",dataProvider = "BaseData")
    public void test4StarReview(final BaseData baseData) {
        loginPage
                .goTo()
                .enterUsername(configuration().email())
                .enterPassword(configuration().password())
                .clickLogin();
        assertThat(loginPage.getName()).isEqualTo(configuration().name()); 
        
        profileTICPage
                   .goTo() 
                   .hoverAndClickFourthStar()
                   .selectHealthInsurancePolicy()
                   .enterReview()
                   .submitReview();

        assertThat(profileTICPage.getConfirmReviewMessage()).isEqualTo(profileTICPage.getExpectedConfirmReviewMessage());   
        assertThat(profileTICPage.getConfirmPageReview()).isEqualTo(profileTICPage.getExpectedReview());  

        String reviewUrl = profileTICPage.getReview();
        myProfile.goTo();

        assertThat(profileTICPage.getProfileName()).isEqualTo(myProfile.getReviewFeedCompany(reviewUrl));
        
        myProfile.goToReviewProfile(reviewUrl); 
        profileTICPage.findReview(reviewUrl);
        
        assertThat(profileTICPage.reviewAuthor()).isEqualTo(myProfile.getReviewAuthorName());
        assertThat(profileTICPage.reviewAuthorNick()).isEqualTo(myProfile.getReviewNickName());
        assertThat(profileTICPage.reviewRating()).isEqualTo(profileTICPage.fourStarRating());
        assertThat(profileTICPage.reviewMessage()).isEqualTo(profileTICPage.getExpectedReview());    
}
}
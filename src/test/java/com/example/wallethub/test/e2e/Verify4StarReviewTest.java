package com.example.wallethub.test.e2e;

import com.example.wallethub.pages.login.LoginNewPage;
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
    private LoginNewPage loginPage;
    private ProfileTICPage profileTICPage;
    private MyProfilePage myProfile;
    
    @DataProvider(name = "BaseData")
    public static Object[][] getLoginData(final Method testMethod) {
        String testCaseId = testMethod.getAnnotation(Test.class).testName();
        return processCsv(BaseData.class, FILE_PATH, testCaseId);
    }

    @Override
    public void initialize() {
        loginPage = createInstance(LoginNewPage.class);
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
    public void testCorrectUserNameAndCorrectPassword(final BaseData baseData) throws InterruptedException{
        loginPage
                .goTo()
                .enterUsername(configuration().email())
                .enterPassword(configuration().password())
                .clickLogin();
        assertThat(loginPage.getNaame()).isEqualTo(configuration().name()); 
        /*   
        profileTICPage
                   .goTo() 
                   .hoverAndClickFourthStar()
                   .selectHealthInsurancePolicy()
                   .enterReview()
                   .submitReview();

        assertThat(profileTICPage.getConfirmReviewMessage()).isEqualTo(profileTICPage.getExpectedConfirmReviewMessage());   
        assertThat(profileTICPage.getConfirmPageReview()).isEqualTo(profileTICPage.getExpectedReview());  

        myProfile.goTo();

        assertThat(profileTICPage.getProfileName()).isEqualTo(myProfile.getReviewFeedCompany());
        
        String reviewId = myProfile.getReviewId();
        myProfile.goToReviewProfile(); 
        profileTICPage.findReview(reviewId);
        
        assertThat(profileTICPage.reviewAuthor()).isEqualTo(myProfile.getReviewAuthorName());
        assertThat(profileTICPage.reviewAuthorNick()).isEqualTo(myProfile.getReviewAuthorName());
        assertThat(profileTICPage.reviewRating()).isEqualTo(profileTICPage.fourStarRating());
        assertThat(profileTICPage.reviewMessage()).isEqualTo(profileTICPage.getExpectedReview());
    
    */
}
}
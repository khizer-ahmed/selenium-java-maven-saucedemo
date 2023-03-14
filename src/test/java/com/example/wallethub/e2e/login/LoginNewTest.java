package com.example.wallethub.e2e.login;

import com.example.wallethub.e2e.BaseE2ETest;
import com.example.wallethub.pages.login.LoginNewPage;
import com.example.wallethub.data.login.LoginNewData;

import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.example.wallethub.utils.DataProviderUtil.processCsv;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LoginNewTest extends BaseE2ETest {
    private static final String FILE_PATH = "login/loginNew.csv";
    private LoginNewPage loginPage;

    @DataProvider(name = "LoginNewData")
    public static Object[][] getLoginData(final Method testMethod) {
        String testCaseId = testMethod.getAnnotation(Test.class).testName();

        return processCsv(LoginNewData.class, FILE_PATH, testCaseId);
    }

    @Override
    public void initialize() {
        loginPage = createInstance(LoginNewPage.class);
    }

    @AfterMethod
    public void captureScreenshot(ITestResult result) {
        ITestNGMethod method = result.getMethod();

        if (ITestResult.FAILURE == result.getStatus()) {
            loginPage.captureScreenshot(method.getMethodName());
        }
    }

    @Test(
            testName = "TC-1",
            dataProvider = "LoginNewData",
            groups = {"smoke", "regression"})
    public void testCorrectUserNameAndCorrectPassword(final LoginNewData loginDto) {
        loginPage
                .goTo()
                .enterUsername(loginDto.getUserName())
                .enterPassword(loginDto.getPassword())
                .clickLogin();
        
        assertThat(loginPage.getName()).isEqualTo(loginDto.getGetUserName());        
   }
}
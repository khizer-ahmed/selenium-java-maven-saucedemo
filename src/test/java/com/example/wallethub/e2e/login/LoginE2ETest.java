package com.example.wallethub.e2e.login;

import com.example.wallethub.e2e.BaseE2ETest;
import com.example.wallethub.data.login.LoginData;
import com.example.wallethub.pages.login.LoginPage;
import com.example.wallethub.pages.product.ProductsPage;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.example.wallethub.utils.DataProviderUtil.processCsv;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LoginE2ETest extends BaseE2ETest {
    private static final String FILE_PATH = "login/login.csv";
    private LoginPage loginPage;

    @DataProvider(name = "loginData")
    public static Object[][] getLoginData(final Method testMethod) {
        String testCaseId = testMethod.getAnnotation(Test.class).testName();

        return processCsv(LoginData.class, FILE_PATH, testCaseId);
    }

    @Override
    public void initialize() {
        loginPage = createInstance(LoginPage.class);
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
            dataProvider = "loginData",
            groups = {"smoke", "regression"})
    public void testCorrectUserNameAndCorrectPassword(final LoginData loginDto) {
        loginPage
                .goTo()
                .enterUsername(loginDto.getUserName())
                .enterPassword(loginDto.getPassword())
                .clickLogin();

        ProductsPage productsPage = createInstance(ProductsPage.class);

        assertThat(productsPage.getTitle()).isEqualTo("Products");
    }

    @Test(
            testName = "TC-2",
            dataProvider = "loginData",
            groups = {"smoke","regression"})
    public void testIncorrectUserNameAndCorrectPassword(final LoginData loginDto) {
        loginPage
                .goTo()
                .enterUsername(loginDto.getUserName())
                .enterPassword(loginDto.getPassword())
                .clickLogin();

        assertThat(loginPage.getErrorMessage()).isEqualTo(loginDto.getErrorMessage());
    }

    @Test(
            testName = "TC-3",
            dataProvider = "loginData",
            groups = {"regression"})
    public void testCorrectUserNameAndIncorrectPassword(final LoginData loginDto) {
        loginPage
                .goTo()
                .enterUsername(loginDto.getUserName())
                .enterPassword(loginDto.getPassword())
                .clickLogin();

        assertThat(loginPage.getErrorMessage()).isEqualTo(loginDto.getErrorMessage());
    }

    @Test(
            testName = "TC-4",
            dataProvider = "loginData",
            groups = {"regression"})
    public void testIncorrectUserNameAndIncorrectPassword(final LoginData loginDto) {
        loginPage
                .goTo()
                .enterUsername(loginDto.getUserName())
                .enterPassword(loginDto.getPassword())
                .clickLogin();

        assertThat(loginPage.getErrorMessage()).isEqualTo(loginDto.getErrorMessage());
    }

    @Test(
            testName = "TC-5",
            dataProvider = "loginData",
            groups = {"regression"})
    public void testBlankUserName(final LoginData loginDto) {
        loginPage.goTo().enterPassword(loginDto.getPassword()).clickLogin();

        assertThat(loginPage.getErrorMessage()).isEqualTo(loginDto.getErrorMessage());
    }

    @Test(
            testName = "TC-6",
            dataProvider = "loginData",
            groups = {"regression"})
    public void testBlankPassword(final LoginData loginDto) {
        loginPage.goTo().enterUsername(loginDto.getUserName()).clickLogin();

        assertThat(loginPage.getErrorMessage()).isEqualTo(loginDto.getErrorMessage());
    }

    @Test(
            testName = "TC-7",
            dataProvider = "loginData",
            groups = {"regression"})
    public void testLockedOutUser(final LoginData loginDto) {
        loginPage
                .goTo()
                .enterUsername(loginDto.getUserName())
                .enterPassword(loginDto.getPassword())
                .clickLogin();

        assertThat(loginPage.getErrorMessage()).isEqualTo(loginDto.getErrorMessage());
    }
}
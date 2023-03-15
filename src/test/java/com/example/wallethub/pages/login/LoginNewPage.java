package com.example.wallethub.pages.login;

import com.example.wallethub.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.example.wallethub.config.ConfigurationManager.configuration;

public final class LoginNewPage extends BasePage {
    private String url = configuration().baseUrl() + "/join/login";
    @FindBy(id = "email")
    private WebElement txtUsername;

    @FindBy(id = "password")
    private WebElement txtPassword;

    @FindBy(xpath = "//button//*[text()='Login']/ancestor::button")
    private WebElement btnLogin;

    public LoginNewPage goTo() {
        getDriver().get(url);
        return this;
    }

    public LoginNewPage enterUsername(final String username) {
        txtUsername.clear();
        txtUsername.sendKeys(username);

        return this;
    }

    public LoginNewPage enterPassword(final String password) {
        txtPassword.clear();
        txtPassword.sendKeys(password);

        return this;
    }

    public String getName() {
        return getDriver()
                .findElement(By.xpath("//span[text()='Syed Khizer']"))
                .getText();
    }

    public void clickLogin() {
        click(btnLogin);
    }
}
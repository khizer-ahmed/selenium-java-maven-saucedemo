package com.example.wallethub.pages.login;

import com.example.wallethub.pages.BasePage;

import static com.example.wallethub.config.ConfigurationManager.configuration;

public final class LoginPage extends BasePage {
    private String url = configuration().baseUrl() + "/join/login";
    private String profileName = configuration().name();
    private String profileNameXpath = "//span[text()='"+profileName+"']";
    private String txtUsername = "//input[@id='email']";
    private String txtPassword = "//input[@id='password']";
    private String btnLogin = "//button//*[text()='Login']/ancestor::button";

    public LoginPage goTo() {
        getDriver().get(url);
        return this;
    }

    public LoginPage enterUsername(final String username) {
        type(txtUsername, username);
        return this;
    }

    public LoginPage enterPassword(final String password) {
        type(txtPassword, password);
        return this;
    }

    public String getName() {
        return getText(profileNameXpath);  
    }

    public void clickLogin() {
        click(btnLogin);
    }
}
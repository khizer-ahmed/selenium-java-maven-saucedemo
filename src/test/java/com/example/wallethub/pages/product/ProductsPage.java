package com.example.wallethub.pages.product;

import com.example.wallethub.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public final class ProductsPage extends BasePage {
    @FindBy(className = "title")
    private WebElement lblTitle;

    public String getTitle() {
        return lblTitle.getText();
    }
}
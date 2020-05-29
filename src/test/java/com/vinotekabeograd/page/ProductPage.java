package com.vinotekabeograd.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {

    private WebDriverWait wdWait;
    private Actions actions;

    public ProductPage(WebDriver driver) {
        wdWait = new WebDriverWait(driver, 10);
        actions = new Actions(driver);
        //driver.get("https://www.vinotekabeograd.com/");
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".heading-wrapper h1 span")
    private WebElement productTitle;
    @FindBy (css = ".product-price.current-price")
    private WebElement productPrice;
    @FindBy (css = ".fa-plus.quantity-up")
    private WebElement quantityUp;
    @FindBy (className = "product-quantity-helper-pre")
    private WebElement quantityValue;
    @FindBy (css = "button.shop-button-qnt")
    private WebElement addToCartButton;
    @FindBy (css = ".btn-success[title='MOJA KORPA']")
    private WebElement myCartButton;
    @FindBy (css = ".ease-slow")
    private WebElement animation;

    public void orderingProductWithQuantityIncrease() {
         quantityUp.click();
        //wdWait.until(ExpectedConditions.invisibilityOf(animation));

    }

    public void addToCartAndGoToCart()  {

        //actions.moveToElement(addToCartButton).build().perform();
        //actions.clickAndHold();
        
        wdWait.until(ExpectedConditions.invisibilityOf(quantityValue));
        addToCartButton.click();
        wdWait.until(ExpectedConditions.visibilityOf(myCartButton));
        myCartButton.click();

    }

    public void waitForQuantityToBeIncreased() {
        wdWait.until(ExpectedConditions.invisibilityOf(quantityValue));
    }

    public String getProductTitle() {
        return productTitle.getText();
    }

    public String getProductPrice() {
        return productPrice.getText();
    }

    public String getProductQuantity() {
        return quantityValue.getText();
    }


}

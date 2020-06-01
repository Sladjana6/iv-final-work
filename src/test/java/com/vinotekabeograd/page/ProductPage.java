package com.vinotekabeograd.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {

    private WebDriverWait wdWait;

    public ProductPage(WebDriver driver) {
        wdWait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".heading-wrapper h1 span")
    private WebElement productTitle;
    @FindBy(css = ".product-price.current-price")
    private WebElement productPrice;
    @FindBy(css = ".fa-plus.quantity-up")
    private WebElement quantityUp;
    @FindBy(className = "product-quantity-helper-pre")
    private WebElement quantityValue;
    @FindBy(css = "button.shop-button-qnt")
    private WebElement addToCartButton;
    @FindBy(css = ".btn-success[title='MOJA KORPA']")
    private WebElement myCartButton;

    /**
     * Increases quantity value by one
     */
    public void orderingProductWithQuantityIncrease() {
        wdWait.until(ExpectedConditions.visibilityOf(quantityUp));
        quantityUp.click();
    }

    /**
     * Ads product to cart and opens cart
     */
    public void addToCartAndGoToCart() {
        wdWait.until(ExpectedConditions.invisibilityOf(quantityValue));
        addToCartButton.click();
        wdWait.until(ExpectedConditions.visibilityOf(myCartButton));
        myCartButton.click();
    }

    /**
     * Returns product title
     *
     * @return product title
     */
    public String getProductTitle() {
        return productTitle.getText();
    }

    /**
     * Returns product price
     *
     * @return product price
     */
    public String getProductPrice() {
        return productPrice.getText();
    }

    /**
     * Returns product quantity
     *
     * @return product quantity
     */
    public String getProductQuantity() {
        return quantityValue.getText();
    }

}

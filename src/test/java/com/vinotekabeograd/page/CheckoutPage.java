package com.vinotekabeograd.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {

    public CheckoutPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "tbody .product-item-title a")
    private WebElement productTitle;
    @FindBy(css = "tbody tr:nth-of-type(1) td:nth-of-type(3)")
    private WebElement productPrice;
    @FindBy(xpath = "//option[@selected='selected']")
    private WebElement productQuantity;
    @FindBy(css = ".text-right.total-price")
    private WebElement totalPrice;

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
        return productQuantity.getText();
    }

    /**
     * Returns total price
     *
     * @return total price
     */
    public String getTotalPrice() {
        return totalPrice.getText();
    }


}

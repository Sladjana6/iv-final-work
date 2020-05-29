package com.vinotekabeograd.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {

    private WebDriverWait wdWait;

    public CheckoutPage(WebDriver driver) {
        wdWait = new WebDriverWait(driver, 10);
        //driver.get("https://www.vinotekabeograd.com/");
        PageFactory.initElements(driver, this);
    }

    @FindBy (css= "tbody .product-item-title a")
    private WebElement productTitle;
    @FindBy (css = "tbody tr:nth-of-type(1) td:nth-of-type(3)")
    private WebElement productPrice;
    @FindBy (xpath = "//option[@selected='selected']")
    private WebElement productQuantity;
    @FindBy(css = ".text-right.total-price")
    private WebElement totalPrice;

    public String getProductTitle() {
        return productTitle.getText();
    }

    public String getProductPrice() {
        return productPrice.getText();
    }

    public String getProductQuantity() {
        return productQuantity.getText();
    }

    public String getTotalPrice() {return totalPrice.getText();}




}

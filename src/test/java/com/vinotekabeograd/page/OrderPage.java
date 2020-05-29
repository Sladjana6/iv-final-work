package com.vinotekabeograd.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {

    private WebDriverWait wdWait;

    public OrderPage(WebDriver driver) {
        wdWait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);

    }


    @FindBy (css = "a[title='Rakije'] span")
    private WebElement typeOfDrink;
    @FindBy (css = ".filter-category-menu a[title='Kajsija']")
    private WebElement sortOfFruit;

}

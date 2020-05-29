package com.vinotekabeograd.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class FilterPage {

    private WebDriverWait wdWait;
    private Actions actions;

    public FilterPage(WebDriver driver) {
        wdWait = new WebDriverWait(driver, 10);
        driver.get("https://www.vinotekabeograd.com/");
        PageFactory.initElements(driver, this);
        actions = new Actions(driver);
        closeNewsletter.click();
    }

    @FindBy(css = "#onload_modal .close")
    private WebElement closeNewsletter;
    @FindBy(css = "a[title='Vina']")
    private WebElement typeOfDrinkFilter;
    @FindBy(css = ".filter-category-menu [title='Srbija']")
    private WebElement countryFilter;

    @FindBy(css = "#nb_f-sorta .headline-wrapper")
    private WebElement sortaHeadline;
    @FindBy(css = "#nb_f-sorta [for='12_chardonnay']")
    private WebElement sortCheckbox;

    @FindBy(css = ".filter-group-colections .headline-wrapper")
    private WebElement kolekcijeHeadline;
    @FindBy(css = "input[value='akcija2plus1']")
    private WebElement actionCheckbox;

    @FindBy(css = ".product-listing-items div[class='item-data col-xs-12']")
    private List<WebElement> results;

    public void searchWithFilters() {
        typeOfDrinkFilter.click();
        actions.moveToElement(countryFilter).click().build().perform();
        wdWait.until(ExpectedConditions.urlContains("vina-iz-srbije"));

        actions.moveToElement(sortaHeadline).build().perform();
        actions.moveToElement(sortCheckbox).click().build().perform();
        wdWait.until(ExpectedConditions.urlContains("chardonnay"));

        actions.moveToElement(kolekcijeHeadline).build().perform();
        actions.moveToElement(actionCheckbox).click().build().perform();
        wdWait.until(ExpectedConditions.urlContains("akcija2plus1"));
    }

    public List<WebElement> getResults() {
        return results;
    }

    public Boolean isProductActionImageDisplayed(WebElement element) {
        WebElement productActionImage = element.findElement(By.cssSelector(".caption-product-list img"));
        return productActionImage.isDisplayed();
    }

    public String getProductCountry(WebElement element) {
        WebElement productCountry = element.findElement(By.cssSelector(".category-wrapper a"));
        return productCountry.getAttribute("title");
    }

    public String getProductTitle(WebElement element) {
        WebElement productTitle = element.findElement(By.cssSelector(".title a"));
        return productTitle.getText();
    }
}

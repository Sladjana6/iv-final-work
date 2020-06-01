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
        actions = new Actions(driver);
        PageFactory.initElements(driver, this);
        closeNewsletter.click();
    }

    @FindBy(css = "#onload_modal .close")
    private WebElement closeNewsletter;
    @FindBy(css = "a[title='Vina']")
    private WebElement typeOfDrinkFilter;
    @FindBy(css = ".filter-category-menu [title='Srbija']")
    private WebElement countryFilterSerbia;

    @FindBy(css = "#nb_f-sorta .headline-wrapper")
    private WebElement sortHeadline;
    @FindBy(css = "#nb_f-sorta [for='12_chardonnay']")
    private WebElement sortCheckboxChardonnay;

    @FindBy(css = ".filter-group-colections .headline-wrapper")
    private WebElement collectionsHeadline;
    @FindBy(css = "input[value='akcija2plus1']")
    private WebElement actionCheckboxTwoPlusOne;

    @FindBy(css = ".product-listing-items div[class='item-data col-xs-12']")
    private List<WebElement> filterResults;

    /**
     * Performs filter by drink wine, country Serbia, sort Chardonnay, collection 2+1
     */
    public void searchWithFilters() {
        typeOfDrinkFilter.click();
        actions.moveToElement(countryFilterSerbia).click().build().perform();
        wdWait.until(ExpectedConditions.urlContains("vina-iz-srbije"));

        actions.moveToElement(sortHeadline).build().perform();
        actions.moveToElement(sortCheckboxChardonnay).click().build().perform();
        wdWait.until(ExpectedConditions.urlContains("chardonnay"));

        actions.moveToElement(collectionsHeadline).build().perform();
        actions.moveToElement(actionCheckboxTwoPlusOne).click().build().perform();
        wdWait.until(ExpectedConditions.urlContains("akcija2plus1"));
    }

    /**
     * Returns list of results
     *
     * @return results
     */
    public List<WebElement> getFilterResults() {
        return filterResults;
    }

    /**
     * This method searches for 2+1 image inside product
     *
     * @param element
     * @return is product image displayed
     */
    public Boolean isProductActionImageDisplayed(WebElement element) {
        WebElement productActionImage = element.findElement(By.cssSelector(".caption-product-list img"));
        return productActionImage.isDisplayed();
    }

    /**
     * This method searches for product country inside product
     *
     * @param element
     * @return product country
     */
    public String getProductCountry(WebElement element) {
        WebElement productCountry = element.findElement(By.cssSelector(".category-wrapper a"));
        return productCountry.getAttribute("title");
    }

    /**
     * This method searches for title inside product
     *
     * @param element
     * @return product title
     */
    public String getProductTitle(WebElement element) {
        WebElement productTitle = element.findElement(By.cssSelector(".title a"));
        return productTitle.getText();
    }
}

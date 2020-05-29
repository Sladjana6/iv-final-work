package com.vinotekabeograd.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchPage {

    private WebDriverWait wdWait;

    public SearchPage(WebDriver driver) {
        wdWait = new WebDriverWait(driver, 10);
        driver.get("https://www.vinotekabeograd.com/");
        PageFactory.initElements(driver, this);
        closeNewsletter.click();
    }

    @FindBy(css = "#onload_modal .close")
    private WebElement closeNewsletter;
    @FindBy(className = "autocomplete-button-simple")
    private WebElement searchBoxIcon;
    @FindBy(id = "search-text")
    private WebElement searchBoxInput;
    @FindBy(css = ".product-item .title a")
    private List<WebElement> searchResults;

    public void performSearch(String query) {
        wdWait.until(ExpectedConditions.visibilityOf(searchBoxIcon));
        searchBoxIcon.click();
        searchBoxInput.sendKeys(query);
        searchBoxInput.submit();
    }

    public List<WebElement> getSearchResults() {
        return searchResults;
    }

    public WebElement getFirstResult() {
        return searchResults.get(0);
    }
}

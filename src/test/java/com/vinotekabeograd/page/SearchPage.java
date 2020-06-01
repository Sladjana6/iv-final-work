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

    /**
     * Performs search based on query parameter
     *
     * @param query
     */
    public void performSearch(String query) {
        wdWait.until(ExpectedConditions.visibilityOf(searchBoxIcon));
        searchBoxIcon.click();
        searchBoxInput.sendKeys(query);
        searchBoxInput.submit();
    }

    /**
     * Returns list of results
     *
     * @return results
     */
    public List<WebElement> getSearchResults() {
        return searchResults;
    }

    /**
     * Returns first result
     *
     * @return first result
     */
    public WebElement getFirstResult() {
        return searchResults.get(0);
    }


}

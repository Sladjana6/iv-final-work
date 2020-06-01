package com.vinotekabeograd.test;

import com.vinotekabeograd.BaseTest;
import com.vinotekabeograd.page.SearchPage;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class SearchPageTest extends BaseTest {

    private SearchPage searchPage;

    @Before
    public void setUp() {
        driver.get("https://www.vinotekabeograd.com/");
        searchPage   = new SearchPage(driver);
    }

    @Test
    public void performSearchTest() {
        final String query = "Zarić";
        searchPage.performSearch(query);
        for (WebElement result : searchPage.getSearchResults()) {
            actions
                    .moveToElement(result)
                    .build().perform();
            //assert that result contains query param
            softAssertions.assertThat(result.getText())
                    .withFailMessage("Search element has no query parameter!")
                    .contains(query.toUpperCase());
        }
    }
}

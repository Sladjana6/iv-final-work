package com.vinotekabeograd.test;

import com.vinotekabeograd.BaseTest;
import com.vinotekabeograd.page.FilterPage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FilterPageTest extends BaseTest {

    private FilterPage filterPage;

    @Before
    public void setUp() {
        driver.get("https://www.vinotekabeograd.com/");
        filterPage = new FilterPage(driver);
    }

    @Test
    public void performFilteringTest() {
        filterPage.searchWithFilters();
        List<WebElement> filterResults = filterPage.getFilterResults();
        //check if result list is empty and stop test
        Assert.assertFalse("Filter result is empty!", filterResults.isEmpty());
        for (WebElement result : filterResults) {
            actions
                    .moveToElement(result)
                    .build().perform();
            //assert for action image 2+1
            softAssertions.assertThat(filterPage.isProductActionImageDisplayed(result))
                    .withFailMessage("Product action image is not displayed!")
                    .isTrue();
            //assert for country Serbia
            softAssertions.assertThat(filterPage.getProductCountry(result))
                    .withFailMessage("Product country is not ok!")
                    .isEqualTo("Srbija");
            //assert for Chardonnay and 2+1
            softAssertions.assertThat(filterPage.getProductTitle(result))
                    .withFailMessage("Product title is not ok!")
                    .contains("AKCIJA 2+1")
                    .contains("CHARDONNAY");
        }
        ;
    }
}

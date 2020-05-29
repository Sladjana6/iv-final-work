package com.vinotekabeograd.test;

import com.vinotekabeograd.BaseTest;
import com.vinotekabeograd.page.FilterPage;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FilterPageTest extends BaseTest {

    private FilterPage filterPage;

    @Before
    public void setUp() {
        filterPage = new FilterPage(driver);
    }

    @Test
    public void performFilteringTest() {
        filterPage.searchWithFilters();
        List<WebElement> results = filterPage.getResults();
        results.forEach(r -> {
            actions.moveToElement(r).build().perform();
            //assert slike za akciju
            softAssertions.assertThat(filterPage.isProductActionImageDisplayed(r))
                    .withFailMessage("Product action image is not displayed!")
                    .isTrue();
            //assert za Srbiju
            softAssertions.assertThat(filterPage.getProductCountry(r))
                    .withFailMessage("Product country is not okay")
                    .isEqualTo("Srbija");
            //assert za Chardonnay i 2+1 naziv
            softAssertions.assertThat(filterPage.getProductTitle(r))
                    .withFailMessage("Product title is not okay")
                    .contains("AKCIJA 2+1")
                    .contains("CHARDONNAY");
        });
    }
}

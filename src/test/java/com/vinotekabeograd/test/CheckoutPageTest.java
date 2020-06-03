package com.vinotekabeograd.test;

import com.vinotekabeograd.BaseTest;
import com.vinotekabeograd.page.CheckoutPage;
import com.vinotekabeograd.page.ProductPage;
import com.vinotekabeograd.page.SearchPage;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class CheckoutPageTest extends BaseTest {

    private SearchPage searchPage;
    private ProductPage productPage;
    private CheckoutPage checkoutPage;

    @Before
    public void setUp() {
        driver.get("https://www.vinotekabeograd.com/");
        searchPage = new SearchPage(driver);
        productPage = new ProductPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    @Test
    public void orderTest() {
        final String query = "kajsija";
        searchPage.performSearch(query);
        List<WebElement> searchResults = searchPage.getSearchResults();

        Random random = new Random();
        WebElement result = searchResults.get(random.nextInt(searchPage.getSearchResults().size()));
        actions
                .moveToElement(result)
                .build().perform();
        result.click();

        productPage.orderingProductWithQuantityIncrease();
        String titleOnProductPage = productPage.getProductTitle();
        String priceOnProductPage = productPage.getProductPrice();
        String quantityOnProductPage = productPage.getProductQuantity();
        productPage.addToCartAndGoToCart();

        String titleOnCheckoutPage = checkoutPage.getProductTitle();
        String priceOnCheckoutPage = checkoutPage.getProductPrice();
        String quantityOnCheckoutPage = checkoutPage.getProductQuantity();
        String totalPriceOnCheckoutPage = checkoutPage.getTotalPrice();

        String expectedPrice = priceParser(priceOnCheckoutPage, Integer.parseInt(quantityOnCheckoutPage));
        String actualPrice = priceParser(totalPriceOnCheckoutPage);

        //assert for product title
        softAssertions.assertThat(titleOnCheckoutPage)
                .withFailMessage("Title is not ok!")
                .isEqualTo(titleOnProductPage);
        //assert for product price
        softAssertions.assertThat(priceOnCheckoutPage)
                .withFailMessage("Price is not ok!")
                .isEqualTo(priceOnProductPage);
        //assert for product quantity
        softAssertions.assertThat(quantityOnCheckoutPage)
                .withFailMessage("Quantity is not ok!")
                .isEqualTo(quantityOnProductPage);
        //assert for total price
        softAssertions.assertThat(actualPrice)
                .withFailMessage("Total price is not ok!")
                .isEqualTo(expectedPrice);
    }

    /**
     * Parsing value from price 1.790,00 RSD to 1790.00
     *
     * @param price
     * @param quantity
     * @return formatted price for assertions
     */
    public static String priceParser(String price, Integer... quantity) {
        int multiplier = quantity.length > 0 ? quantity[0] : 1;
        String replaced = price.replace(".", "").replace(",", ".");
        String sub = StringUtils.substringBefore(replaced, " RSD");
        float f = Float.parseFloat(sub);
        return String.format("%.2f", f * multiplier);
    }
}

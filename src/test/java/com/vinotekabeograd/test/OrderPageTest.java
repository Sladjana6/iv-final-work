package com.vinotekabeograd.test;

import com.vinotekabeograd.BaseTest;
import com.vinotekabeograd.page.CheckoutPage;
import com.vinotekabeograd.page.OrderPage;
import com.vinotekabeograd.page.ProductPage;
import com.vinotekabeograd.page.SearchPage;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class OrderPageTest extends BaseTest {

    private SearchPage searchPage;
    private OrderPage orderPage;
    private ProductPage productPage;
    private CheckoutPage checkoutPage;

    @Before
    public void setUp() {
        searchPage = new SearchPage(driver);
        orderPage = new OrderPage(driver);
        productPage = new ProductPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    @Test
    public void orderTest() {
        final String query = "Momirović";
        searchPage.performSearch(query);
        WebElement result = searchPage.getFirstResult();
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

        String expectedPrice = priceParser(priceOnCheckoutPage,Integer.parseInt(quantityOnCheckoutPage));
        String actualPrice =  priceParser(totalPriceOnCheckoutPage);


        softAssertions.assertThat(titleOnCheckoutPage)
                .withFailMessage("Title is not okay")
                .isEqualTo(titleOnProductPage);
        softAssertions.assertThat(priceOnCheckoutPage)
                .withFailMessage("Price is not okay")
                .isEqualTo(priceOnProductPage);
        softAssertions.assertThat(quantityOnCheckoutPage)
               .withFailMessage("Quantity is not okay")
               .isEqualTo(quantityOnProductPage);
        softAssertions.assertThat(actualPrice)
                .withFailMessage("Total price is not ok")
                .isEqualTo(expectedPrice);

    }

    public static String priceParser(String price, Integer ... quantity) {
        int multiplier = quantity.length>0?quantity[0]:1;

        String replaced = price.replace(".","").replace(",",".");
        String sub = StringUtils.substringBefore(replaced," RSD");
        float f = Float.parseFloat(sub);
        return String.format("%.2f",f*multiplier);
    }




}

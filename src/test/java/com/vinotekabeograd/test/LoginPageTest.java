package com.vinotekabeograd.test;

import com.vinotekabeograd.BaseTest;
import com.vinotekabeograd.page.LoginPage;
import org.junit.Before;
import org.junit.Test;

public class LoginPageTest extends BaseTest {

    private LoginPage loginPage;

    @Before
    public void setUp() {
        driver.get("https://www.vinotekabeograd.com/");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void positiveLoginTest() {
        loginPage.performLogin("sladjanafilipovic6@gmail.com", "sladjana6");
        softAssertions.assertThat(loginPage.getUser())
                .withFailMessage("User is not correct!")
                .isEqualTo("SLADJANA FILIPOVIC");
        softAssertions.assertThat(loginPage.isLogoutDisplayed())
                .withFailMessage("Logout button is not displayed!")
                .isTrue();
    }

    @Test
    public void negativeLoginTest() {
        loginPage.performLogin("sladjanafilipovic6@gmail.com", "sladjana");
        softAssertions.assertThat(loginPage.getFailMessage())
                .withFailMessage("Something is wrong with login!")
                .isEqualTo("Pogrešna email adresa ili lozinka. Molimo Vas pokušajte ponovo!");
    }
}


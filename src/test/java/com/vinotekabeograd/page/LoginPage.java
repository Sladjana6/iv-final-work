package com.vinotekabeograd.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriverWait wdWait;

    public LoginPage(WebDriver driver) {
        wdWait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
        closeNewsletter.click();
    }

    @FindBy(css = "#onload_modal .close")
    private WebElement closeNewsletter;
    @FindBy(className = "login-btn")
    private WebElement loginLabel;
    @FindBy(id = "login_email")
    private WebElement emailInput;
    @FindBy(id = "login_password")
    private WebElement passwordInput;
    @FindBy(css = ".btn-login.confirm-loader")
    private WebElement loginButton;

    @FindBy(css = ".item-username span")
    private WebElement usernameSpan;
    @FindBy(css = ".item-logout a")
    private WebElement logoutButton;
    @FindBy(css = ".login_form .alert-danger")
    private WebElement badLoginMessage;


    /**
     * Opens login form, populates it with data and submits it
     *
     * @param userEmail
     * @param userPassword
     */
    public void performLogin(String userEmail, String userPassword) {
        loginLabel.click();
        wdWait.until(ExpectedConditions.visibilityOf(emailInput));
        emailInput.clear();
        emailInput.sendKeys(userEmail);
        passwordInput.clear();
        passwordInput.sendKeys(userPassword);
        loginButton.click();
    }

    /**
     * Returns user's first name and last name from header
     *
     * @return user's first name, last name
     */
    public String getUser() {
        wdWait.until(ExpectedConditions.visibilityOf(usernameSpan));
        return usernameSpan.getText();
    }

    /**
     * Checks if logout button is displayed
     *
     * @return is logout button displayed
     */
    public Boolean isLogoutDisplayed() {
        return logoutButton.isDisplayed();
    }

    /**
     * Returns fail message for invalid login
     *
     * @return fail message
     */
    public String getFailMessage() {
        wdWait.until(ExpectedConditions.visibilityOf(badLoginMessage));
        return badLoginMessage.getText();
    }
}

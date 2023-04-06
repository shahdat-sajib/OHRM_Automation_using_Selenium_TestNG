package org.shahdat.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.shahdat.utilities.ExplicitWaitUtils.waitForElementPresence;
import static org.shahdat.log.Logger.logstep;

public class LoginPage {
    private final WebDriver driver;
    private final By userNameLocator = By.xpath("//input[@placeholder='Username']");
    private final By passwordLocator = By.xpath("//input[@placeholder='Password']");
    private final By loginBtnLocator = By.xpath("//button[normalize-space()='Login']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyErrorMessagesForInvalidLogin(String userName, String password) throws InterruptedException {
        driver.navigate().refresh();
        waitForElementPresence(driver, userNameLocator, 5);
        driver.findElement(userNameLocator).clear();
        driver.findElement(userNameLocator).sendKeys(userName);
        driver.findElement(passwordLocator).clear();
        driver.findElement(passwordLocator).sendKeys(password);
        driver.findElement(loginBtnLocator).submit();
        logstep("Username, Password entered and login button pressed");
    }
}

package org.shahdat.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

import static org.shahdat.utilities.ExplicitWaitUtils.waitForElementPresence;
import static org.shahdat.log.Logger.logstep;

public class LoginPage {
    private final WebDriver driver;
    private final By userNameLocator = By.xpath("//input[@placeholder='Username']");
    private final By passwordLocator = By.xpath("//input[@placeholder='Password']");
    private final By loginBtnLocator = By.xpath("//button[normalize-space()='Login']");
    private final By requiredLocator = By.xpath("//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']");
    private final By invalidMsgLocator = By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyErrorMessagesForInvalidLogin(String userName, String password, String expectedRequiredMsg, String invalidMsg) throws InterruptedException {
        driver.navigate().refresh();
        waitForElementPresence(driver, userNameLocator, 5);
        driver.findElement(userNameLocator).clear();
        driver.findElement(userNameLocator).sendKeys(userName);
        driver.findElement(passwordLocator).clear();
        driver.findElement(passwordLocator).sendKeys(password);
        driver.findElement(loginBtnLocator).submit();
        logstep("Username, Password entered and login button pressed");

        if(Objects.equals(userName, "") | Objects.equals(password, "")){
            String actual = driver.findElement(requiredLocator).getText();
            if(expectedRequiredMsg.equalsIgnoreCase(actual)){
                logstep("Username or, password validation successful");
            }
        }else {
            waitForElementPresence(driver, invalidMsgLocator, 5);
            String actual = driver.findElement(invalidMsgLocator).getText();
            if(invalidMsg.equalsIgnoreCase(actual)){
                logstep("Username or, password invalid validation successful");
            }
        }
    }
}

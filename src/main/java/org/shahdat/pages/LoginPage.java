package org.shahdat.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

import static org.shahdat.utilities.ExplicitWaitUtils.waitForElementPresence;
import static org.shahdat.log.Logger.logstep;
import static org.shahdat.utilities.AssertionUtils.verifyTextAssertion;
import static org.shahdat.utilities.FormFillupUtils.fillupForm;
import static org.shahdat.utilities.VisibilityUtils.visibilityCheck;

public class LoginPage extends BasePage {
    private final WebDriver driver;
    private final By userNameLocator = By.xpath("//input[@placeholder='Username']");
    private final By passwordLocator = By.xpath("//input[@placeholder='Password']");
    private final By loginBtnLocator = By.xpath("//button[normalize-space()='Login']");
    private final By requiredLocator = By.xpath("//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']");
    private final By invalidMsgLocator = By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']");
    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void verifyErrorMessagesForInvalidLogin(String userName, String password, String expectedRequiredMsg, String invalidMsg){
        driver.navigate().refresh();
        fillupForm(driver, userNameLocator, userName);
        fillupForm(driver, passwordLocator, password);
        driver.findElement(loginBtnLocator).submit();
        logstep("Login button pressed");

        if(Objects.equals(userName, "") | Objects.equals(password, "")){
            String actual = driver.findElement(requiredLocator).getText();
            if(expectedRequiredMsg.equalsIgnoreCase(actual)){
                logstep("Username or, password REQUIRED validation message verified");
            }
        }else {
            waitForElementPresence(driver, invalidMsgLocator, 5);
            String actual = driver.findElement(invalidMsgLocator).getText();
            if(invalidMsg.equalsIgnoreCase(actual)){
                logstep("Username or, password INVALID validation message verified");
            }
        }
    }
    public void verifyUserLoginWithValidCredential(String userName, String password){
        driver.navigate().refresh();
        fillupForm(driver, userNameLocator, userName);
        fillupForm(driver, passwordLocator, password);
        driver.findElement(loginBtnLocator).submit();
        logstep("Login button pressed");
    }
    public void verifyDashboardPageAppearance(){
        verifyBasePageElementsVisibility("Dashboard");
    }

}

package org.shahdat.utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static org.shahdat.log.Logger.logstep;
import static org.shahdat.utilities.ExplicitWaitUtils.waitForElementPresence;

public class AssertionUtils {
    public static void verifyTextAssertion(WebDriver driver, By elementLocator, String expectedText, String logMessage) {
        waitForElementPresence(driver, elementLocator, 5);
        String actualText = driver.findElement(elementLocator).getText();
        try {
            Assert.assertEquals(expectedText, actualText);
            logstep(logMessage + "'" + actualText + "'" + " - Assertion Verified");
        } catch (AssertionError e) {
            throw new AssertionError(logMessage + " - Assertion Verification Failed. \nExpected: " + expectedText + ", Actual: " + actualText);
        }
    }

}

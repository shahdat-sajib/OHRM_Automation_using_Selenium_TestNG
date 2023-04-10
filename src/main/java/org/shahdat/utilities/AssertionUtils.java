package org.shahdat.utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.shahdat.log.Logger.logstep;
import static org.shahdat.utilities.ExplicitWaitUtils.waitForElementPresence;

public class AssertionUtils {
    public static void verifyTextAssertion(WebDriver driver, By elementLocator, String expectedText, String logMessage) {
        waitForElementPresence(driver, elementLocator, 5);
        String actualText = driver.findElement(elementLocator).getText();
        if (expectedText.equalsIgnoreCase(actualText)) {
            logstep(logMessage + " - Assertion Verified");
        } else {
            logstep(logMessage + " - Assertion Verification Failed. \nExpected: " + expectedText + ", Actual: " + actualText);
        }
    }

}

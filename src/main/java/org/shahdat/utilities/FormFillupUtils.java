package org.shahdat.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.shahdat.log.Logger.logstep;
import static org.shahdat.utilities.ExplicitWaitUtils.waitForElementPresence;

public class FormFillupUtils {
    public static void fillupForm(WebDriver driver, By elementLocator, String value) {
        waitForElementPresence(driver, elementLocator, 5);
        WebElement element = driver.findElement(elementLocator);
        element.clear();
        element.sendKeys(value);
        String placeholderText = element.getAttribute("placeholder");
        logstep( placeholderText + " is entered");
    }
}

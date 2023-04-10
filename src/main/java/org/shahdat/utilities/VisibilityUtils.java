package org.shahdat.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.shahdat.log.Logger.logstep;
import static org.shahdat.utilities.ExplicitWaitUtils.waitForElementPresence;

public class VisibilityUtils {
    public static void visibilityCheck(WebDriver driver, By elementLocator, String eleName){
        waitForElementPresence(driver, elementLocator, 5);
        boolean display = driver.findElement(elementLocator).isDisplayed();
        if(display){
            logstep(eleName + " is visible");
        }else{
            logstep(eleName + " is not visible!");
        }
    }
}

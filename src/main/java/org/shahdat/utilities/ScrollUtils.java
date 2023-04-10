package org.shahdat.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.shahdat.log.Logger.logstep;

public class ScrollUtils {
    public static void scrollToElement(WebDriver driver, By element) {
        WebElement element2 = driver.findElement(element);
        String placeholderText = element2.getText();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element2);
//        logstep("Scrolling to " + placeholderText + "element");
    }

}

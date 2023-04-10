package org.shahdat.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.shahdat.utilities.VisibilityUtils.visibilityCheck;

public class BasePage {
    private final WebDriver driver;
    private final By basePageTitleLocator = By.xpath("//div[@class='oxd-topbar-header-title']");
    private final By basePageUserDropdownLocator = By.xpath("//span[@class='oxd-userdropdown-tab']");
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }
    public void verifyBasePageElementsVisibility(String pageName){
        visibilityCheck(driver, basePageTitleLocator, pageName + "Page title");
        visibilityCheck(driver, basePageUserDropdownLocator, "In " + pageName + " User Dropdown Menu");
    }
}

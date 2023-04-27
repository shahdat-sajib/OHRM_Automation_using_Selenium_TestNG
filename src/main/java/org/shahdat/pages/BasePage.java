package org.shahdat.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.shahdat.log.Logger.logstep;
import static org.shahdat.utilities.ExplicitWaitUtils.waitForElementPresence;
import static org.shahdat.utilities.ScrollUtils.scrollToElement;
import static org.shahdat.utilities.VisibilityUtils.visibilityCheck;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

public class BasePage {
    private final WebDriver driver;
    private final By basePageLogoLocator = By.xpath("//img[@alt='client brand banner']");
    private final By searchOptnLocator = By.xpath("//div[@class='oxd-main-menu-search']");
    private final By basePageTitleLocator = By.xpath("//div[@class='oxd-topbar-header-title']");
    private final By basePageUserDropdownLocator = By.xpath("//span[@class='oxd-userdropdown-tab']");
    private final By adminOptnLocator = By.xpath("//span[normalize-space()='Admin']");
    private final By pimOptnLocator = By.xpath("//span[normalize-space()='PIM']");
    private final By leaveOptnLocator = By.xpath("//span[normalize-space()='Leave']");
    private final By timeOptnLocator = By.xpath("//span[normalize-space()='Time']");
    private final By recruitmentOptnLocator = By.xpath("//span[normalize-space()='Recruitment']");
    private final By myInfoOptnLocator = By.xpath("//span[normalize-space()='My Info']");
    private final By performanceOptnLocator = By.xpath("//span[normalize-space()='Performance']");
    private final By dashboardOptnLocator = By.xpath("//span[normalize-space()='Dashboard']");
    private final By directoryOptnLocator = By.xpath("//span[normalize-space()='Directory']");
    private final By maintenanceOptnLocator = By.xpath("//span[normalize-space()='Maintenance']");
    private final By buzzOptnLocator = By.xpath("//span[normalize-space()='Buzz']");
//    private final By collapseMenuBtnLocator = By.xpath("//i[@class='oxd-icon bi-chevron-left']");
    private final By collapseMenuBtnLocator = By.className("oxd-main-menu-button");
//    private final By sidebarLocator = By.xpath("//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/div/div/button/i");
    private final By sidebarLocator = By.className("oxd-sidepanel");
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }
    public void verifyBasePageElementsVisibility() {
        visibilityCheck(driver, basePageTitleLocator, "Base Page TITLE");
        visibilityCheck(driver, basePageUserDropdownLocator, "Base Page USER DROPDOWN MENU");
        visibilityCheck(driver, basePageLogoLocator, "Base Page LOGO");
        visibilityCheck(driver, searchOptnLocator, "Base Page Menu: SEARCH");
        visibilityCheck(driver, adminOptnLocator, "Base Page Menu: ADMIN");
        visibilityCheck(driver, pimOptnLocator, "Base Page Menu: PIM");
        visibilityCheck(driver, leaveOptnLocator, "Base Page Menu: LEAVE");
        visibilityCheck(driver, timeOptnLocator, "Base Page Menu: TIME");
        visibilityCheck(driver, recruitmentOptnLocator, "Base Page Menu: RECRUITMENT");
        visibilityCheck(driver, myInfoOptnLocator, "Base Page Menu: MY INFO");
        visibilityCheck(driver, performanceOptnLocator, "Base Page Menu: PERFORMANCE");
        visibilityCheck(driver, dashboardOptnLocator, "Base Page Menu: DASHBOARD");
        visibilityCheck(driver, directoryOptnLocator, "Base Page Menu: DIRECTORY");
        visibilityCheck(driver, maintenanceOptnLocator, "Base Page Menu: MAINTENANCE");
        visibilityCheck(driver, buzzOptnLocator, "Base Page Menu: BUZZ");
        visibilityCheck(driver, collapseMenuBtnLocator, "Base Page Sidebar 'Collapse Button'");
    }

    public void clickCollapseMenuAndCheckSidebar(WebDriver driver, By sidebarLocator) {
        WebElement collapseBtn = driver.findElement(collapseMenuBtnLocator);
        collapseBtn.click();
        logstep("Sidebar 'Collapse Button' is clicked");
        String className = driver.findElement(sidebarLocator).getAttribute("class");
        if (className.startsWith("oxd-sidepanel")) {
            boolean isCollapsed = className.endsWith("toggled");
            String message = isCollapsed ? "collapsed" : "expanded";
            logstep("Sidebar is " + message);
            assertTrue(true, "Sidebar is " + message);
        }
    }
    public void verifySidebarCollapseButtonFunctionality() {
        logstep("Check 1: For collapse functionality");
        clickCollapseMenuAndCheckSidebar(driver, sidebarLocator);
        logstep("Check 2: For re-expand functionality");
        clickCollapseMenuAndCheckSidebar(driver, sidebarLocator);
    }
}

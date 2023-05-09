package org.shahdat.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.Map;
import static org.shahdat.utilities.VisibilityUtils.visibilityCheck;
import static org.shahdat.utilities.AssertionUtils.verifyTextAssertion;

public class DashboardPage {
    private final WebDriver driver;
    private final String DASHBOARD_ELEMENT_LOCATOR = "(//div[@class='oxd-grid-item oxd-grid-item--gutters orangehrm-dashboard-widget'])[%d]";
    private final String DASHBOARD_ELEMENT_HEADER_LOCATOR = DASHBOARD_ELEMENT_LOCATOR + "//child::div[@class='orangehrm-dashboard-widget-name']";
    private final By DASHBOARD_GRID_ITEMS = By.xpath("//div[@class='oxd-grid-3 orangehrm-dashboard-grid']/div");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyDashboardPageElementsVisibility(Map<String, String> testData) {
        int gridSize = driver.findElements(DASHBOARD_GRID_ITEMS).size();
        for (int i = 1; i <= gridSize; i++) {
            By dashboardElementLocator = By.xpath(String.format(DASHBOARD_ELEMENT_LOCATOR, i));
            By dashboardElementHeaderLocator = By.xpath(String.format(DASHBOARD_ELEMENT_HEADER_LOCATOR, i));
            visibilityCheck(driver, dashboardElementLocator, "Dashboard Page Element No. " + i);
            visibilityCheck(driver, dashboardElementHeaderLocator, "Dashboard Page Element " + i + " title");
            String DataKey = "Element " + i + " Header";
            verifyTextAssertion(driver, dashboardElementHeaderLocator, testData.get(DataKey), "Dashboard Page Element " + i + " title ");
        }
    }
}

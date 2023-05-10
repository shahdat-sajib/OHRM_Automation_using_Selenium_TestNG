package org.shahdat.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.shahdat.utilities.VisibilityUtils.visibilityCheck;
import static org.shahdat.utilities.AssertionUtils.verifyTextAssertion;

public class DashboardPage {
    private final WebDriver driver;
    private final String DASHBOARD_ELEMENT_LOCATOR = "(//div[@class='oxd-grid-item oxd-grid-item--gutters orangehrm-dashboard-widget'])[%d]";
    private final String DASHBOARD_ELEMENT_HEADER_LOCATOR = DASHBOARD_ELEMENT_LOCATOR + "//child::div[@class='orangehrm-dashboard-widget-name']";
    private final By DASHBOARD_GRID_ITEMS = By.xpath("//div[@class='oxd-grid-3 orangehrm-dashboard-grid']/div");
    private final By CARD1_EMPLOYEE_IMG = By.xpath("//img[@class='employee-image']");
    private final By CARD1_HEADER = By.xpath("//p[@class='oxd-text oxd-text--p orangehrm-attendance-card-state']");
    private final By CARD1_PUNCH_TIME = By.xpath("//p[@class='oxd-text oxd-text--p orangehrm-attendance-card-details']");
    private final By CARD1_FULL_TIME = By.xpath("//span[@class='oxd-text oxd-text--span orangehrm-attendance-card-fulltime']");

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

    public static String calculateFullTime(String timeStr) {
        // Parse the time string into hours and minutes
        String[] parts = timeStr.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);

        // Calculate the total minutes from 9.00 AM
        int totalMinutes = (hours - 9) * 60 + minutes;

        // Calculate the hours and minutes from the total minutes
        int hoursFrom9 = totalMinutes / 60;
        int minutesFrom9 = totalMinutes % 60;

        // Return the formatted result
        return String.format("%dh %02dm", hoursFrom9, minutesFrom9);
    }


    public void verifyItemsVisibilityForTimeAtWord(Map<String, String> testData){
        visibilityCheck(driver, CARD1_EMPLOYEE_IMG, "Employee image in 'Time at Work'");
        visibilityCheck(driver, CARD1_HEADER, "Card1 title: '" + driver.findElement(CARD1_HEADER).getText() + "'");
        String punch_time = ("Punched In: " + testData.get("Punch Time"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d'th' 'at' HH:mm a '(GMT 'Z')'");
        LocalDateTime dateTime = LocalDateTime.parse(testData.get("Punch Time"), formatter);
        String timeStr = dateTime.format(DateTimeFormatter.ofPattern("HH:mm"));

        verifyTextAssertion(driver, CARD1_PUNCH_TIME, punch_time, "Punch time ");
        System.out.println(driver.findElement(CARD1_FULL_TIME).getText());

        String fullTime = calculateFullTime(timeStr);
        System.out.println(fullTime);
    }
}

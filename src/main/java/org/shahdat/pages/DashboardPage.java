package org.shahdat.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.shahdat.log.Logger.logstep;
import static org.shahdat.utilities.VisibilityUtils.visibilityCheck;
import static org.shahdat.utilities.AssertionUtils.verifyTextAssertion;
import static org.testng.Assert.*;

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
        Pattern pattern = Pattern.compile("\\d{2}:\\d{2} [AP]M");
        Matcher matcher = pattern.matcher(timeStr);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Invalid time string");
        }
        String time = matcher.group();
        LocalTime endTime = LocalTime.parse(time, java.time.format.DateTimeFormatter.ofPattern("hh:mm a"));
        LocalTime startTime = LocalTime.of(9, 0, 0);
        long diffMinutes = ChronoUnit.MINUTES.between(startTime, endTime);
        int hours = (int) diffMinutes / 60;
        int minutes = (int) diffMinutes % 60;
        return String.format("%dh %02dm", hours, minutes);
    }




    public void verifyItemsVisibilityForTimeAtWord() {
        visibilityCheck(driver, CARD1_EMPLOYEE_IMG, "Employee image in 'Time at Work'");
        visibilityCheck(driver, CARD1_HEADER, "Card1 title: '" + driver.findElement(CARD1_HEADER).getText() + "'");
    }

    public void verifyPunchOutAssertion(Map<String, String> testData){
        String punch_time = ("Punched In: " + testData.get("Punch Time"));
        verifyTextAssertion(driver, CARD1_PUNCH_TIME, punch_time, "Punch time");
    }

    public void verifyFullWokTime(Map<String, String> testData) {
        String CalculatedWorkTime = calculateFullTime(testData.get("Punch Time"));
        String WorkTimeInUI = driver.findElement(CARD1_FULL_TIME).getText();
        if (WorkTimeInUI.contains(CalculatedWorkTime)) {
            logstep("Full office time " + WorkTimeInUI + "is asserted");
            assertTrue(true, "Full office time " + WorkTimeInUI + " is asserted");
        } else {
            fail("Assertion failed. Expected: " + CalculatedWorkTime + "Today is not contains in Expected: " + WorkTimeInUI);
        }
    }
}

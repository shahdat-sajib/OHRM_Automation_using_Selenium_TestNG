package EndToEndTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.shahdat.pages.LoginPage;
import org.shahdat.reporter.ExtentManager;
import org.shahdat.utilities.ExcelUtils;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import static org.shahdat.log.Logger.logstep;

public class OrangeHRM_Tests {
    static WebDriver driver;
    static LoginPage objLoginPage;

    public static void setup(String browserName, String URL){
        WebDriverManager.chromedriver().arch64().setup();
        if (browserName.equalsIgnoreCase("chrome") || browserName.equalsIgnoreCase("firefox") || browserName.equalsIgnoreCase("edge")){
            if (browserName.equalsIgnoreCase("chrome")){
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(options);
            } else if (browserName.equalsIgnoreCase("firefox")) {
                FirefoxOptions options = new FirefoxOptions();
                driver = new FirefoxDriver(options);
            } else if (browserName.equalsIgnoreCase("edge")) {
                EdgeOptions options = new EdgeOptions();
                driver = new EdgeDriver(options);
            }
            driver.manage().window().maximize();
            driver.get(URL);
        }else {
            logstep("The browser is not in scope");
        }
    }
    @BeforeTest(alwaysRun = true)
    @Parameters({"browserName", "URL"})
    public void openBrowser(String browserName, String URL){
        ExtentManager.startReport();                  // for generating the report
        setup(browserName, URL);
    }

    @DataProvider(name = "invalidLogin")
    public Iterator<Object[]> InvalidLoginTestsDP() throws IOException {
        return ExcelUtils.getExcelData("inValidLoginData");
    }

    @Test(priority = 1, dataProvider = "invalidLogin")
    public void verifyInvalidLogin(Map<String, String> testData){
        ExtentManager.logTest("Verify Invalid Login");
        objLoginPage = new LoginPage(driver);
        objLoginPage.verifyErrorMessagesForInvalidLogin(testData.get("userName"), testData.get("password"));
    }





    @AfterMethod(alwaysRun = true)
    public void endMethod(ITestResult result) throws Exception {
        ExtentManager.getResult(result, driver);      // for print each test in the report
    }

    @AfterTest(alwaysRun = true)
    public void endSession(){
        ExtentManager.stopReport();                    // to stop the report
        driver.quit();
    }
}

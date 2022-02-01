package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestSetup extends ConfigReader {

    public WebDriver driver = null;
    public ExtentTest test, suiteTest;
    public static ExtentSparkReporter htmlReporter;
    public static ExtentReports extent;
    protected static final Logger log = LoggerFactory.getLogger("Logger");

    @BeforeMethod
    public void initialize() {
        if (browser.equalsIgnoreCase("Chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:\\AutomationDrivers\\chromedriver\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.firefox.driver", "");
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        driver.get(baseURL);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        test = suiteTest.createNode(testNodes);
    }

    @BeforeSuite
    public void startReport() {
        htmlReporter = new ExtentSparkReporter("./reports/result.html");
        htmlReporter.config().setDocumentTitle("Page Object Model Automation");
        htmlReporter.config().setTheme(Theme.DARK);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @BeforeClass
    public void startTestModule() {
        suiteTest = extent.createTest(testCaseName, testDescription);
        suiteTest.assignAuthor(author);
        suiteTest.assignCategory(category);
    }

    public long takeScreenShot() {
        long number = (long) Math.floor(Math.random() * 900000000L) + 10000000L;
        try {
            TakesScreenshot scrShot = ((TakesScreenshot) driver);
            File srcFiler = scrShot.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(srcFiler, new File("./reports/images/" + number + ".png"));
        } catch (WebDriverException e) {
            e.printStackTrace();
            System.err.println("The browser has been closed.");
        } catch (IOException e) {
            System.err.println("The snapshot could not be taken");
        }
        return number;
    }

    public void reportStep(String desc, String status, boolean bSnap) {
        Media img = null;
        if (bSnap) {

            long snapNumber = 100000L;
            snapNumber = takeScreenShot();
            try {
                img = MediaEntityBuilder.createScreenCaptureFromPath("./../reports/images/" + snapNumber + ".png")
                        .build();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (status.equalsIgnoreCase("TRUE")) {
            test.pass(desc, img);
        } else if (status.equalsIgnoreCase("FALSE")) {
            test.fail(desc, img);
            throw new RuntimeException();
        } else if (status.equalsIgnoreCase("WARNING")) {
            test.warning(desc, img);
        } else if (status.equalsIgnoreCase("INFO")) {
            test.info(desc);
        }
    }

    public void reportStep(String desc, String status) {
        reportStep(desc, status, true);
    }

    @AfterSuite
    public void endReport() {
        extent.flush();
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}

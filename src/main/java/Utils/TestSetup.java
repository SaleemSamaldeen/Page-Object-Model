package Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class TestSetup extends ConfigReader {

    public WebDriver driver = null;
    //protected static ThreadLocal<ChromeDriver> driver =new ThreadLocal<>();
    protected static final Logger log = LoggerFactory.getLogger("Logger");

    @BeforeMethod
    public void initialize() {
        if (browser.equalsIgnoreCase("Chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:\\AutomationDrivers\\chromedriver\\chromedriver.exe");
            /**  ChromeOptions options = new ChromeOptions();
             options.addArguments("--no-sandbox");
             options.addArguments("--headless");
             options.addArguments("--disable-dev-shm-usage");
             options.addArguments("--window-size=1920x1080");**/
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.firefox.driver", "");
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        driver.get(baseURL);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}

package SampleTest;

import Pages.HomePage;
import Utils.TestDataReader;
import Utils.TestSetup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class TestCase1 extends TestSetup {

    public HomePage homePage;
    WebDriver driver = null;

    //To run test in sequential through DataProvider
    @Test(dataProvider = "GenericDataProvider", dataProviderClass = TestDataReader.class, testName = "GoogleSearch")
    public void googleSearch(String text) {
        System.setProperty("webdriver.chrome.driver", "C:\\AutomationDrivers\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com");
        homePage = new HomePage(driver, test);
        homePage.searchGoogle(text);
        System.out.println("I'm inside test-searchResultsCount " + homePage.getSearchResults());
        System.out.println("Thread count " + Thread.currentThread().getId());
        driver.close();
    }
}

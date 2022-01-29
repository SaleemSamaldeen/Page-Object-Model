package SampleTest;

import Pages.HomePage;
import Utils.TestDataReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class TestCase2 {

    public HomePage homePage;
    WebDriver driver = null;

    @Test(dataProvider = "GenericDataProvider", dataProviderClass = TestDataReader.class, testName = "GoogleSearchCount")
    public void googleSearch(String text) {
        System.setProperty("webdriver.chrome.driver", "C:\\AutomationDrivers\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com");
        homePage = new HomePage(driver);
        homePage.searchGoogle(text);
        System.out.println("I'm inside test-searchResultsCount " + homePage.getSearchResults());
        System.out.println("Thread count " + Thread.currentThread().getId());
        driver.close();
    }
}

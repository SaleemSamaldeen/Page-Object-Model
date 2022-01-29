package SampleTest;

import Pages.HomePage;
import Utils.TestDataReader;
import Utils.TestSetup;
import org.testng.annotations.Test;

public class CheckTest extends TestSetup {
    public HomePage homePage;

    @Test(dataProvider = "GenericDataProvider", dataProviderClass = TestDataReader.class, testName = "GoogleSearchCount")
    public void googleSearch(String text) throws InterruptedException {
        homePage = new HomePage(driver);
        homePage.acceptCookies();
        homePage.hoverOnMobileMenu();
    }
}

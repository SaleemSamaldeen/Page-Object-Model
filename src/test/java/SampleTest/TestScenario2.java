package SampleTest;

import Pages.HomePage;
import Utils.TestDataReader;
import Utils.TestSetup;
import org.testng.annotations.Test;

public class TestScenario2 extends TestSetup {

    public HomePage homePage;

    @Test(dataProvider = "GenericDataProvider", dataProviderClass = TestDataReader.class, testName = "GoogleSearch")
    public void googleSearch(String searchInput) {
        homePage = new HomePage(driver);
        System.out.println(driver);
        homePage.searchGoogle(searchInput);
        System.out.println(homePage.getSearchResults());
    }
}

package SampleTest;

import Pages.HomePage;
import Utils.TestDataReader;
import Utils.TestSetup;
import org.testng.annotations.Test;

public class TestScenario1 extends TestSetup {

    public HomePage homePage;

    @Test(dataProvider = "GenericDataProvider", dataProviderClass = TestDataReader.class, testName = "GoogleSearchCount")
    public void googleSearch(String searchInput) {
        homePage = new HomePage(driver, test);
        System.out.println(driver);
        homePage.searchGoogle(searchInput);
        System.out.println(homePage.getSearchResults());
    }
}

package SampleTest;

import Pages.HomePage;
import Utils.TestDataReader;
import Utils.TestSetup;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestScenarioWithReport2 extends TestSetup {

    @BeforeTest
    public void bt() {
        category = "Smoke";
        author = "Shazia";
        testCaseName = "Search google and verify results";
        testNodes = "TC002";
        testDescription = "Verify google search and results count";
    }

    public HomePage homePage;

    @Test(dataProvider = "GenericDataProvider", dataProviderClass = TestDataReader.class, testName = "GoogleSearch",
            description = "Verify google search and results count")
    public void googleSearchWithReport(String searchInput) {
        homePage = new HomePage(driver, test);
        reportStep("Step 1: " + "Search google page", "info");
        System.out.println(driver);
        homePage.searchGoogle(searchInput);
        System.out.println(homePage.getSearchResults());
    }
}
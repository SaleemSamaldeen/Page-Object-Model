package SampleTest;

import Pages.HomePage;
import Utils.TestSetup;
import org.testng.annotations.Test;

public class FirstTest extends TestSetup {

    public HomePage homePage;

    @Test
    public void googleSearch() {
        homePage = new HomePage(driver, test);
        System.out.println(driver);
        homePage.searchGoogle("Test Automation");
        System.out.println("I'm inside test-searchResultsCount " + homePage.getSearchResults());
        System.out.println("Thread count " + Thread.currentThread().getId());
    }

    @Test
    public void searchResultsCount() {
        homePage = new HomePage(driver, test);
        System.out.println(driver);
        homePage.searchGoogle("Performance testing");
        System.out.println("I'm inside test-searchResultsCount " + homePage.getSearchResults());
        System.out.println("Thread count " + Thread.currentThread().getId());
    }


}

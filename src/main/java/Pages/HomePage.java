package Pages;

import Utils.TestSetup;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends TestSetup {

    public WebDriverWait wait;

    public HomePage(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test;
        PageFactory.initElements(driver, this);
    }

    int timeout = 50;

    @FindBy(xpath = "//input[@name='q']")
    public WebElement searchText;

    @FindBy(xpath = "//div[@id='result-stats']")
    public WebElement searchResults;

    @FindBy(xpath = "//a[@title='Handy']")
    public WebElement mobileHeader;

    @FindBy(xpath = "//a[@title='iPhone 12 mit Vertrag']")
    public WebElement iphone12WithContract;

    @FindBy(xpath = "//a[text()='Akzeptieren']")
    public WebElement cookies;


    public void searchGoogle(String text) {
        waitUntilElementLocated("//input[@name='q']", "Search text box");
        searchText.clear();
        searchText.sendKeys(text);
        searchText.sendKeys(Keys.ENTER);
    }

    public String getSearchResults() {
        waitUntilElementLocated("//div[@id='result-stats']", "Search Result");
        reportStep("Search Results Count", String.valueOf(searchResults.getText().trim().contains("results")), true);
        return searchResults.getText().trim();
    }

    public void hoverOnMobileMenu() throws InterruptedException {
        waitUntilElementLocated("//a[@title='Handy']", "Mobile Menu");
        Actions action = new Actions(driver);
        action.moveToElement(mobileHeader).perform();
        Thread.sleep(2000);
        iphone12WithContract.click();
    }

    public void acceptCookies() throws InterruptedException {
        Thread.sleep(5000);
        cookies.click();
    }

    public void waitUntilElementLocated(String locator, String locatorName) {
        wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
        if (!driver.findElement(By.xpath(locator)).isDisplayed()) {
            reportStep(locatorName + " locator is not present in webpage", "FAIL", true);
        }
    }

}

package Pages;

import Utils.TestSetup;
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

    //public WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    int timeout = 120;

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
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='q']")));
        searchText.clear();
        searchText.sendKeys(text);
        searchText.sendKeys(Keys.ENTER);
    }

    public String getSearchResults() {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='result-stats']")));
        return searchResults.getText().trim();
    }

    public void hoverOnMobileMenu() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Handy']")));
        Actions action = new Actions(driver);
        action.moveToElement(mobileHeader).perform();
        Thread.sleep(2000);
        iphone12WithContract.click();
    }

    public void acceptCookies() throws InterruptedException {
        Thread.sleep(5000);
        cookies.click();
    }

}

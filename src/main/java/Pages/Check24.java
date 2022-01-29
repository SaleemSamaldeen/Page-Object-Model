package Pages;

import Utils.TestSetup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Check24 extends TestSetup {

    public Check24(WebDriver driver) {
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
}

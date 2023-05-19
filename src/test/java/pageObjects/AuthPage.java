package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WebDriverHelper;
import java.time.Duration;

public class AuthPage {
    public WebDriver driver;
    private final WebDriverWait wait;
    private final WebDriverHelper webDriverHelper;

    @FindBy(xpath = "//button[@class='btn authorize unlocked']")
    WebElement authorizeButton;
    @FindBy(xpath = "//div[@class='auth-container']//form//button[@type='submit']")
    WebElement modalAuthorizeButton;
    @FindBy(xpath = "//div[@class='auth-container']//form//input[@type='text']")
    WebElement textBoxApiKey;
    @FindBy(xpath = "//div[@class='auth-container']//form//button[@class='btn modal-btn auth btn-done button']")
    WebElement modalCloseButton;

    public AuthPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        webDriverHelper = new WebDriverHelper(driver);
    }

    public void typeApiKey(String key) {
        textBoxApiKey.sendKeys(key);
    }

    public void clickModalAuthorizeButton() {
        modalAuthorizeButton.click();
    }

    public void clickAuthorizeButton() {
        authorizeButton.click();
    }

    public void clickModalCloseButton() {
        modalCloseButton.click();
    }
}

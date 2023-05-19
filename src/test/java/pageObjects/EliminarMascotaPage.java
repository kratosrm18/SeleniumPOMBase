package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WebDriverHelper;

import java.time.Duration;

public class EliminarMascotaPage {
    public WebDriver driver;
    private final WebDriverWait wait;
    private final WebDriverHelper webDriverHelper;
    @FindBy(id = "operations-pet-deletePet")
    WebElement deletePetButton;
    @FindBy(xpath = "//button[@class='btn try-out__btn']")
    WebElement tryItOutButton;
    @FindBy(xpath = "//input[@placeholder='petId']")
    WebElement petIdTextBox;
    @FindBy(xpath = "//button[@class='btn execute opblock-control__btn']")
    WebElement executeButton;

    public EliminarMascotaPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        webDriverHelper = new WebDriverHelper(driver);
    }

    public void clickDeletePetButton() {
        webDriverHelper.waitUntilElementIsVisible(deletePetButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", deletePetButton);
        deletePetButton.click();
    }

    public void clickTryItOutButton() {
        webDriverHelper.waitUntilElementIsVisible(tryItOutButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tryItOutButton);
        tryItOutButton.click();
    }

    public void sendKeysPetIdTextBox(int t) {
        webDriverHelper.waitUntilElementIsVisible(petIdTextBox);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", petIdTextBox);
        petIdTextBox.clear();
        petIdTextBox.sendKeys(Integer.toString(t));
    }
    public void clickExecuteButton() {
        webDriverHelper.waitUntilElementIsVisible(executeButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", executeButton);
        executeButton.click();
    }
}

package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WebDriverHelper;

import java.time.Duration;

public class ActualizarMascotaPage {
    public WebDriver driver;
    private final WebDriverWait wait;
    private final WebDriverHelper webDriverHelper;
    @FindBy(id = "operations-pet-updatePet")
    WebElement updatePetButton;
    @FindBy(xpath = "//button[@class='btn try-out__btn']")
    WebElement tryItOutButton;
    @FindBy(xpath = "//button[@class='btn execute opblock-control__btn']")
    WebElement executeButton;
    @FindBy(xpath = "//table[@class='responses-table live-responses-table']//td[@class='response-col_status']")
    WebElement responseCode;

    public ActualizarMascotaPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        webDriverHelper = new WebDriverHelper(driver);
    }

    public void clickUpdatePetButton() {
        webDriverHelper.waitUntilElementIsVisible(updatePetButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", updatePetButton);
        updatePetButton.click();
    }

    public void clickTryItOutButton() {
        webDriverHelper.waitUntilElementIsVisible(tryItOutButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tryItOutButton);
        tryItOutButton.click();
    }

    public void clickExecuteButton() {
        webDriverHelper.waitUntilElementIsVisible(executeButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", executeButton);
        executeButton.click();
    }

    public int getResponseCode() {
        webDriverHelper.waitUntilElementIsVisible(responseCode);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", responseCode);
        return Integer.parseInt(responseCode.getText());
    }
}

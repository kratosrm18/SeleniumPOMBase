package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WebDriverHelper;

import java.time.Duration;

public class ListarMascotasPage {
    public WebDriver driver;
    private final WebDriverWait wait;
    private final WebDriverHelper webDriverHelper;
    @FindBy(id = "operations-pet-findPetsByTags")
    WebElement findByTagButton;
    @FindBy(id = "operations-pet-findPetsByStatus")
    WebElement findByStatusButton;
    @FindBy(xpath = "//button[@class='btn try-out__btn']")
    WebElement tryItOutButton;
    @FindBy(xpath = "//button[@class='btn btn-sm json-schema-form-item-add null button']")
    WebElement addStringButton;
    @FindBy(xpath = "//button[@class='btn execute opblock-control__btn']")
    WebElement executeButton;
    @FindBy(xpath = "//tr//input[@type='text']")
    WebElement filterTextBox;
    @FindBy(xpath = "//table[@class='parameters']//select")
    WebElement statusList;

    public ListarMascotasPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        webDriverHelper = new WebDriverHelper(driver);
    }

    public void clickFindByTagButton() {
        webDriverHelper.waitUntilElementIsVisible(findByTagButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", findByTagButton);
        findByTagButton.click();
    }

    public void clickFindByStatusButton() {
        webDriverHelper.waitUntilElementIsVisible(findByStatusButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", findByStatusButton);
        findByStatusButton.click();
    }

    public void clickTryItOutButton() {
        webDriverHelper.waitUntilElementIsVisible(tryItOutButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tryItOutButton);
        tryItOutButton.click();
    }

    public void clickAddStringButton() {
        webDriverHelper.waitUntilElementIsVisible(addStringButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addStringButton);
        addStringButton.click();
    }

    public void sendKeysFilterTextBox(String t) {
        webDriverHelper.waitUntilElementIsVisible(filterTextBox);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", filterTextBox);
        filterTextBox.clear();
        filterTextBox.sendKeys(t);
    }

    public void clickExecuteButton() {
        webDriverHelper.waitUntilElementIsVisible(executeButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", executeButton);
        executeButton.click();
    }

    public void selectStatus(String s) {
        Select list = new Select(statusList);
        list.selectByVisibleText(s);
    }
}

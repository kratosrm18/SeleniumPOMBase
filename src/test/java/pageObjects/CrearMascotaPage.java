package pageObjects;

import org.json.JSONObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WebDriverHelper;

import java.time.Duration;

public class CrearMascotaPage {
    public WebDriver driver;
    private final WebDriverWait wait;
    private final WebDriverHelper webDriverHelper;
    @FindBy(id = "operations-pet-addPet")
    WebElement addPetButton;
    @FindBy(xpath = "//button[@class='btn try-out__btn']")
    WebElement tryItOutButton;
    @FindBy(xpath = "//textarea[@class='body-param__text']")
    WebElement paramsTextArea;
    @FindBy(xpath = "//button[@class='btn execute opblock-control__btn']")
    WebElement executeButton;
    @FindBy(xpath = "//table[@class='responses-table live-responses-table']//td[@class='response-col_status']")
    WebElement responseCode;
    JSONObject params=new JSONObject();
    JSONObject category=new JSONObject();


    public CrearMascotaPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        webDriverHelper = new WebDriverHelper(driver);
    }

    public void clickAddPetButton() {
        webDriverHelper.waitUntilElementIsVisible(addPetButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addPetButton);
        addPetButton.click();
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

    public void setPetId(int id) {
        params.put("id",id);
    }

    public void setWrongPetId(String id) {
        params.put("id",id);
    }

    public void setPetName(String name) {
        params.put("name",name);
    }

    public void setCategoryId(int catId) {
        category.put("id",catId);
        params.put("category",category);
    }

    public void setCategoryName(String cat) {
        category.put("name",cat);
        params.put("category",category);
    }

    public void setPhotoUrl(String photo) {
        String[] photoUrls = {photo};
        params.put("photoUrls",photoUrls);
    }

    public void setTag(String tag) {

        JSONObject tag1=new JSONObject();
        tag1.put("id",0);
        tag1.put("name",tag);

        JSONObject[] tags = {tag1};

        params.put("tags",tags);
    }

    public void setStatus(String status) {
        params.put("status",status);
    }

    public void clearParams(){
        paramsTextArea.clear();
    }
    public void sendKeysParams() {
        paramsTextArea.sendKeys(params.toString());
    }
}

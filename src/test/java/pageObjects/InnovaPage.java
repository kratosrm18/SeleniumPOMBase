package pageObjects;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WebDriverHelper;

import java.time.Duration;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

public class InnovaPage {
    public WebDriver driver;
    private final WebDriverWait wait;
    private final WebDriverHelper webDriverHelper;
    private List<String> completados = Arrays.asList("3", "1", "4");
    @FindBy(id = "txtEmail")
    WebElement email;
    @FindBy(id = "txtPassword")
    WebElement clave;
    @FindBy(id = "btnLogin")
    WebElement iniciarSesion;
    @FindBy(id = "usernameInput")
    WebElement user;
    @FindBy(id = "passwordInput")
    WebElement clave2;
    @FindBy(id = "PageContentPlaceholder_loginControl_LoginButton")
    WebElement iniciarSesion2;
    @FindBy(xpath = "//span[@class='selection']")
    WebElement menu;
    @FindBy(xpath = "//span[@class='select2-results']//ul//li[2]")
    WebElement resi;
    @FindBy(xpath = "//span//i[@class=\"bi bi-easel2 fs-1\"]")
    WebElement icono;
    @FindBy(xpath = "//span[@class='select2-results']//ul//li[2]")
    WebElement misClases;
    @FindBy(xpath = "//div[@class='mb-0']//a[@class='text-dark fw-bold text-hover-primary fs-3']")
    List<WebElement> todasLasClases;
    @FindBy(xpath = "//div[@class='mb-0']//a[@class='text-dark fw-bold text-hover-primary fs-3']")
    List<WebElement> tituloClase;
    @FindBy(xpath = "//div[@class=\"pt-3\"]")
    List<WebElement> modulos;
    @FindBy(xpath = "//div[@class='mb-0']//a[@class='text-dark fw-bold text-hover-primary fs-3']")
    List<WebElement> temas;
    @FindBy(xpath = "//div[@class='mb-0']//a[@class='text-dark fw-bold text-hover-primary fs-3']")
    List<WebElement> subtemas;
    @FindBy(xpath = "//form/div[@id=\"embed\"]/script")
    WebElement script;
    public InnovaPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        webDriverHelper = new WebDriverHelper(driver);
    }
    public void llenarEmailyClave(){
        wait.until(ExpectedConditions.visibilityOf(email));
        email.sendKeys("arelivalladares123@gmail.com");
        clave.sendKeys("JEL2OLK8");
        iniciarSesion.click();
    }
    public void llenarUseryClave() {
        wait.until(ExpectedConditions.visibilityOf(user));
        user.sendKeys("VA178405");
        clave2.sendKeys("Contraseña1234$123");
        iniciarSesion2.click();
    }
    public void cambiarVentana() {
        wait.until(numberOfWindowsToBe(2));
        String originalWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }
    public void seleccionarResi() {
        wait.until(ExpectedConditions.visibilityOf(menu));
        menu.click();
        wait.until(ExpectedConditions.visibilityOf(resi));
        resi.click();
    }
    public void misClases() throws InterruptedException {
        //Thread.sleep(3000);
        wait.until(numberOfWindowsToBe(1));
        //String originalWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
                driver.switchTo().window(windowHandle);
                break;
        }
        driver.get("https://virtualqxmedic.com/videoteca/mis-clases");
    }

    public void scrapStart() {
        //todasLasClases.stream().forEach((c)->{
            //driver.get(c.getAttribute("href"));
            driver.get("https://virtualqxmedic.com/videoteca/mis-clases/WlFOUVdzQ2wrWWJkUDBmWks0YlA4Zz09");
            wait.until(ExpectedConditions.visibilityOfAllElements(modulos));
            modulos.stream().forEach((m)->{
                System.out.println(modulos.indexOf(m));
                if(modulos.indexOf(m)>0) {
                    m.findElement(By.xpath(".//div[@class=\"text-gray-800 fw-bold text-capitalize\"]")).click();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                if(modulos.indexOf(m)==0) {
                    WebElement element = m.findElement(By.xpath(".//a[@class=\"mb-1 text-dark text-hover-primary fw-bold text-capitalize videoclase\"]"));
                    //JavascriptExecutor executor = (JavascriptExecutor)driver;
                    //executor.executeScript("arguments[0].click();", element);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();",element);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                m.findElement(By.xpath(".//a[@class=\"mb-1 text-dark text-hover-primary fw-bold text-capitalize videoclase\"]")).click();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                if(modulos.indexOf(m)>2) {
                    System.out.println(script.getText());
                }
            });
            //System.out.println(c.getAttribute("href"));
        //});
    }
}

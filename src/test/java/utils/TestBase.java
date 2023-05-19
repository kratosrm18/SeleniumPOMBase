package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class TestBase {
    public WebDriver driver;

    public WebDriver WebDriverManager() throws IOException {

        FileInputStream fil = new FileInputStream(System.getProperty("user.dir") + "//src//test//resources//globalProperties//global.properties");
        Properties prop = new Properties();
        prop.load(fil);
        String url = prop.getProperty("URL");

        if (driver == null) {
            if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments ("--disable-notifications");
                chromeOptions.addArguments("--remote-allow-origins=*");
                this.driver = new ChromeDriver(chromeOptions);
            }

            if (prop.getProperty("browser").equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setProfile(new FirefoxProfile());
                firefoxOptions.addPreference("dom.webnotifications.enabled", false);
                this.driver = new FirefoxDriver();

            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
            driver.get(url);
        }

        return driver;
    }
}

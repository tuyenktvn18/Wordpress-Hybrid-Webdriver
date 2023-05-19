package commons.driver;

import commons.BrowserLists;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LocalFactory {
    private String browserName;

    public LocalFactory(String browserName) {
        this.browserName = browserName;
    }

    private static ThreadLocal<WebDriver> tDriver = new ThreadLocal<WebDriver>();

    public WebDriver createDriver() {
        BrowserLists browserList = BrowserLists.valueOf(browserName.toUpperCase());
        if (browserList == BrowserLists.FIREFOX) {
            System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addPreference("intl.accept_languages", "vi-vn, vi");
            options.addArguments("--disable-notifications");
            tDriver.set(new FirefoxDriver(options));
        } else if (browserList == BrowserLists.CHROME) {
            System.setProperty("webdriver.chrome.argd", "--disable-logging");
            System.setProperty("webdriver.chrome.silentOutput", "true");
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--lang=vi");
            options.addArguments("--disable-notifications");
            options.setExperimentalOption("useAutomationExtension", false);
            options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

            options.addArguments("--start-maximized");
            options.addArguments("--disable-web-security");
            options.addArguments("--no-proxy-server");

            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);

            options.setExperimentalOption("prefs", prefs);
            tDriver.set(new ChromeDriver(options));
        } else if (browserList == BrowserLists.EDGE) {
            WebDriverManager.edgedriver().setup();
            tDriver.set(new EdgeDriver());
        } else if (browserList == BrowserLists.H_CHROME) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1400,800");
            tDriver.set(new ChromeDriver(options));
        } else if (browserList == BrowserLists.H_FIREFOX) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless");
            options.addArguments("window-size=1920x1080");
            tDriver.set(new FirefoxDriver(options));
        } else {
            throw new RuntimeException("Browser name invalid");
        }
        return tDriver.get();
    }
}

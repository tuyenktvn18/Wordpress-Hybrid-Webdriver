package commons.driver;

import commons.BrowserLists;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class GridFactory {
    private WebDriver driver;
    private String browserName;
    private String ipAddress;
    private String portNumber;

    public GridFactory(String browserName, String ipAddress, String portNumber) {
        this.browserName = browserName;
        this.ipAddress = ipAddress;
        this.portNumber = portNumber;
    }

    public WebDriver createDriver() {
        BrowserLists browser = BrowserLists.valueOf(browserName.toUpperCase());
        DesiredCapabilities capability = null;

        if (browser == BrowserLists.CHROME) {
            WebDriverManager.chromedriver().setup();
            capability = DesiredCapabilities.chrome();
            capability.setBrowserName("chrome");
            capability.setPlatform(Platform.WINDOWS);
            ChromeOptions options = new ChromeOptions();
            options.merge(capability);
        } else if (browser == BrowserLists.FIREFOX) {
            WebDriverManager.firefoxdriver().setup();
            capability = DesiredCapabilities.firefox();
            capability.setBrowserName("firefox");
            capability.setPlatform(Platform.WINDOWS);
            FirefoxOptions options = new FirefoxOptions();
            options.merge(capability);
        } else if (browser == BrowserLists.EDGE) {
            WebDriverManager.edgedriver().setup();
            capability = DesiredCapabilities.edge();
            capability.setBrowserName("edge");
            capability.setPlatform(Platform.ANY);
            capability.setJavascriptEnabled(true);
        }
        try {
            driver = new RemoteWebDriver(new URL(String.format("http://%s:%s/wd/hub", ipAddress, portNumber)), capability);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }
}

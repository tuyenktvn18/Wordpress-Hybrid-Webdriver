package commons.driver;

import commons.GlobalConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackFactory {

    private WebDriver driver;
    private String browserName;
    private String osName;
    private String osVersion;


    public BrowserstackFactory(String browserName, String osName, String os_version) {
        this.browserName = browserName;
        this.osName = osName;
        this.osVersion = os_version;
    }

    //https://www.browserstack.com/docs/automate/selenium/getting-started/java
    public WebDriver createDriver() {
        DesiredCapabilities capability = new DesiredCapabilities();
        capability.setCapability("os", osName);
        capability.setCapability("osVersion", osVersion);
        capability.setCapability("browser", browserName);
        capability.setCapability("browserVersion", "latest");
        capability.setCapability("browserstack.debug", "true");
        capability.setCapability("project", "Wordpress");
        capability.setCapability("name", "Run on " + " | " + osName + " | " + browserName);
//        capability.setCapability("resolution", "1920X1080");
        try {
            driver = new RemoteWebDriver(new URL(GlobalConstants.getGlobalConstants().getBrowserStackUrl()), capability);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }
}

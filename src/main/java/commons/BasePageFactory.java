package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageFactory {
    public static BasePageFactory getBasePageObject() {
        return new BasePageFactory();
    }

    public void openPageUrl(WebDriver driver, String pageUrl) {
        driver.get(pageUrl);
    }

    public String getTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public String getPageUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public String getPageSource(WebDriver driver) {
        return driver.getPageSource();
    }

    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    public void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    public void refreshCurrentPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public Alert waiForAlertPresence(WebDriver driver) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        return explicitWait.until(ExpectedConditions.alertIsPresent());
    }

    public void acceptAlert(WebDriver driver) {
        waiForAlertPresence(driver).accept();
    }

    public void cancelAlert(WebDriver driver) {
        waiForAlertPresence(driver).dismiss();
    }

    public String getAlertText(WebDriver driver) {
        return waiForAlertPresence(driver).getText();
    }

    public void sendkeyToAlert(WebDriver driver, String textValue) {
        waiForAlertPresence(driver).sendKeys(textValue);
    }

    public void switchToWindowByID(WebDriver driver, String windowID) {
        Set<String> allWindowIDs = driver.getWindowHandles();

        for (String id : allWindowIDs) {
            if (!id.equals(windowID)) {
                driver.switchTo().window(id);
                break;
            }
        }
    }

    public void switchToWindowByTitle(WebDriver driver, String expectedPageTitle) {
        Set<String> allWindows = driver.getWindowHandles();

        for (String window : allWindows) {
            driver.switchTo().window(window);
            String actualPageTitle = driver.getTitle().trim();
            if (expectedPageTitle.equals(actualPageTitle)) {
                break;
            }
        }
    }

    public void closeWindowByID(WebDriver driver, String currentPageID) {
        Set<String> allWindows = driver.getWindowHandles();

        for (String window : allWindows) {
            if (!window.equals(currentPageID)) {
                driver.switchTo().window(window);
                driver.close();
            }
        }
        driver.switchTo().window(currentPageID);
    }

    public void sleepInsecond(long second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void waitForElementVisible(WebDriver driver, WebElement element) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForAllElementVisible(WebDriver driver, List<WebElement> elements) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void waitForElementInvisible(WebDriver driver, WebElement element) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitForAllElementInvisible(WebDriver driver, List<WebElement> elements) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(elements));
    }

    public void waitForElementClickable(WebDriver driver, WebElement element) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    private long longTimeout = 30;

    public void clickToElement(WebDriver driver, WebElement element) {
        element.click();
    }

    public boolean isElementDisplayed(WebDriver driver, WebElement element) {
        return element.isDisplayed();
    }

    public String getElementText(WebDriver driver, WebElement element) {
        return element.getText();
    }

    public void sendkeyToElement(WebDriver driver, WebElement element, String textValue) {
        element.clear();
        element.sendKeys(textValue);
    }
}

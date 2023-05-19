package pageObjects.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUI.user.UserHomePagePageUI;

public class UsePageDetailPO extends BasePage {
    private WebDriver driver;

    public UsePageDetailPO(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isPageInforDisplayedWithPageTitle(String pageTitle) {
        waitForAllElementVisible(driver, UserHomePagePageUI.PAGE_TITLE, pageTitle);
        return isElementDisplayed(driver, UserHomePagePageUI.PAGE_TITLE, pageTitle);
    }

    public boolean isPageInforDisplayedWithPageBody(String pageTitle) {
        waitForAllElementVisible(driver, UserHomePagePageUI.PAGE_BODY, pageTitle);
        return isElementDisplayed(driver, UserHomePagePageUI.PAGE_BODY, pageTitle);
    }
}

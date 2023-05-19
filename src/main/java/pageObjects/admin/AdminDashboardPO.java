package pageObjects.admin;

import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.admin.AdminDashboardPageUI;

public class AdminDashboardPO extends BasePage{
    private WebDriver driver;

    public AdminDashboardPO (WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click to Post menu link")
    public AdminPostSearchPO clickToPostMenuLink() {
        waitForElementClickable(driver, AdminDashboardPageUI.POST_MENU_LINK);
        clickToElement(driver, AdminDashboardPageUI.POST_MENU_LINK);
        return PageGeneratorManager.getAdminPostSearchPage(driver);
    }

    @Step("Click to Pages menu link")
    public AdminPageSearchPO clickToPagesMenuLink() {
        waitForElementClickable(driver, AdminDashboardPageUI.PAGE_MENU_LINK);
        clickToElement(driver, AdminDashboardPageUI.PAGE_MENU_LINK);
        return PageGeneratorManager.getAdminPageSearchPage(driver);
    }

    @Step("Click to Media menu link")
    public AdminMediaLibPO clickToMediaMenuLink() {
        waitForElementClickable(driver, AdminDashboardPageUI.MEDIA_MENU_LINK);
        clickToElement(driver, AdminDashboardPageUI.MEDIA_MENU_LINK);
        return PageGeneratorManager.getAdminMediaLibPage(driver);
    }
}
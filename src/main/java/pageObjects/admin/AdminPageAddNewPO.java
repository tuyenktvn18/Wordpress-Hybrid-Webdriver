package pageObjects.admin;

import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.admin.AdminPageAddnewPageUI;


public class AdminPageAddNewPO  extends BasePage{
    private WebDriver driver;

    public AdminPageAddNewPO (WebDriver driver) {
        this.driver = driver;
    }

    public void enterIntoPageTitle(String pageTitle) {
        if (!isElementUndisplayed(driver, AdminPageAddnewPageUI.CLOSE_POPUP)) {
            clickToElement(driver,AdminPageAddnewPageUI.CLOSE_POPUP);
        }
        waitForElementVisible(driver, AdminPageAddnewPageUI.ADD_TITLE_TEXTBOX);
        sendkeyToElement(driver, AdminPageAddnewPageUI.ADD_TITLE_TEXTBOX, pageTitle);
    }

    public void enterIntoPageBody(String pageBody) {
        waitForElementClickable(driver, AdminPageAddnewPageUI.PAGE_BODY_BEFORE_TEXTBOX);
        clickToElement(driver, AdminPageAddnewPageUI.PAGE_BODY_BEFORE_TEXTBOX);
        clearValueInElementByDeleteKey(driver,AdminPageAddnewPageUI.PAGE_BODY_AFTER_TEXTBOX);
        waitForAllElementVisible(driver, AdminPageAddnewPageUI.PAGE_BODY_AFTER_TEXTBOX);
        sendkeyToElement(driver, AdminPageAddnewPageUI.PAGE_BODY_AFTER_TEXTBOX, pageBody);
    }

    @Step("Click to update or publish button")
    public void clickToUpdateOrPublishButton() {
        waitForElementClickable(driver, AdminPageAddnewPageUI.UPDATE_OR_PUBLIC_BUTTON);
        clickToElement(driver, AdminPageAddnewPageUI.UPDATE_OR_PUBLIC_BUTTON);
    }

    @Step("Click to confirm publish button")
    public void clickToConfirmPublishButton() {
        waitForElementClickable(driver, AdminPageAddnewPageUI.CONFIRM_PUBLIC_BUTTON);
        clickToElement(driver, AdminPageAddnewPageUI.CONFIRM_PUBLIC_BUTTON);
    }

    @Step("Check post publish message displayed")
    public boolean isPostPublishMessageDisplayed(String publishedMessage) {
        waitForElementVisible(driver,AdminPageAddnewPageUI.PUBLISHED_MESSAGE,publishedMessage);
        return isElementDisplayed(driver, AdminPageAddnewPageUI.PUBLISHED_MESSAGE,publishedMessage);
    }

    @Step("Open search post page")
    public AdminPageSearchPO openSearchPostPage(String searchPageUrl) {
        openPageUrl(driver, searchPageUrl);
        return PageGeneratorManager.getAdminPageSearchPage(driver);
    }

    @Step("Enter into page title with {0} and body with {1}")
    public void enterIntoPageTitleAndBody(String pageTitle, String pageBody) {
        enterIntoPageTitle(pageTitle);
        enterIntoPageBody(pageBody);
    }
}
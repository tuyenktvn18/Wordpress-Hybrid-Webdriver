package pageObjects.admin;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.admin.AdminPageSearchPageUI;

public class AdminPageSearchPO extends BasePage {

    private WebDriver driver;

    public AdminPageSearchPO(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click to add new button")
    public AdminPageAddNewPO clickToAddNewButton() {
        waitForElementClickable(driver, AdminPageSearchPageUI.ADD_NEW_BUTTON);
        clickToElement(driver, AdminPageSearchPageUI.ADD_NEW_BUTTON);
        return new AdminPageAddNewPO(driver);
    }

    @Step("Enter to search textbox with value {0}")
    public void enterToSearchTextbox(String pageTitle) {
        waitForAllElementVisible(driver, AdminPageSearchPageUI.PAGE_SEARCH_TEXTBOX);
        sendkeyToElement(driver, AdminPageSearchPageUI.PAGE_SEARCH_TEXTBOX, pageTitle);
    }

    @Step("click to search page button")
    public void clickToSearchPageButton() {
        waitForElementClickable(driver, AdminPageSearchPageUI.PAGE_SEARCH_BUTTON);
        clickToElement(driver, AdminPageSearchPageUI.PAGE_SEARCH_BUTTON);
    }

    @Step("Check page search tab displayed with value {1}")
    public boolean isPageSearchTableDisplayed(String columnByName, String pageTitle) {
        waitForElementVisible(driver, AdminPageSearchPageUI.DYNAMIC_VALUE_IN_COLUMN_BY_NAME, columnByName, pageTitle);
        return isElementDisplayed(driver, AdminPageSearchPageUI.DYNAMIC_VALUE_IN_COLUMN_BY_NAME, columnByName, pageTitle);

    }

    @Step("Click to page title link  with value {1}")
    public AdminPageAddNewPO clickToPageTitleLink(String columnByName, String pageTitle) {
        waitForElementClickable(driver, AdminPageSearchPageUI.DYNAMIC_VALUE_IN_COLUMN_BY_NAME,columnByName, pageTitle);
        clickToElement(driver, AdminPageSearchPageUI.DYNAMIC_VALUE_IN_COLUMN_BY_NAME,columnByName, pageTitle);
        return new AdminPageAddNewPO(driver);
    }

    @Step("Select page detail checkbox with value {0}")
    public void selectPageDetailcheckbox(String editPageTitle) {
        waitForElementClickable(driver, AdminPageSearchPageUI.PAGE_TITLE_CHECKBOX, editPageTitle);
        clickToElement(driver, AdminPageSearchPageUI.PAGE_TITLE_CHECKBOX, editPageTitle);
    }

    @Step("select item action dropdown with value {0}")
    public void selectItemActionDropdown(String actionDropdown) {
        waitForElementClickable(driver, AdminPageSearchPageUI.ACTION_DROPDOWN, actionDropdown);
        selectItemInDefaultDropdown(driver, AdminPageSearchPageUI.ACTION_DROPDOWN, actionDropdown);
    }

    @Step("Click to apply button")
    public void clickToApplyButton() {
        waitForElementClickable(driver, AdminPageSearchPageUI.APPLY_BUTTON);
        clickToElement(driver, AdminPageSearchPageUI.APPLY_BUTTON);
    }

    @Step("Check move to trash message displayed with message {0}")
    public boolean isMoveToTrashMessageDisplayed(String message) {
        waitForElementVisible(driver, AdminPageSearchPageUI.MOVE_TO_TRASH_MESSAGE, message);
        return isElementDisplayed(driver, AdminPageSearchPageUI.MOVE_TO_TRASH_MESSAGE, message);
    }

    @Step("Check no page found message displayed with message {0}")
    public boolean isNoPageFoundMessageDisplayed(String message) {
        waitForElementVisible(driver, AdminPageSearchPageUI.NO_PAGE_FOUND_MESSAGE, message);
        return isElementDisplayed(driver, AdminPageSearchPageUI.NO_PAGE_FOUND_MESSAGE, message);
    }

}

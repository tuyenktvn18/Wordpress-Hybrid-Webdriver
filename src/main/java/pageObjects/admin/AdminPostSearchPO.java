package pageObjects.admin;

import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.admin.AdminPostSearchPageUI;


public class AdminPostSearchPO extends BasePage {
    private WebDriver driver;

    public AdminPostSearchPO(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click to add new button")
    public AdminPostAddNewPO clickToAddNewButton() {
        waitForElementClickable(driver, AdminPostSearchPageUI.ADD_NEW_BUTTON);
        clickToElement(driver, AdminPostSearchPageUI.ADD_NEW_BUTTON);
        return new AdminPostAddNewPO(driver);
    }

    @Step("Enter to search textbox with {0}")
    public void enterToSearchTextbox(String postTitle) {
        waitForAllElementVisible(driver, AdminPostSearchPageUI.POST_SEARCH_TEXTBOX);
        sendkeyToElement(driver, AdminPostSearchPageUI.POST_SEARCH_TEXTBOX, postTitle);
    }

    @Step("Click to search post button")
    public void clickToSearchPostButton() {
        waitForElementClickable(driver, AdminPostSearchPageUI.POST_SEARCH_BUTTON);
        clickToElement(driver, AdminPostSearchPageUI.POST_SEARCH_BUTTON);
    }

    @Step("Check post search table displayed with post title {1}")
    public boolean isPostSearchTableDisplayed(String columnByName, String postTitle) {
        waitForElementVisible(driver, AdminPostSearchPageUI.DYNAMIC_VALUE_IN_COLUMN_BY_NAME, columnByName, postTitle);
        return isElementDisplayed(driver, AdminPostSearchPageUI.DYNAMIC_VALUE_IN_COLUMN_BY_NAME, columnByName, postTitle);
    }

    @Step("Click to post title link with post title {1}")
    public void clickToPostTitleLink(String columnByName, String postTitle) {
        waitForElementClickable(driver, AdminPostSearchPageUI.DYNAMIC_VALUE_IN_COLUMN_BY_NAME, columnByName, postTitle);
        clickToElement(driver, AdminPostSearchPageUI.DYNAMIC_VALUE_IN_COLUMN_BY_NAME, columnByName, postTitle);

    }

    @Step("Select post detail checkbox")
    public void selectPostDetailcheckbox(String editPostTitle) {
        waitForElementClickable(driver, AdminPostSearchPageUI.POST_TITLE_CHECKBOX, editPostTitle);
        clickToElement(driver, AdminPostSearchPageUI.POST_TITLE_CHECKBOX, editPostTitle);
    }

    @Step("Select item action dropdown with {0}")
    public void selectItemActionDropdown(String actionDropdown) {
        waitForElementClickable(driver, AdminPostSearchPageUI.ACTION_DROPDOWN, actionDropdown);
        selectItemInDefaultDropdown(driver, AdminPostSearchPageUI.ACTION_DROPDOWN, actionDropdown);
    }

    @Step("Click to apply button")
    public void clickToApplyButton() {
        waitForElementClickable(driver, AdminPostSearchPageUI.APPLY_BUTTON);
        clickToElement(driver, AdminPostSearchPageUI.APPLY_BUTTON);
    }

    @Step("Check move to trash message displayed with message {0}")
    public boolean isMoveToTrashMessageDisplayed(String message) {
        waitForElementVisible(driver, AdminPostSearchPageUI.MOVE_TO_TRASH_MESSAGE, message);
        return isElementDisplayed(driver, AdminPostSearchPageUI.MOVE_TO_TRASH_MESSAGE, message);

    }

    @Step("Check no post found message displayed with message {0}")
    public boolean isNoPostFoundMessageDisplayed(String message) {
        waitForElementVisible(driver, AdminPostSearchPageUI.NO_POSTS_FOUND_MESSAGE, message);
        return isElementDisplayed(driver, AdminPostSearchPageUI.NO_POSTS_FOUND_MESSAGE, message);
    }

    @Step("Click To Post SubMenu Link")
    public AdminPostCategoryPO clickToPostSubMenuLink(String subMenuName) {
        waitForElementClickable(driver,AdminPostSearchPageUI.SUB_MENU_IN_POST,subMenuName);
        clickToElement(driver,AdminPostSearchPageUI.SUB_MENU_IN_POST,subMenuName);
        return PageGeneratorManager.getAdminPostCategoryPage(driver);
    }



}
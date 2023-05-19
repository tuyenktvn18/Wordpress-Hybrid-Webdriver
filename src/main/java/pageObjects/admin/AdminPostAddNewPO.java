package pageObjects.admin;

import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.admin.AdminPostAddnewPageUI;


public class AdminPostAddNewPO extends BasePage{
    private WebDriver driver;

    public AdminPostAddNewPO(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Enter into post title with value {0}")
    public void enterIntoPostTitle(String postTitle) {
        if (!isElementUndisplayed(driver, AdminPostAddnewPageUI.CLOSE_POPUP)) {
            clickToElement(driver,AdminPostAddnewPageUI.CLOSE_POPUP);
        }
        waitForElementVisible(driver, AdminPostAddnewPageUI.ADD_TITLE_TEXTBOX);
        sendkeyToElement(driver, AdminPostAddnewPageUI.ADD_TITLE_TEXTBOX, postTitle);
    }

    @Step("Enter into post body with value {0}")
    public void enterIntoPostBody(String postBody) {
        waitForElementClickable(driver, AdminPostAddnewPageUI.POST_BODY_BEFORE_TEXTBOX);
        clickToElement(driver, AdminPostAddnewPageUI.POST_BODY_BEFORE_TEXTBOX);
        clearValueInElementByDeleteKey(driver,AdminPostAddnewPageUI.POST_BODY_AFTER_TEXTBOX);
        waitForAllElementVisible(driver, AdminPostAddnewPageUI.POST_BODY_AFTER_TEXTBOX);
        sendkeyToElement(driver, AdminPostAddnewPageUI.POST_BODY_AFTER_TEXTBOX, postBody);
    }

    @Step("Enter into edit post body with value {0}")
    public void enterIntoEditPostBody(String editPostBody) {
        clearValueInElementByDeleteKey(driver,AdminPostAddnewPageUI.POST_BODY_AFTER_TEXTBOX);
        waitForAllElementVisible(driver, AdminPostAddnewPageUI.POST_BODY_AFTER_TEXTBOX);
        sendkeyToElement(driver, AdminPostAddnewPageUI.POST_BODY_AFTER_TEXTBOX, editPostBody);
    }

    @Step ("Click to update or publish button")
    public void clickToUpdateOrPublishButton() {
        waitForElementClickable(driver, AdminPostAddnewPageUI.UPDATE_OR_PUBLIC_BUTTON);
        clickToElement(driver, AdminPostAddnewPageUI.UPDATE_OR_PUBLIC_BUTTON);
    }

    @Step("Click to confirm publish button")
    public void clickToConfirmPublishButton() {
        waitForElementClickable(driver, AdminPostAddnewPageUI.CONFIRM_PUBLIC_BUTTON);
        clickToElement(driver, AdminPostAddnewPageUI.CONFIRM_PUBLIC_BUTTON);
    }

    @Step("Check is post publish message displayed with message {0}")
    public boolean isPostPublishMessageDisplayed(String publishedMessage) {
        waitForElementVisible(driver,AdminPostAddnewPageUI.PUBLISHED_MESSAGE,publishedMessage);
        return isElementDisplayed(driver, AdminPostAddnewPageUI.PUBLISHED_MESSAGE,publishedMessage);
    }

    @Step("Open search post page")
    public AdminPostSearchPO openSearchPostPage(String searchPostUrl) {
        openPageUrl(driver, searchPostUrl);
        return PageGeneratorManager.getAdminPostSearchPage(driver);
    }



}
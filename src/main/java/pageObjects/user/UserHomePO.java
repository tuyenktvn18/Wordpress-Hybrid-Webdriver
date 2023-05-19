package pageObjects.user;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUI.user.UserHomePagePageUI;

public class UserHomePO extends BasePage {
    private WebDriver driver;

    public UserHomePO(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Check post infor displayed with post title {0}")
    public boolean isPostInforDisplayedWithPostTitle(String postTitle) {
        waitForAllElementVisible(driver, UserHomePagePageUI.POST_TITLE, postTitle);
        return isElementDisplayed(driver, UserHomePagePageUI.POST_TITLE, postTitle);
    }

    @Step("Check post infor displayed with post body {1}")
    public boolean isPostInforDisplayedWithPostBody(String postTitle, String postbody) {
        waitForAllElementVisible(driver, UserHomePagePageUI.POST_BODY, postTitle, postbody);
        return isElementDisplayed(driver, UserHomePagePageUI.POST_BODY, postTitle, postbody);
    }

    @Step("Check post infor displayed with post author {1}")
    public boolean isPostInforDisplayedWithPostAuthor(String postTitle, String adminUserName) {
        waitForAllElementVisible(driver, UserHomePagePageUI.POST_BY, postTitle, adminUserName);
        return isElementDisplayed(driver, UserHomePagePageUI.POST_BY, postTitle, adminUserName);
    }

    @Step("Check post infor displayed with post time {1}")
    public boolean isPostInforDisplayedWithPostTime(String postTitle, String currentDay) {
        waitForAllElementVisible(driver, UserHomePagePageUI.POST_TIME, postTitle, currentDay);
        return isElementDisplayed(driver, UserHomePagePageUI.POST_TIME, postTitle, currentDay);
    }

    @Step("Click to post title {0}")
    public UserPostDetailPO clickToPostTitle(String postTitle) {
        waitForElementClickable(driver, UserHomePagePageUI.POST_TITLE, postTitle);
        clickToElement(driver, UserHomePagePageUI.POST_TITLE, postTitle);
        return PageGeneratorManager.getUserPostDetailPage(driver);
    }

    @Step("Check post infor undisplayed with post title {0}")
    public boolean isPostInforUndisplayedWithPostTitle(String postTitle) {
        return isElementUndisplayed(driver, UserHomePagePageUI.POST_TITLE, postTitle);
    }

    @Step("Enter to search textbox {0}")
    public void enterToSearchTextbox(String searchItem) {
        waitForElementVisible(driver, UserHomePagePageUI.SEARCH_TEXTBOX);
        sendkeyToElement(driver, UserHomePagePageUI.SEARCH_TEXTBOX, searchItem);
    }

    @Step("Click to search textbox")
    public UserPostSearchPO clickToSearchButton() {
        waitForElementClickable(driver, UserHomePagePageUI.SEARCH_BUTTON);
        clickToElement(driver, UserHomePagePageUI.SEARCH_BUTTON);
        return PageGeneratorManager.getUserPostSearchPage(driver);
    }

    @Step("Check page infor displayed with page title menu")
    public boolean isPageInforDisplayedWithPageTitleInMenu(String pageTitle) {
        waitForAllElementVisible(driver, UserHomePagePageUI.PAGE_TITLE_MENU, pageTitle);
        return isElementDisplayed(driver, UserHomePagePageUI.PAGE_TITLE_MENU, pageTitle);
    }

    @Step("Click to page title {0}")
    public UsePageDetailPO clickToPageTitle(String pageTitle) {
        waitForElementClickable(driver, UserHomePagePageUI.PAGE_TITLE_MENU, pageTitle);
        clickToElement(driver, UserHomePagePageUI.PAGE_TITLE_MENU, pageTitle);
        return PageGeneratorManager.getUserPageDetailPage(driver);
    }

    @Step("Check page infor undisplayed with page title {0}")
    public boolean isPageInforUndisplayedWithPageTitle(String editPageTitle) {
        return isElementUndisplayed(driver, UserHomePagePageUI.PAGE_TITLE_MENU, editPageTitle);
    }
}

package pageObjects.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUI.user.UserPostDetailPageUI;

public class UserPostDetailPO extends BasePage {
    private WebDriver driver;

    public UserPostDetailPO(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isPostInforDisplayedWithPostTitle(String postTitle) {
        waitForAllElementVisible(driver, UserPostDetailPageUI.POST_TITLE, postTitle);
        return isElementDisplayed(driver, UserPostDetailPageUI.POST_TITLE, postTitle);
    }

    public boolean isPostInforDisplayedWithPostBody(String postTitle,String postbody) {
        waitForAllElementVisible(driver, UserPostDetailPageUI.POST_BODY, postTitle,postbody);
        return isElementDisplayed(driver, UserPostDetailPageUI.POST_BODY, postTitle,postbody);
    }

    public boolean isPostInforDisplayedWithPostAuthor(String postTitle,String adminUserName) {
        waitForAllElementVisible(driver, UserPostDetailPageUI.POST_BY, postTitle,adminUserName);
        return isElementDisplayed(driver, UserPostDetailPageUI.POST_BY, postTitle,adminUserName);
    }

    public boolean isPostInforDisplayedWithPostTime(String postTitle,String currentDay) {
        waitForAllElementVisible(driver, UserPostDetailPageUI.POST_TIME, postTitle,currentDay);
        return isElementDisplayed(driver, UserPostDetailPageUI.POST_TIME, postTitle,currentDay);
    }
}

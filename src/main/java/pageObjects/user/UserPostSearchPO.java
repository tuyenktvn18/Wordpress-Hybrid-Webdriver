package pageObjects.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUI.user.UserPostSearchPageUI;

public class UserPostSearchPO extends BasePage {

    private WebDriver driver;

    public UserPostSearchPO(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isNothingFoundMessageDisplayed(String message) {
        waitForElementVisible(driver, UserPostSearchPageUI.NOTHING_FOUND_MESSAGE, message);
        return isElementDisplayed(driver, UserPostSearchPageUI.NOTHING_FOUND_MESSAGE, message);
    }
}

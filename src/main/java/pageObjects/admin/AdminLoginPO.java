package pageObjects.admin;

import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.admin.AdminLoginPageUI;

public class AdminLoginPO extends BasePage{
    private WebDriver driver;

    public AdminLoginPO(WebDriver driver) {
        this.driver = driver;
    }

    public void inputToUsernameTextbox(String adminUserName) {
        waitForElementVisible(driver, AdminLoginPageUI.USER_NAME_TEXTBOX);
        sendkeyToElement(driver, AdminLoginPageUI.USER_NAME_TEXTBOX, adminUserName);
    }

    public void inputToPasswordTextbox(String adminpassword) {
        waitForElementVisible(driver, AdminLoginPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver, AdminLoginPageUI.PASSWORD_TEXTBOX, adminpassword);
    }

    public void clickToLoginButton() {
        waitForElementClickable(driver, AdminLoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, AdminLoginPageUI.LOGIN_BUTTON);
    }

    @Step("Login into admin page username with {0} and password with {1}")
    public AdminDashboardPO loginIntoAdminPage(String adminUserName, String adminpassword){
        inputToUsernameTextbox(adminUserName);
        inputToPasswordTextbox(adminpassword);
        clickToLoginButton();
        return PageGeneratorManager.getAdminDashboardPage(driver);
    }
}

package pageObjects.admin;

import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.admin.AdminPostUpdateCategoryPageUI;

public class AdminPostUpdateCategoryPO extends BasePage {
    private WebDriver driver;

    public AdminPostUpdateCategoryPO(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToUpdateButton() {
        waitForElementClickable(driver, AdminPostUpdateCategoryPageUI.UPDATE_BUTTON);
        clickToElement(driver, AdminPostUpdateCategoryPageUI.UPDATE_BUTTON);
    }

    public boolean isCategoryUpdatedMessageDisplayed(String mesage) {
        waitForElementVisible(driver, AdminPostUpdateCategoryPageUI.UPDATED_MESSAGE, mesage);
        return isElementDisplayed(driver, AdminPostUpdateCategoryPageUI.UPDATED_MESSAGE, mesage);
    }

    public AdminPostCategoryPO clickToGoTocategoriesLink() {
        waitForElementClickable(driver, AdminPostUpdateCategoryPageUI.GO_TO_CATEGORIES_LINK);
        clickToElement(driver, AdminPostUpdateCategoryPageUI.GO_TO_CATEGORIES_LINK);
        return PageGeneratorManager.getAdminPostCategoryPage(driver);
    }

    public void enterIntoDetailCategoryByInput(String labelName, String editDetailCategory) {
        waitForElementVisible(driver, AdminPostUpdateCategoryPageUI.DYNAMIC_DETAIL_CATEGORY, labelName);
        sendkeyToElement(driver, AdminPostUpdateCategoryPageUI.DYNAMIC_DETAIL_CATEGORY, editDetailCategory,labelName);
    }

    public void enterIntoDetailCategoryBySelect(String labelName, String editParentCategory) {
        waitForElementVisible(driver, AdminPostUpdateCategoryPageUI.DYNAMIC_DETAIL_CATEGORY, labelName);
        selectItemInDefaultDropdown(driver, AdminPostUpdateCategoryPageUI.DYNAMIC_DETAIL_CATEGORY, editParentCategory,labelName);

    }
}
package pageObjects.admin;

import commons.DatabaseBuilder;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUI.admin.AdminPostCategoryPageUI;

import java.sql.Connection;
import java.util.HashMap;


public class AdminPostCategoryPO extends AdminPostSearchPO {
    private WebDriver driver;
    DatabaseBuilder databaseBuilder = new DatabaseBuilder();

    public AdminPostCategoryPO(WebDriver driver) {
        super(driver);
        this.driver = driver;

    }

    @Step("Enter into detail Category with value is {1}")
    public void enterIntoDetailCategory(String labelName, String valuedDetailCategory) {
        waitForElementVisible(driver, AdminPostCategoryPageUI.DYNAMIC_DETAIL_CATEGORY_INPUT, labelName);
        sendkeyToElement(driver, AdminPostCategoryPageUI.DYNAMIC_DETAIL_CATEGORY_INPUT, valuedDetailCategory, labelName);
    }

    @Step("Enter into parent Category with value is {0}")
    public void enterIntoParentCategory(String valueParentCategory) {
        waitForElementVisible(driver, AdminPostCategoryPageUI.DYNAMIC_PARENT_CATEGORY_DROPDOWN);
        selectItemInDefaultDropdown(driver, AdminPostCategoryPageUI.DYNAMIC_PARENT_CATEGORY_DROPDOWN, valueParentCategory);
    }

    @Step("Enter into description Category with value is  {0}")
    public void enterIntoDescriptionCategory(String valueDescriptionCategory) {
        waitForElementVisible(driver, AdminPostCategoryPageUI.DYNAMIC_DESCRIPTION_CATEGORY_TEXTAREA);
        sendkeyToElement(driver, AdminPostCategoryPageUI.DYNAMIC_DESCRIPTION_CATEGORY_TEXTAREA, valueDescriptionCategory);
    }

    @Step("Click to Add new Category")
    public void clickToAddNewCategory() {
        waitForElementClickable(driver, AdminPostCategoryPageUI.ADD_NEW_CATEGORY_BUTTON);
        clickToElement(driver, AdminPostCategoryPageUI.ADD_NEW_CATEGORY_BUTTON);
    }

    @Step("Verify category info for {0} ")
    public boolean isCategoryInforDisplayedWithColumnName(String labelColumn, String value) {
        waitForElementVisible(driver, AdminPostCategoryPageUI.VALUE_CATEGORY_BY_NAME_COLUMN, labelColumn, value);
        return isElementDisplayed(driver, AdminPostCategoryPageUI.VALUE_CATEGORY_BY_NAME_COLUMN, labelColumn, value);
    }

    @Step("Verify added message displayed")
    public boolean isAddedmessageDisplayed(String mesage) {
        waitForElementVisible(driver, AdminPostCategoryPageUI.ADDED_MESSAGE, mesage);
        return isElementDisplayed(driver, AdminPostCategoryPageUI.ADDED_MESSAGE, mesage);
    }

    @Step("Verify category info for {0} ")
    public boolean isCategoryInforDisplayedWithSlugComlumnName(String slugCategory) {
        String formatedValue = slugCategory.toLowerCase().replace(' ', '-');
        waitForElementVisible(driver, AdminPostCategoryPageUI.VALUE_CATEGORY_BY_SLUG_COLUMN, formatedValue);
        return isElementDisplayed(driver, AdminPostCategoryPageUI.VALUE_CATEGORY_BY_SLUG_COLUMN, formatedValue);
    }

    @Step("Enter to search textbox")
    public void enterToSearchTextbox(String nameCategory) {
        waitForElementVisible(driver, AdminPostCategoryPageUI.SEARCH_TEXTBOX);
        sendkeyToElement(driver, AdminPostCategoryPageUI.SEARCH_TEXTBOX, nameCategory);
    }

    @Step("click to search category button")
    public void clickToSearchCategoriesButton() {
        waitForElementClickable(driver, AdminPostCategoryPageUI.SEARCH_BUTTON);
        clickToElement(driver, AdminPostCategoryPageUI.SEARCH_BUTTON);
    }

    @Step("Click to category name link")
    public AdminPostUpdateCategoryPO clickToCategoryNameLink(String labelColumn, String value) {
        waitForElementClickable(driver, AdminPostCategoryPageUI.VALUE_CATEGORY_BY_NAME_COLUMN, labelColumn, value);
        clickToElement(driver, AdminPostCategoryPageUI.VALUE_CATEGORY_BY_NAME_COLUMN, labelColumn, value);
        return PageGeneratorManager.getAdminPostUpdateCategoryPage(driver);
    }

    @Step("Check message when no category found")
    public boolean isNoCategoryFoundMessageDisplayed(String message) {
        waitForElementVisible(driver, AdminPostCategoryPageUI.NO_CATEGORY_FOUND, message);
        return isElementDisplayed(driver, AdminPostCategoryPageUI.NO_CATEGORY_FOUND, message);
    }

    @Step("Select Category detail checkbox")
    public void selectCategoryDetailcheckbox(String nameCategory) {
        waitForElementClickable(driver, AdminPostCategoryPageUI.CATEGORY_NAME_CHECKBOX, nameCategory);
        clickToElement(driver, AdminPostCategoryPageUI.CATEGORY_NAME_CHECKBOX, nameCategory);
    }

    @Step("Retrieve Category data to verify with {2}")
    public String getCategoryInforInDB(Connection connection, String categoryName, String columnName) {
        String sql = "SELECT * FROM seleniumpractice.wp_terms where name like '" + categoryName + "' ORDER BY term_id DESC";
        HashMap<String, String> sqlResults = databaseBuilder.getSqlResults(connection, sql);
        return sqlResults.get(columnName);
    }

    @Step("Retrieve total row after delete category")
    public String getTotalRowAfterDelete(Connection connection, String categoryName, String columnName) {
        String sql = "select count(*) as total_rows FROM seleniumpractice.wp_terms where name like'" + categoryName + "'";
        HashMap<String, String> sqlResults = databaseBuilder.getSqlResults(connection, sql);
        return sqlResults.get(columnName);
    }
}
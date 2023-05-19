package pageObjects.admin;

import commons.BasePage;
import commons.DatabaseBuilder;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUI.admin.AdminMediaLibraryPageUI;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

public class AdminMediaLibPO extends BasePage {
    private WebDriver driver;
    DatabaseBuilder databaseBuilder = new DatabaseBuilder();

    public AdminMediaLibPO(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click To Media SubMenu Link")
    public AdminMediaAddNewPO clickToMediaSubMenuLink(String subMenuName) {
        waitForElementClickable(driver, AdminMediaLibraryPageUI.SUB_MENU_IN_POST, subMenuName);
        clickToElement(driver, AdminMediaLibraryPageUI.SUB_MENU_IN_POST, subMenuName);
        return PageGeneratorManager.getAdminMediaAddNewPage(driver);
    }

    @Step("Check image displayed at Lib page with {0}")
    public boolean isImageDisplayedAtLibPage(List<String> imageSubTitle) {
        int count = 0;
        for (int i = 0; i < imageSubTitle.size(); i++) {
            if (!isImageDisplayedAtLibPage(imageSubTitle.get(i).replace(".jpg", ""))) {
                count = count + 1;
            }
        }
        if (count == 0) return true;
        else return false;
    }

    @Step("Check image displayed at Lib page with {0}")
    public boolean isImageDisplayedAtLibPage(String imageSubTitle) {
        return isImageLoaded(driver, AdminMediaLibraryPageUI.IMAGE_SOURCE, imageSubTitle.replace(".jpg", ""));
    }

    @Step("Verify image is added in DB")
    public int verifyImageInDB(Connection connection, List<String> imageSubTitle, String columnName) {
        int count = 0;
        for (String s : imageSubTitle) {
            String sql = "select  count(*) as total_rows FROM seleniumpractice.wp_posts where post_type = 'attachment' and guid like '%" + s + "%' order by ID desc";
            HashMap<String, String> sqlResults = databaseBuilder.getSqlResults(connection, sql);
            count = count + Integer.parseInt(sqlResults.get(columnName));
        }
        return count;
    }

    @Step("Enter {0} into search textbox")
    public void enterIntoSearchTextbox(String imageSubTitle) {
        waitForAllElementVisible(driver, AdminMediaLibraryPageUI.SUB_TITLE_SEARCH_TEXTBOX);
        sendkeyToElement(driver, AdminMediaLibraryPageUI.SUB_TITLE_SEARCH_TEXTBOX, imageSubTitle);
    }

    @Step("Clear search textbox")
    public void clearSearchTextbox() {
        waitForAllElementVisible(driver, AdminMediaLibraryPageUI.SUB_TITLE_SEARCH_TEXTBOX);
        getWebElement(driver, AdminMediaLibraryPageUI.SUB_TITLE_SEARCH_TEXTBOX).clear();
    }

    @Step("Click to bulk select button")
    public void clickToBulkSelectButton() {
        waitForAllElementVisible(driver, AdminMediaLibraryPageUI.BULK_SELECT_BUTTON);
        clickToElement(driver, AdminMediaLibraryPageUI.BULK_SELECT_BUTTON);
    }

    @Step("Select image to delete with {0}")
    public void selectImageToDelete(List<String> imageSubTitle) {
        imageSubTitle.stream().forEach(image->
                clickToElementByJS(driver,AdminMediaLibraryPageUI.IMAGE_SOURCE,image.replace(".jpg","")));
    }

    @Step("Click delete button")
    public void clickToDetele() {
        waitForAllElementVisible(driver, AdminMediaLibraryPageUI.DELETE_MULTIPLE_IMAGE_BUTTON);
        clickToElement(driver, AdminMediaLibraryPageUI.DELETE_MULTIPLE_IMAGE_BUTTON);
    }
}

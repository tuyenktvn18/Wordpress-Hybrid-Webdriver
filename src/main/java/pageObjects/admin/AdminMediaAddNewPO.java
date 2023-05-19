package pageObjects.admin;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUI.admin.AdminMediaAddNewPageUI;

import java.util.ArrayList;
import java.util.List;

public class AdminMediaAddNewPO extends BasePage {

    private WebDriver driver;

    public AdminMediaAddNewPO(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Upload image")
    public void uploadMultipleFile(String... fileNames) {
        uploadMultipleFiles(driver, fileNames);
        for (String file : fileNames) {
            waitForElementVisible(driver, AdminMediaAddNewPageUI.EDIT_BTN_BY_IMG, file.replace(".jpg", ""));
        }
    }

    @Step("Check image title displayed at add new page")
    public boolean isImageTitleDisplayedAtAddNewPage(String... fileNames) {
        int count = 0;
        for (String file : fileNames) {
            if (!isElementDisplayed(driver, AdminMediaAddNewPageUI.EDIT_BTN_BY_IMG, file.replace(".jpg", ""))) {
                count = count + 1;
            }
        }
        if (count == 0) return true;
        else return false;
    }

    @Step("Get image source")
    public List<String> getImageSubTitle() {
        List<String> ImageSubTitle = new ArrayList<>();
        List<WebElement> imageSubTitleList = getListWebElement(driver, AdminMediaAddNewPageUI.IMAGE_SUB_TITLE_LIST);
        imageSubTitleList.stream().forEach(imageSubTitle -> ImageSubTitle.add(imageSubTitle.getText()));
        return ImageSubTitle;
    }

    @Step("Check image displayed")
    public boolean isImageDisplayed(String... fileNames) {
        int count = 0;
        for (String file : fileNames) {
            if (!isImageLoaded(driver, AdminMediaAddNewPageUI.IMAGE_SOURCE, file.replace(".jpg", ""))) {
                count = count + 1;
            }
        }
        if (count == 0) return true;
        else return false;
    }

    @Step("Open media lib page")
    public AdminMediaLibPO openMediaLibPage(String mediaLibUrl) {
        openPageUrl(driver, mediaLibUrl);
        return PageGeneratorManager.getAdminMediaLibPage(driver);
    }
}

package com.wordpress.testCases.admin;

import commons.*;
import dataTestModels.PageModel;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.admin.AdminDashboardPO;
import pageObjects.admin.AdminLoginPO;
import pageObjects.admin.AdminMediaAddNewPO;
import pageObjects.admin.AdminMediaLibPO;

import java.sql.Connection;
import java.util.List;

public class Media_01_CRDS extends BaseTest {

    private WebDriver driver;
    private Connection connection;

    @Parameters({"browserName", "urlAdmin", "urlUser", "envName", "ipAddress", "portNumber", "osName", "osVersion"})
    @BeforeClass
    public void beforeClass(@Optional("chrome") String browserName, String adminUrl, String endUserUrl, @Optional("local") String envName, @Optional("192.168.1.13") String ipAddress, @Optional("4444") String portNumber, @Optional("Windows") String osName, @Optional("10") String osVersion) {
        this.adminUrl = adminUrl;
        this.endUserUrl = endUserUrl;
        pageData = DataObjectBuilder.buildDataObjectBuilderWithFileName("Page.json", PageModel.class);
        connection = DatabaseBuilder.getConnection();
        driver = getBrowserDriver(browserName, adminUrl, envName, ipAddress, portNumber, osName, osVersion);
        adminLoginPage = PageGeneratorManager.getAdminHomePage(driver);

        adminDashboardPage = adminLoginPage.loginIntoAdminPage(GlobalConstants.getGlobalConstants().getAdminUserName(), GlobalConstants.getGlobalConstants().getAdminPassword());
    }

    @Test
    public void Media_01_Create_New() {
        adminMediaLibPage = adminDashboardPage.clickToMediaMenuLink();
        mediaLibUrl = adminMediaLibPage.getPageUrl(driver);
        adminMediaAddNewPage = adminMediaLibPage.clickToMediaSubMenuLink("Add New");

        adminMediaAddNewPage.uploadMultipleFile(imageForTest1, imageForTest2);

        verifyTrue(adminMediaAddNewPage.isImageDisplayed(imageForTest1, imageForTest2));
        verifyTrue(adminMediaAddNewPage.isImageTitleDisplayedAtAddNewPage(imageForTest1, imageForTest2));

        imageSubTitle = adminMediaAddNewPage.getImageSubTitle();
        adminMediaLibPage = adminMediaAddNewPage.openMediaLibPage(mediaLibUrl);

        verifyTrue(adminMediaLibPage.isImageDisplayedAtLibPage(imageSubTitle));
        verifyEquals(adminMediaLibPage.verifyImageInDB(connection, imageSubTitle, "total_rows"),imageSubTitle.size());
    }

    @Test
    public void Media_02_Search_And_View() {
        adminMediaLibPage.refreshCurrentPage(driver);
        adminMediaLibPage.enterIntoSearchTextbox(imageSubTitle.get(0));

        verifyTrue(adminMediaLibPage.isImageDisplayedAtLibPage(imageSubTitle.get(0)));
    }

    @Test
    public void Media_03_Delete() {
        adminMediaLibPage.clearSearchTextbox();
        adminMediaLibPage.refreshCurrentPage(driver);

        adminMediaLibPage.clickToBulkSelectButton();
        adminMediaLibPage.selectImageToDelete(imageSubTitle);
        adminMediaLibPage.clickToDetele();
        adminMediaLibPage.acceptAlert(driver);

        verifyFalse(adminMediaLibPage.isImageDisplayedAtLibPage(imageSubTitle));

    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserAndDriver();
    }


    String adminUrl, endUserUrl, mediaLibUrl;
    String imageForTest1 = "imageForTest1.jpg";
    String imageForTest2 = "imageForTest2.jpg";
    private List<String> imageSubTitle;
    private PageModel pageData;
    private AdminLoginPO adminLoginPage;
    private AdminDashboardPO adminDashboardPage;
    private AdminMediaLibPO adminMediaLibPage;
    private AdminMediaAddNewPO adminMediaAddNewPage;
}

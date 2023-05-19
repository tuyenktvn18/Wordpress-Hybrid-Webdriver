package com.wordpress.testCases.admin;

import commons.*;
import dataTestModels.PageModel;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.admin.AdminDashboardPO;
import pageObjects.admin.AdminLoginPO;
import pageObjects.admin.AdminPageAddNewPO;
import pageObjects.admin.AdminPageSearchPO;
import pageObjects.user.UsePageDetailPO;
import pageObjects.user.UserHomePO;

import java.sql.Connection;

public class Page_01_CRUDS extends BaseTest {

    @Parameters({ "browserName", "urlAdmin", "urlUser", "envName", "ipAddress", "portNumber", "osName", "osVersion" })
    @BeforeClass
    public void beforeClass(@Optional("chrome") String browserName, String adminUrl, String endUserUrl, @Optional("local") String envName, @Optional("192.168.1.13") String ipAddress, @Optional("4444") String portNumber, @Optional("Windows") String osName, @Optional("10") String osVersion) {
        this.adminUrl = adminUrl;
        this.endUserUrl=endUserUrl;

        pageData = DataObjectBuilder.buildDataObjectBuilderWithFileName("Page.json", PageModel.class);
        connection = DatabaseBuilder.getConnection();
        driver = getBrowserDriver(browserName, adminUrl, envName, ipAddress, portNumber, osName, osVersion );
        adminLoginPage = PageGeneratorManager.getAdminHomePage(driver);

        adminDashboardPage = adminLoginPage.loginIntoAdminPage(GlobalConstants.getGlobalConstants().getAdminUserName(), GlobalConstants.getGlobalConstants().getAdminPassword());
    }

    @Test
    public void Page_01_Create_New() {
        adminPageSearchPage = adminDashboardPage.clickToPagesMenuLink();
        searchPageUrl = adminPageSearchPage.getPageUrl(driver);

        adminPageAddNewPage = adminPageSearchPage.clickToAddNewButton();

        adminPageAddNewPage.enterIntoPageTitleAndBody(pageData.getPageTitle(), pageData.getPageBody());

        adminPageAddNewPage.clickToUpdateOrPublishButton();
        adminPageAddNewPage.clickToConfirmPublishButton();

        verifyTrue(adminPageAddNewPage.isPostPublishMessageDisplayed("Page published."));
    }

    @Test
    public void Page_02_Search_And_View() {
        adminPageSearchPage = adminPageAddNewPage.openSearchPostPage(searchPageUrl);

        adminPageSearchPage.enterToSearchTextbox(pageData.getPageTitle());
        adminPageSearchPage.clickToSearchPageButton();

        verifyTrue(adminPageSearchPage.isPageSearchTableDisplayed("Title", pageData.getPageTitle()));
        verifyTrue(adminPageSearchPage.isPageSearchTableDisplayed("Author", GlobalConstants.getGlobalConstants().getAdminUserName()));

        userHomePage = adminPageSearchPage.openEndUserSite(driver, endUserUrl);

        verifyTrue(userHomePage.isPageInforDisplayedWithPageTitleInMenu(pageData.getPageTitle()));

        userPageDetailPage = userHomePage.clickToPageTitle(pageData.getPageTitle());

        verifyTrue(userPageDetailPage.isPageInforDisplayedWithPageTitle(pageData.getPageTitle()));
        verifyTrue(userPageDetailPage.isPageInforDisplayedWithPageBody(pageData.getPageBody()));
    }

    @Test
    public void Page_03_Edit() {
        adminLoginPage = userPageDetailPage.openAdminSite(driver, adminUrl);

        adminLoginPage.inputToUsernameTextbox(GlobalConstants.getGlobalConstants().getAdminUserName());
        adminLoginPage.inputToPasswordTextbox(GlobalConstants.getGlobalConstants().getAdminPassword());
        adminLoginPage.clickToLoginButton();

        adminPageSearchPage = adminDashboardPage.clickToPagesMenuLink();

        adminPageSearchPage.enterToSearchTextbox(pageData.getPageTitle());

        adminPageSearchPage.clickToSearchPageButton();

        adminPageSearchPage.clickToPageTitleLink("Title", pageData.getPageTitle());

        adminPageAddNewPage.enterIntoPageTitle(pageData.getEditPageTitle());

        adminPageAddNewPage.enterIntoPageBody(pageData.getEditPageBody());

        adminPageAddNewPage.clickToUpdateOrPublishButton();

        verifyTrue(adminPageAddNewPage.isPostPublishMessageDisplayed("Page updated."));

        userHomePage = adminPageAddNewPage.openEndUserSite(driver, endUserUrl);

        verifyTrue(userHomePage.isPageInforDisplayedWithPageTitleInMenu(pageData.getEditPageTitle()));

        userPageDetailPage = userHomePage.clickToPageTitle(pageData.getEditPageTitle());

        verifyTrue(userPageDetailPage.isPageInforDisplayedWithPageTitle(pageData.getEditPageTitle()));
        verifyTrue(userPageDetailPage.isPageInforDisplayedWithPageBody(pageData.getEditPageBody()));
    }

    @Test
    public void Page_04_Delete() {
        adminLoginPage = userPageDetailPage.openAdminSite(driver, adminUrl);

        adminLoginPage.inputToUsernameTextbox(GlobalConstants.getGlobalConstants().getAdminUserName());
        adminLoginPage.inputToPasswordTextbox(GlobalConstants.getGlobalConstants().getAdminPassword());
        adminLoginPage.clickToLoginButton();

        adminPageSearchPage = adminDashboardPage.clickToPagesMenuLink();

        adminPageSearchPage.enterToSearchTextbox(pageData.getEditPageTitle());

        adminPageSearchPage.clickToSearchPageButton();

        adminPageSearchPage.selectPageDetailcheckbox(pageData.getEditPageTitle());

        adminPageSearchPage.selectItemActionDropdown("Move to Trash");

        adminPageSearchPage.clickToApplyButton();

        verifyTrue(adminPageSearchPage.isMoveToTrashMessageDisplayed("1 page moved to the Trash. "));

        adminPageSearchPage.enterToSearchTextbox(pageData.getEditPageTitle());

        adminPageSearchPage.clickToSearchPageButton();

        verifyTrue(adminPageSearchPage.isNoPageFoundMessageDisplayed("No pages found."));

        userHomePage = adminPageSearchPage.openEndUserSite(driver, endUserUrl);

        verifyTrue(userHomePage.isPageInforUndisplayedWithPageTitle(pageData.getEditPageTitle()));

    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserAndDriver();
    }

    private WebDriver driver;
    String adminUrl, endUserUrl, searchPageUrl;
    Connection connection;
    PageModel pageData;
    AdminLoginPO adminLoginPage;
    AdminDashboardPO adminDashboardPage;
    AdminPageSearchPO adminPageSearchPage;
    AdminPageAddNewPO adminPageAddNewPage;
    UserHomePO userHomePage;
    UsePageDetailPO userPageDetailPage;
}

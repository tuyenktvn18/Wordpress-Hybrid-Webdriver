package com.wordpress.testCases.admin;

import commons.*;
import dataTestModels.PostModel;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.admin.AdminDashboardPO;
import pageObjects.admin.AdminLoginPO;
import pageObjects.admin.AdminPostAddNewPO;
import pageObjects.admin.AdminPostSearchPO;
import pageObjects.user.UserHomePO;
import pageObjects.user.UserPostDetailPO;

import java.sql.Connection;

public class Post_01_CRUDS extends BaseTest {

    @Parameters({ "browserName", "urlAdmin", "urlUser", "envName", "ipAddress", "portNumber", "osName", "osVersion" })
    @BeforeClass
    public void beforeClass(@Optional("chrome") String browserName, String adminUrl, String endUserUrl, @Optional("local") String envName, @Optional("192.168.1.13") String ipAddress, @Optional("4444") String portNumber, @Optional("Windows") String osName, @Optional("10") String osVersion) {
        this.adminUrl = adminUrl;
        this.endUserUrl = endUserUrl;

        postData = DataObjectBuilder.buildDataObjectBuilderWithFileName("Post.json", PostModel.class);
        connection = DatabaseBuilder.getConnection();
        driver = getBrowserDriver(browserName, adminUrl, envName, ipAddress, portNumber, osName, osVersion );
        adminLoginPage = PageGeneratorManager.getAdminHomePage(driver);

        showbrowserConsoleLogs(driver);

        adminDashboardPage = adminLoginPage.loginIntoAdminPage(GlobalConstants.getGlobalConstants().getAdminUserName(), GlobalConstants.getGlobalConstants().getAdminPassword());
        showbrowserConsoleLogs(driver);
    }

    @Test
    public void Post_01_Create_New() {
        adminPostSearchPage = adminDashboardPage.clickToPostMenuLink();
        searchPostUrl = adminPostSearchPage.getPageUrl(driver);

        adminPostAddNewPage = adminPostSearchPage.clickToAddNewButton();
        showbrowserConsoleLogs(driver);

        adminPostAddNewPage.enterIntoPostTitle(postData.getPostTitle());

        adminPostAddNewPage.enterIntoPostBody(postData.getPostBody());

        adminPostAddNewPage.clickToUpdateOrPublishButton();

        adminPostAddNewPage.clickToConfirmPublishButton();

        verifyTrue(adminPostAddNewPage.isPostPublishMessageDisplayed("Post published."));
    }

    @Test
    public void Post_02_Search_And_View() {
        adminPostSearchPage = adminPostAddNewPage.openSearchPostPage(searchPostUrl);

        adminPostSearchPage.enterToSearchTextbox(postData.getPostTitle());

        adminPostSearchPage.clickToSearchPostButton();

        verifyTrue(adminPostSearchPage.isPostSearchTableDisplayed("Title", postData.getPostTitle()));
        verifyTrue(adminPostSearchPage.isPostSearchTableDisplayed("Author", GlobalConstants.getGlobalConstants().getAdminUserName()));

        userHomePage = adminPostSearchPage.openEndUserSite(driver, endUserUrl);

        verifyTrue(userHomePage.isPostInforDisplayedWithPostTitle(postData.getPostTitle()));
        verifyTrue(userHomePage.isPostInforDisplayedWithPostBody(postData.getPostTitle(), postData.getPostBody()));
        verifyTrue(userHomePage.isPostInforDisplayedWithPostAuthor(postData.getPostTitle(), GlobalConstants.getGlobalConstants().getAdminUserName()));
        verifyTrue(userHomePage.isPostInforDisplayedWithPostTime(postData.getPostTitle(), currentDay));

        userPostDetailPage = userHomePage.clickToPostTitle(postData.getPostTitle());

        verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostTitle(postData.getPostTitle()));
        verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostBody(postData.getPostTitle(), postData.getPostBody()));
        verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostAuthor(postData.getPostTitle(), GlobalConstants.getGlobalConstants().getAdminUserName()));
        verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostTime(postData.getPostTitle(), currentDay));
    }

    @Test
    public void Post_03_Edit() {
        adminLoginPage = userPostDetailPage.openAdminSite(driver, adminUrl);

        adminLoginPage.inputToUsernameTextbox(GlobalConstants.getGlobalConstants().getAdminUserName());
        adminLoginPage.inputToPasswordTextbox(GlobalConstants.getGlobalConstants().getAdminPassword());
        adminLoginPage.clickToLoginButton();

        adminDashboardPage = PageGeneratorManager.getAdminDashboardPage(driver);
        adminPostSearchPage = adminDashboardPage.clickToPostMenuLink();

        adminPostSearchPage.enterToSearchTextbox(postData.getPostTitle());

        adminPostSearchPage.clickToSearchPostButton();

        adminPostSearchPage.clickToPostTitleLink("Title", postData.getPostTitle());

        adminPostAddNewPage.enterIntoPostTitle(postData.getEditPostTitle());

        adminPostAddNewPage.enterIntoEditPostBody(postData.getEditPostBody());

        adminPostAddNewPage.clickToUpdateOrPublishButton();

        verifyTrue(adminPostAddNewPage.isPostPublishMessageDisplayed("Post updated."));

        userHomePage = adminPostSearchPage.openEndUserSite(driver, endUserUrl);

        verifyTrue(userHomePage.isPostInforDisplayedWithPostTitle(postData.getEditPostTitle()));
        verifyTrue(userHomePage.isPostInforDisplayedWithPostBody(postData.getEditPostTitle(), postData.getEditPostBody()));
        verifyTrue(userHomePage.isPostInforDisplayedWithPostAuthor(postData.getEditPostTitle(), GlobalConstants.getGlobalConstants().getAdminUserName()));
        verifyTrue(userHomePage.isPostInforDisplayedWithPostTime(postData.getEditPostTitle(), currentDay));

        userPostDetailPage = userHomePage.clickToPostTitle(postData.getEditPostTitle());

        verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostTitle(postData.getEditPostTitle()));
        verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostBody(postData.getEditPostTitle(), postData.getEditPostBody()));
        verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostAuthor(postData.getEditPostTitle(), GlobalConstants.getGlobalConstants().getAdminUserName()));
        verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostTime(postData.getEditPostTitle(), currentDay));
    }

    @Test
    public void Post_04_Delete() {
        adminLoginPage = userPostDetailPage.openAdminSite(driver, adminUrl);

        adminLoginPage.inputToUsernameTextbox(GlobalConstants.getGlobalConstants().getAdminUserName());
        adminLoginPage.inputToPasswordTextbox(GlobalConstants.getGlobalConstants().getAdminPassword());
        adminLoginPage.clickToLoginButton();

        adminDashboardPage = PageGeneratorManager.getAdminDashboardPage(driver);
        adminPostSearchPage = adminDashboardPage.clickToPostMenuLink();

        adminPostSearchPage.enterToSearchTextbox(postData.getEditPostTitle());

        adminPostSearchPage.clickToSearchPostButton();

        adminPostSearchPage.selectPostDetailcheckbox(postData.getEditPostTitle());

        adminPostSearchPage.selectItemActionDropdown("Move to Trash");

        adminPostSearchPage.clickToApplyButton();

        verifyTrue(adminPostSearchPage.isMoveToTrashMessageDisplayed("1 post moved to the Trash. "));

        adminPostSearchPage.enterToSearchTextbox(postData.getEditPostTitle());

        adminPostSearchPage.clickToSearchPostButton();

        verifyTrue(adminPostSearchPage.isNoPostFoundMessageDisplayed("No posts found."));

        userHomePage = adminPostSearchPage.openEndUserSite(driver, endUserUrl);

        verifyTrue(userHomePage.isPostInforUndisplayedWithPostTitle(postData.getEditPostTitle()));
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserAndDriver();
    }

    String adminUrl, endUserUrl, searchPostUrl;
    private WebDriver driver;
    String currentDay = getToday();
    PostModel postData;
    Connection connection;
    AdminLoginPO adminLoginPage;
    AdminDashboardPO adminDashboardPage;
    AdminPostSearchPO adminPostSearchPage;
    AdminPostAddNewPO adminPostAddNewPage;
    UserHomePO userHomePage;
    UserPostDetailPO userPostDetailPage;
}

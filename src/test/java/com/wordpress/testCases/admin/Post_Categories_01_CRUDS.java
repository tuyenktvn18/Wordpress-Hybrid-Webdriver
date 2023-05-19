package com.wordpress.testCases.admin;

import commons.*;
import dataTestModels.CategoryModel;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.admin.*;

import java.sql.Connection;


public class Post_Categories_01_CRUDS extends BaseTest {

    @Parameters({ "browserName", "urlAdmin", "urlUser", "envName", "ipAddress", "portNumber", "osName", "osVersion" })
    @BeforeClass
    public void beforeClass(@Optional("chrome") String browserName, String adminUrl, String endUserUrl, @Optional("local") String envName, @Optional("192.168.1.13") String ipAddress, @Optional("4444") String portNumber, @Optional("Windows") String osName, @Optional("10") String osVersion) {
        this.adminUrl = adminUrl;
        this.endUserUrl = endUserUrl;

        categoryData = DataObjectBuilder.buildDataObjectBuilderWithFileName("Catigories.json", CategoryModel.class);
        connection = DatabaseBuilder.getConnection();
        driver = getBrowserDriver(browserName, adminUrl, envName, ipAddress, portNumber, osName, osVersion );
        adminLoginPage = PageGeneratorManager.getAdminHomePage(driver);

        adminDashboardPage = adminLoginPage.loginIntoAdminPage(GlobalConstants.getGlobalConstants().getAdminUserName(), GlobalConstants.getGlobalConstants().getAdminPassword());
    }


    @Test
    @Description("Add new post category")
    public void Category_01_Create_New() {

        adminPostSearchPage = adminDashboardPage.clickToPostMenuLink();
        adminPostCategoryPage = adminPostSearchPage.clickToPostSubMenuLink("Categories");

        adminPostCategoryPage.enterIntoDetailCategory("Name", categoryData.getNameCategory());
        adminPostCategoryPage.enterIntoDetailCategory("Slug", categoryData.getSlugCategory());
        adminPostCategoryPage.enterIntoParentCategory(categoryData.getParentCategory());
        adminPostCategoryPage.enterIntoDescriptionCategory(categoryData.getDescriptionCategory());

        adminPostCategoryPage.clickToAddNewCategory();

        verifyTrue(adminPostCategoryPage.isAddedmessageDisplayed("Category added."));
        verifyTrue(adminPostCategoryPage.isCategoryInforDisplayedWithColumnName("Name", categoryData.getNameCategory()));
        verifyTrue(adminPostCategoryPage.isCategoryInforDisplayedWithColumnName("Description", categoryData.getDescriptionCategory()));
        verifyTrue(adminPostCategoryPage.isCategoryInforDisplayedWithSlugComlumnName(categoryData.getSlugCategory()));

        verifyEquals(adminPostCategoryPage.getCategoryInforInDB(connection,categoryData.getNameCategory(), "name"),categoryData.getNameCategory());
        verifyEquals(adminPostCategoryPage.getCategoryInforInDB(connection,categoryData.getNameCategory(), "slug"),categoryData.getSlugCategory().toLowerCase().replace(' ', '-'));
    }

    @Test
    public void Category_02_Search_And_View() {
        adminPostCategoryPage.refreshCurrentPage(driver);

        adminPostCategoryPage.enterToSearchTextbox(categoryData.getNameCategory());

        adminPostCategoryPage.clickToSearchCategoriesButton();

        verifyTrue(adminPostCategoryPage.isCategoryInforDisplayedWithColumnName("Name", categoryData.getNameCategory()));
        verifyTrue(adminPostCategoryPage.isCategoryInforDisplayedWithColumnName("Description", categoryData.getDescriptionCategory()));
        verifyTrue(adminPostCategoryPage.isCategoryInforDisplayedWithSlugComlumnName(categoryData.getSlugCategory()));

    }

    @Test
    public void Category_03_Edit() {
        adminPostCategoryPage.refreshCurrentPage(driver);
        adminPostCategoryPage.enterToSearchTextbox(categoryData.getNameCategory());

        adminPostCategoryPage.clickToSearchCategoriesButton();

        adminPostUpdateCategoryPage = adminPostCategoryPage.clickToCategoryNameLink("Name", categoryData.getNameCategory());

        adminPostUpdateCategoryPage.enterIntoDetailCategoryByInput("name", categoryData.getEditNameCategory());
        adminPostUpdateCategoryPage.enterIntoDetailCategoryByInput("slug", categoryData.getEditSlugCategory());
        adminPostUpdateCategoryPage.enterIntoDetailCategoryBySelect("parent", categoryData.getEditParentCategory());
        adminPostUpdateCategoryPage.enterIntoDetailCategoryByInput("description", categoryData.getEditDescriptionCategory());

        adminPostUpdateCategoryPage.clickToUpdateButton();

        verifyTrue(adminPostUpdateCategoryPage.isCategoryUpdatedMessageDisplayed("Category updated."));

        adminPostCategoryPage = adminPostUpdateCategoryPage.clickToGoTocategoriesLink();

        adminPostCategoryPage.enterToSearchTextbox(categoryData.getEditNameCategory());
        adminPostCategoryPage.clickToSearchCategoriesButton();

        verifyTrue(adminPostCategoryPage.isCategoryInforDisplayedWithColumnName("Name", categoryData.getEditNameCategory()));
        verifyTrue(adminPostCategoryPage.isCategoryInforDisplayedWithColumnName("Description", categoryData.getEditDescriptionCategory()));
        verifyTrue(adminPostCategoryPage.isCategoryInforDisplayedWithSlugComlumnName(categoryData.getEditSlugCategory()));

        verifyEquals(adminPostCategoryPage.getCategoryInforInDB(connection,categoryData.getEditNameCategory(), "name"),categoryData.getEditNameCategory());
        verifyEquals(adminPostCategoryPage.getCategoryInforInDB(connection,categoryData.getEditNameCategory(), "slug"),categoryData.getEditSlugCategory().toLowerCase().replace(' ', '-'));
    }

    @Test
    public void Category_04_Delete() {
        adminPostCategoryPage.refreshCurrentPage(driver);
        adminPostCategoryPage.enterToSearchTextbox(categoryData.getEditNameCategory());

        adminPostCategoryPage.clickToSearchCategoriesButton();

        adminPostCategoryPage.selectCategoryDetailcheckbox(categoryData.getEditNameCategory());

        adminPostCategoryPage.selectItemActionDropdown("Delete");

        adminPostCategoryPage.clickToApplyButton();

        verifyTrue(adminPostCategoryPage.isMoveToTrashMessageDisplayed("Categories deleted."));

        adminPostCategoryPage.enterToSearchTextbox(categoryData.getEditNameCategory());

        adminPostCategoryPage.clickToSearchPostButton();

        verifyTrue(adminPostCategoryPage.isNoCategoryFoundMessageDisplayed("No categories found."));
        verifyEquals(adminPostCategoryPage.getTotalRowAfterDelete(connection,categoryData.getEditNameCategory(), "total_rows"),"0");
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserAndDriver();
        closeConectionDB();
    }

    String adminUrl, endUserUrl;
    private WebDriver driver;
    CategoryModel categoryData;
    Connection connection;
    AdminLoginPO adminLoginPage;
    AdminDashboardPO adminDashboardPage;
    AdminPostSearchPO adminPostSearchPage;
    AdminPostCategoryPO adminPostCategoryPage;
    AdminPostUpdateCategoryPO adminPostUpdateCategoryPage;
}

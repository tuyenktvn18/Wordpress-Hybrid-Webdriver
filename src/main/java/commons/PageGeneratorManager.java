package commons;

import org.openqa.selenium.WebDriver;
import pageObjects.admin.*;
import pageObjects.user.UsePageDetailPO;
import pageObjects.user.UserHomePO;
import pageObjects.user.UserPostDetailPO;
import pageObjects.user.UserPostSearchPO;

public class PageGeneratorManager {
    public static AdminLoginPO getAdminHomePage(WebDriver driver) {
        return new AdminLoginPO(driver);
    }

    public static AdminDashboardPO getAdminDashboardPage(WebDriver driver) {
        return new AdminDashboardPO(driver);
    }

    public static AdminLoginPO getAdminLoginPage(WebDriver driver) {
        return new AdminLoginPO(driver);
    }

    public static AdminPostSearchPO getAdminPostSearchPage(WebDriver driver) {
        return new AdminPostSearchPO(driver);
    }

    public static AdminPostAddNewPO getAdminAddNewPage(WebDriver driver) {
        return new AdminPostAddNewPO(driver);
    }

    public static UserHomePO getUserHomePage(WebDriver driver) {
        return new UserHomePO(driver);
    }

    public static UserPostDetailPO getUserPostDetailPage(WebDriver driver) {
        return new UserPostDetailPO(driver);
    }

    public static UserPostSearchPO getUserPostSearchPage(WebDriver driver) {
        return new UserPostSearchPO(driver);
    }

    public static AdminPostCategoryPO getAdminPostCategoryPage(WebDriver driver) {
        return new AdminPostCategoryPO(driver);
    }

    public static AdminPostUpdateCategoryPO getAdminPostUpdateCategoryPage(WebDriver driver) {
        return new AdminPostUpdateCategoryPO(driver);
    }

    public static AdminPageSearchPO getAdminPageSearchPage(WebDriver driver) {
        return new AdminPageSearchPO(driver);
    }

    public static UsePageDetailPO getUserPageDetailPage(WebDriver driver) {
        return new UsePageDetailPO(driver);
    }

    public static AdminMediaLibPO getAdminMediaLibPage(WebDriver driver) {
        return new AdminMediaLibPO(driver);
    }

    public static AdminMediaAddNewPO getAdminMediaAddNewPage(WebDriver driver) {
        return new AdminMediaAddNewPO(driver);
    }

}

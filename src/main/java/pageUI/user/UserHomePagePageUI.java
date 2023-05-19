package pageUI.user;

public class UserHomePagePageUI {
    public final static String POST_TITLE = "xpath=//article//h2/a[text()='%s']";
    public final static String POST_TIME = "xpath=//article//h2/a[text()='%s']/..//following-sibling::div//a/time[text()='%s' and contains(@class,'published')]";
    public final static String POST_BY = "xpath=//article//h2/a[text()='%s']/..//following-sibling::div//span//a[text()='%s']";
    public final static String POST_BODY = "xpath=//article//h2/a[text()='%s']/../../following-sibling::div/p[text()='%s']";

    public final static String SEARCH_TEXTBOX = "xpath=//label[text()='Search']//following-sibling::div//input";
    public final static String SEARCH_BUTTON = "xpath=//label[text()='Search']//following-sibling::div//button";

    public final static String PAGE_TITLE_MENU = "xpath=//ul[@id='primary-menu']//li//a[text()='%s']";
    public final static String PAGE_TITLE = "xpath=//h1[@class='entry-title' and text()='%s']";
    public final static String PAGE_BODY = "xpath=//header[@class='entry-header']//following-sibling::div//p[text()='%s']";
}

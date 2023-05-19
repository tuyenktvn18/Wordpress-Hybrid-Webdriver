package pageUI.admin;

public class AdminPostSearchPageUI {
    public final static String ADD_NEW_BUTTON = "xpath=//h1[contains(.,'Posts')]//following-sibling::a[text()='Add New']";
    public final static String POST_SEARCH_TEXTBOX ="xpath=//input[@id='post-search-input']";
    public final static String POST_SEARCH_BUTTON ="xpath=//input[@id='search-submit']";

    public final static String DYNAMIC_VALUE_IN_COLUMN_BY_NAME = "xpath=//table[contains(@class,'table-view-list posts')]//tbody//tr[1]//*[@data-colname='%s']//a[text()='%s']";
    public final static String POST_TITLE_CHECKBOX ="xpath=//label[contains(text(),'%s')]//following-sibling::input";
    public final static String ACTION_DROPDOWN ="xpath=//select[@id='bulk-action-selector-top']";
    public final static String APPLY_BUTTON ="xpath=//input[@id='doaction']";
    public final static String MOVE_TO_TRASH_MESSAGE ="xpath=//div[@id='message']/p[contains(text(),'%s')]";
    public final static String NO_POSTS_FOUND_MESSAGE ="xpath=//table[contains(@class,'table-view-list posts')]//tbody//tr/td[text()='%s']";

    public static final String SUB_MENU_IN_POST = "xpath=//li[@id='menu-posts']//li//a[text()='%s']";
}
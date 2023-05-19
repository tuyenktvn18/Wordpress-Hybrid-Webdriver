package pageUI.admin;

public class AdminPageSearchPageUI {
    public final static String ADD_NEW_BUTTON = "xpath=//h1[contains(.,'Pages')]//following-sibling::a[text()='Add New']";
    public final static String PAGE_SEARCH_TEXTBOX ="xpath=//input[@id='post-search-input']";
    public final static String PAGE_SEARCH_BUTTON ="xpath=//input[@id='search-submit']";

    public final static String DYNAMIC_VALUE_IN_COLUMN_BY_NAME = "xpath=//table[contains(@class,'table-view-list pages')]//tbody//tr[1]//*[@data-colname='%s']//a[contains(text(),'%s')]";
    public final static String PAGE_TITLE_CHECKBOX ="xpath=//label[contains(text(),'%s')]//following-sibling::input";
    public final static String ACTION_DROPDOWN ="xpath=//select[@id='bulk-action-selector-top']";
    public final static String APPLY_BUTTON ="xpath=//input[@id='doaction']";
    public final static String MOVE_TO_TRASH_MESSAGE ="xpath=//div[@id='message']/p[contains(text(),'%s')]";
    public final static String NO_PAGE_FOUND_MESSAGE ="xpath=//table[contains(@class,'table-view-list pages')]//tbody//tr/td[text()='%s']";

    public static final String SUB_MENU_IN_POST = "xpath=//li[@id='menu-posts']//li//a[text()='%s']";
}

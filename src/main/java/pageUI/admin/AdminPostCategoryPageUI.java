package pageUI.admin;

public class AdminPostCategoryPageUI {
    public final static String DYNAMIC_DETAIL_CATEGORY_INPUT ="xpath=//label[text()='%s']//following-sibling::input";
    public final static String DYNAMIC_PARENT_CATEGORY_DROPDOWN ="xpath=//label[text()='Parent Category']//following-sibling::select";
    public final static String DYNAMIC_DESCRIPTION_CATEGORY_TEXTAREA ="xpath=//label[text()='Description']//following-sibling::textarea";
    public static final String ADD_NEW_CATEGORY_BUTTON = "xpath=//input[@value='Add New Category']";
    public static final String CATEGORY_NAME_CHECKBOX = "xpath=//label[contains(text(),'%s')]//following-sibling::input";
    public static final String ADDED_MESSAGE = "xpath=//div[contains(@id,'response')]//p[text()='%s']";
    public static final String DELETED_MESSAGE = "xpath=//div[@id='message']//p[text()='%s']";

    public static final String SEARCH_TEXTBOX ="xpath=//input[@type='search']";
    public static final String SEARCH_BUTTON ="xpath=//input[@value='Search Categories']";

    public static final String VALUE_CATEGORY_BY_NAME_COLUMN ="xpath=//table[contains(@class,'table-view-list tags')]//tr//td[@data-colname= '%s']//*[contains(text(),'%s')]";
    public static final String VALUE_CATEGORY_BY_SLUG_COLUMN ="xpath=//table[contains(@class,'table-view-list tags')]//tr//td[@data-colname= 'Slug' and contains(text(),'%s')]";
    public static final String NO_CATEGORY_FOUND = "xpath=//tbody//tr[@class='no-items']//td[text()='%s']";



}

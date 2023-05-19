package pageUI.admin;

public class AdminMediaLibraryPageUI {
    public final static String ADD_NEW_BUTTON = "xpath=//h1[contains(.,'Media Library')]//following-sibling::a[text()='Add New']";
    public final static String SUB_MENU_IN_POST = "xpath=//li[@id='menu-media']//a[text()='%s']";

    public final static String IMAGE_SOURCE = "xpath=//li[@class='attachment save-ready']//img[contains(@src,'%s')]";
//    public final static String SELECT_IMAGE = "xpath=//li[@class='attachment save-ready']//img[contains(@src,'%s')]/..";
    public final static String SUB_TITLE_SEARCH_TEXTBOX = "xpath=//input[@id='media-search-input']";
    public final static String BULK_SELECT_BUTTON = "xpath=//button[text()='Bulk select']";
    public final static String DELETE_MULTIPLE_IMAGE_BUTTON = "xpath=//button[contains(@class,'delete-selected-button')]";

}

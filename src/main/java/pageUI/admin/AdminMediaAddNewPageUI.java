package pageUI.admin;

public class AdminMediaAddNewPageUI {
    public static final String IMAGE_TITLE = "xpath=//div[@class='attachment-details']//strong[text()='%s']";
    public static final String IMAGE_TITLE_LIST = "xpath=//div[@class='attachment-details']//strong";
    public static final String IMAGE_SUB_TITLE = "xpath=//strong[text()='%s']/..//following-sibling::span";
    public static final String IMAGE_SUB_TITLE_LIST = "xpath=//div[@class='attachment-details']//span[contains(@class,'subtitle')]";
    public static final String IMAGE_SOURCE = "xpath=//strong[text()='%s']/../..//preceding-sibling::img";

    public static final String EDIT_BTN_BY_IMG = "xpath=//div[@class='attachment-details']//strong[text()='%s']";

    public static final String TITLE_UPDATE = "xpath=//label[text()='Title']//following-sibling::input";
    public static final String COSE_DIALOG = "xpath=//span[text()='Close dialog']";
}

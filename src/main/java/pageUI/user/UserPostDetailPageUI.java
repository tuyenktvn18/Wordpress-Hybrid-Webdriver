package pageUI.user;

public class UserPostDetailPageUI {
    public final static String POST_TITLE = "xpath=//article//h1[text()='%s']";
    public final static String POST_TIME = "xpath=//article//h1[text()='%s']/..//following-sibling::div//a/time[text()='%s' and contains(@class,'published')]";
    public final static String POST_BY = "xpath=//article//h1[text()='%s']/..//following-sibling::div//span//a[text()='%s']";
    public final static String POST_BODY = "xpath=//article//h1[text()='%s']/../following-sibling::div/p[text()='%s']";
}

package dataTestModels;

import commons.BasePage;
import lombok.Getter;

public class PageModel {

    private String uniqueString = String.valueOf(BasePage.getBasePageObject().getRandomNumber());

    @Getter
    NewPage newPage;

    static class NewPage {
        private String pageTitle;
        private String pageBody;
    }

    public String getPageTitle() {
        return newPage.pageTitle + uniqueString;
    }

    public String getPageBody() {
        return newPage.pageBody + uniqueString;
    }

    @Getter
    EditPage editPage;

    static class EditPage {
        String editPageTitle;
        String editPageBody;
    }

    public String getEditPageTitle() {
        return editPage.editPageTitle + uniqueString;
    }

    public String getEditPageBody() {
        return editPage.editPageBody + uniqueString;
    }

}

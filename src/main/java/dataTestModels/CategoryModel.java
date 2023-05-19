package dataTestModels;

import commons.BasePage;
import lombok.Getter;

public class CategoryModel {

    private String uniqueString = String.valueOf(BasePage.getBasePageObject().getRandomNumber());

    @Getter
    NewCategory newCategory;

    static class NewCategory {
        private String nameCategory;
        private String slugCategory;
        private String parentCategory;
        private String descriptionCategory;
    }


    public String getNameCategory() {
        return newCategory.nameCategory + uniqueString;
    }

    public String getSlugCategory() {
        return newCategory.slugCategory + uniqueString;
    }

    public String getParentCategory() {
        return newCategory.parentCategory;
    }

    public String getDescriptionCategory() {
        return newCategory.descriptionCategory + uniqueString;
    }

    @Getter
    EditCategory editCategory;

    static class EditCategory {
        String editNameCategory;
        String editSlugCategory;
        String editParentCategory;
        String editDescriptionCategory;
    }

    public String getEditNameCategory() {
        return editCategory.editNameCategory + uniqueString;
    }

    public String getEditSlugCategory() {
        return editCategory.editSlugCategory + uniqueString;
    }

    public String getEditParentCategory() {
        return editCategory.editParentCategory ;
    }

    public String getEditDescriptionCategory() {
        return editCategory.editDescriptionCategory + uniqueString;
    }


}

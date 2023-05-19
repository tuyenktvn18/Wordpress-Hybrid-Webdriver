package dataTestModels;

import commons.BasePage;
import lombok.Getter;

public class PostModel {

    private String uniqueString = String.valueOf(BasePage.getBasePageObject().getRandomNumber());

    @Getter
    NewPost newPost;

    static class NewPost {
        private String postTitle;
        private String postBody;
    }

    public String getPostTitle() {
        return newPost.postTitle + uniqueString;
    }

    public String getPostBody() {
        return newPost.postBody + uniqueString;
    }

    @Getter
    EditPost editPost;

    static class EditPost {
        String editPostTitle;
        String editPostBody;
    }

    public String getEditPostTitle() {
        return editPost.editPostTitle + uniqueString;
    }

    public String getEditPostBody() {
        return editPost.editPostBody + uniqueString;
    }

}

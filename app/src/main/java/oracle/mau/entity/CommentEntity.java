package oracle.mau.entity;

/**
 * Created by 田帅 on 2017/3/9.
 */

public class CommentEntity {
    private int userId;
    private String userName;
    private String commentContent;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }
}

package oracle.mau.entity;

import java.util.List;

/**
 * Created by 田帅 on 2017/3/14.
 * 用户好友文章实体
 */

public class UserFriendArticleEntity {
    private int userId;
    private String userName;
    private List<ArticleEntity> articleEntityList;

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

    public List<ArticleEntity> getArticleEntityList() {
        return articleEntityList;
    }

    public void setArticleEntityList(List<ArticleEntity> articleEntityList) {
        this.articleEntityList = articleEntityList;
    }
}

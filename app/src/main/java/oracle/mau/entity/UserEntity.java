package oracle.mau.entity;

import java.util.List;

/**
 * Created by shadow on 2017/2/28.
 */

public class UserEntity {
    private int userid;
    private String usertel;
    private String userpwd;
    private String username;
    private String userpic;
    /**
     * 用户详情使用
     */
    //文章集合
    private List<ArticleEntity> articleEntityList;


    public List<ArticleEntity> getArticleEntityList() {
        return articleEntityList;
    }

    public void setArticleEntityList(List<ArticleEntity> articleEntityList) {
        this.articleEntityList = articleEntityList;
    }
    //

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsertel() {
        return usertel;
    }

    public void setUsertel(String usertel) {
        this.usertel = usertel;
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpic() {
        return userpic;
    }

    public void setUserpic(String userpic) {
        this.userpic = userpic;
    }
}

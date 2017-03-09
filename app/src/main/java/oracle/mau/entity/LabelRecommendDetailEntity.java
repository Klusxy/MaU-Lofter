package oracle.mau.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 田帅 on 2017/3/9.
 */

public class LabelRecommendDetailEntity implements Serializable{
    /**
     * 用户id  用户头像地址  用户名
     */
    private int userId;
    private String userImg;
    private String userName;
    /**
     * 文章id  对应图片地址集合  文章内容  文章地址  文章日期
     */
    private int articleId;
    private List<String> articleImgList;
    private String articleContent;
    private String articleLocation;
    private String articleDate;
    /**
     * 标签id  标签名
     */
    private int tagId;
    private String tagName;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public List<String> getArticleImgList() {
        return articleImgList;
    }

    public void setArticleImgList(List<String> articleImgList) {
        this.articleImgList = articleImgList;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public String getArticleLocation() {
        return articleLocation;
    }

    public void setArticleLocation(String articleLocation) {
        this.articleLocation = articleLocation;
    }

    public String getArticleDate() {
        return articleDate;
    }

    public void setArticleDate(String articleDate) {
        this.articleDate = articleDate;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}

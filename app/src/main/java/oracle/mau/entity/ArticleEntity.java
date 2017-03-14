package oracle.mau.entity;

import java.util.List;

/**
 * Created by 田帅 on 2017/3/13.
 */

public class ArticleEntity {
    /**
     * 文章id
     */
    private int articleId;
    /**
     * 文章内容
     */
    private String articleContent;
    /**
     * 文章地址
     */
    private String articleLocation;
    /**
     * 文章日期
     */
    private String articleDate;

    public String getArticleDate() {
        return articleDate;
    }

    public void setArticleDate(String articleDate) {
        this.articleDate = articleDate;
    }

    /**
     * 文章图片集合
     */
    private List<String> imgList;
    /**
     * 文章图片（单张,Label网格使用）
     */
    private String articleImg;
    /**
     * 用户实体
     */
    private UserEntity articleUser;
    /**
     * 评论集合
     */
    private List<CommentEntity> commentEntityList;
    /**
     * 标签实体
     */
    private LabelTagEntity articleTag;

    public List<String> getImgList() {
        return imgList;
    }

    public void setImgList(List<String> imgList) {
        this.imgList = imgList;
    }

    public UserEntity getArticleUser() {
        return articleUser;
    }

    public void setArticleUser(UserEntity articleUser) {
        this.articleUser = articleUser;
    }

    public List<CommentEntity> getCommentEntityList() {
        return commentEntityList;
    }

    public void setCommentEntityList(List<CommentEntity> commentEntityList) {
        this.commentEntityList = commentEntityList;
    }

    public LabelTagEntity getArticleTag() {
        return articleTag;
    }

    public void setArticleTag(LabelTagEntity articleTag) {
        this.articleTag = articleTag;
    }

    public String getArticleLocation() {
        return articleLocation;
    }

    public void setArticleLocation(String articleLocation) {
        this.articleLocation = articleLocation;
    }
    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public String getArticleImg() {
        return articleImg;
    }

    public void setArticleImg(String articleImg) {
        this.articleImg = articleImg;
    }
}

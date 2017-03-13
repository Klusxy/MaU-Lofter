package oracle.mau.entity;

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
     * 文章图片（单张,Label网格使用）
     */
    private String articleImg;

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

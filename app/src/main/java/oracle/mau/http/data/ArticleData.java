package oracle.mau.http.data;

import java.util.List;

import oracle.mau.entity.ArticleEntity;
import oracle.mau.http.bean.BeanData;

/**
 * Created by 田帅 on 2017/3/13.
 */

public class ArticleData extends BeanData {
    private List<ArticleEntity> articleList;

    public List<ArticleEntity> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<ArticleEntity> articleList) {
        this.articleList = articleList;
    }
}

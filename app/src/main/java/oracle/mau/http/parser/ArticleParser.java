package oracle.mau.http.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import oracle.mau.entity.ArticleEntity;
import oracle.mau.http.bean.BeanData;
import oracle.mau.http.bean.BeanParser;
import oracle.mau.http.data.ArticleData;

/**
 * Created by 田帅 on 2017/3/13.
 */

public class ArticleParser extends BeanParser {
    @Override
    public BeanData parser(String result) {
        ArticleData data = new ArticleData();
        List<ArticleEntity> list = new ArrayList<>();
        try {
            JSONArray arr = new JSONArray(result);
            for (int i = 0 ; i < arr.length() ; i ++) {
                ArticleEntity e = new ArticleEntity();
                JSONObject jsonObject = arr.getJSONObject(i);
                String articleId = jsonObject.getString("id");
                e.setArticleId(Integer.parseInt(articleId));
                String article_content = jsonObject.getString("article_content");
                e.setArticleContent(article_content);
                JSONArray article_imgs = jsonObject.getJSONArray("article_imgs");
                for (int j = 0 ; j<article_imgs.length() ; j++) {
                    JSONObject imgObject = article_imgs.getJSONObject(j);
                    String img_url = imgObject.getString("img_url");
                    e.setArticleImg(img_url);
                }
                list.add(e);
            }
            data.setArticleList(list);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }
}

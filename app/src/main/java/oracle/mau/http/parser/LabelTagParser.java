package oracle.mau.http.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import oracle.mau.entity.ArticleEntity;
import oracle.mau.entity.LabelTagEntity;
import oracle.mau.http.bean.BeanData;
import oracle.mau.http.bean.BeanParser;
import oracle.mau.http.data.LabelTagData;

/**
 * Created by 田帅 on 2017/3/12.
 */

public class LabelTagParser extends BeanParser {
    @Override
    public BeanData parser(String result) {
        LabelTagData data = new LabelTagData();
        try {

            List<LabelTagEntity> tagEntityList = new ArrayList<>();
            JSONArray arr = new JSONArray(result);

            for (int i = 0; i < arr.length(); i++) {
                LabelTagEntity tagEntity = new LabelTagEntity();
                JSONObject tagObject = arr.getJSONObject(i);
                //标签id
                String tag_id = tagObject.getString("id");
                tagEntity.setTagId(Integer.parseInt(tag_id));
                //标签名
                String tag_name = tagObject.getString("name");
                tagEntity.setTagTitle(tag_name);
                /**
                 * 四篇文章集合
                 */
                JSONArray imgArr = tagObject.getJSONArray("article");
                List<ArticleEntity> articleEntityList = new ArrayList<>();
                for (int j = 0; j < imgArr.length(); j++) {
                    JSONObject articleObject = imgArr.getJSONObject(j);
                    /**
                     * 文章实体
                     */
                    ArticleEntity articleEntity = new ArticleEntity();
                    //文章id
                    String article_id = articleObject.getString("article_id");
                    articleEntity.setArticleId(Integer.parseInt(article_id));
                    //文章图片地址
                    String img_url = articleObject.getString("img_url");
                    articleEntity.setArticleImg(img_url);

                    articleEntityList.add(articleEntity);
                }
                tagEntity.setArticleEntityListList(articleEntityList);
                tagEntityList.add(tagEntity);
            }
            data.setList(tagEntityList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }
}

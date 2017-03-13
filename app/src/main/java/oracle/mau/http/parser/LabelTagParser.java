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
            LabelTagEntity tagEntity = new LabelTagEntity();
            List<LabelTagEntity> tagEntityList = new ArrayList<>();
            //四篇文章实体集合
            List<ArticleEntity> articleEntityList = new ArrayList<>();
            JSONArray arr = new JSONArray(result);
            for (int i = 0; i < arr.length(); i++) {
                JSONObject jsonObject = arr.getJSONObject(i);
                JSONObject tagObject = jsonObject.getJSONObject("tag");
                //标签id
                String tag_id = tagObject.getString("tag_id");
                tagEntity.setTagId(Integer.parseInt(tag_id));
                //标签名
                String tag_name = tagObject.getString("tag_name");
                tagEntity.setTagTitle(tag_name);
                JSONArray imgArr = jsonObject.getJSONArray("article_imgs");
                /**
                 * 只要四张
                 */
                int flag;
                if (imgArr.length() >= 4) {
                    flag = 4;
                } else {
                    flag = imgArr.length();
                }
                for (int j = 0; j < flag; j++) {
                    JSONObject articleObject = imgArr.getJSONObject(j);
                    ArticleEntity articleEntity = new ArticleEntity();
                    //文章id
                    String article_id = articleObject.getString("article_id");
                    articleEntity.setArticleId(Integer.parseInt(article_id));
                    //文章图片地址
                    String img_url = articleObject.getString("img_url");
                    articleEntity.setArticleImg(img_url);
                    articleEntityList.add(articleEntity);
                }
                tagEntityList.add(tagEntity);
            }
            tagEntity.setArticleEntityListList(articleEntityList);
            data.setList(tagEntityList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }
}

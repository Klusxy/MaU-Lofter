package oracle.mau.http.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import oracle.mau.entity.ArticleEntity;
import oracle.mau.entity.LabelTagEntity;
import oracle.mau.entity.UserEntity;
import oracle.mau.http.bean.BeanData;
import oracle.mau.http.bean.BeanParser;
import oracle.mau.http.data.ArticleData;

/**
 * Created by 田帅 on 2017/3/14.
 */

public class ArticleListParser extends BeanParser {
    @Override
    public BeanData parser(String result) {
        ArticleData data = new ArticleData();
        try {
            List<ArticleEntity> articleEntityList = new ArrayList<>();
            JSONArray arr = new JSONArray(result);
            for (int i = 0; i < arr.length(); i++) {
                ArticleEntity articleEntity = new ArticleEntity();
                JSONObject articleObject = arr.getJSONObject(i);
                //文章id
                String id = articleObject.getString("id");
                articleEntity.setArticleId(Integer.parseInt(id));
                //文章内容
                String content = articleObject.getString("content");
                articleEntity.setArticleContent(content);
                //文章地址
                String article_location = articleObject.getString("article_location");
                articleEntity.setArticleLocation(article_location);
                //文章时间
                String create_time = articleObject.getString("create_time");
                articleEntity.setArticleDate(create_time);
                //文章图片集合
                List<String> imgList = new ArrayList<>();
                JSONArray imgArr = articleObject.getJSONArray("imgs");
                for (int j = 0; j < imgArr.length(); j++) {
                    JSONObject imgObject = imgArr.getJSONObject(j);
                    String img_url = imgObject.getString("img_url");
                    imgList.add(img_url);
                }
                articleEntity.setImgList(imgList);
                //标签实体
                LabelTagEntity tagEntity = new LabelTagEntity();
                JSONObject tagObject = articleObject.getJSONObject("article_tag");
                String tag_id = tagObject.getString("tag_id");
                String tag_name = tagObject.getString("tag_name");
                tagEntity.setTagId(Integer.parseInt(tag_id));
                tagEntity.setTagTitle(tag_name);
                articleEntity.setArticleTag(tagEntity);
                //用户实体
                UserEntity userEntity = new UserEntity();
                JSONObject userObject = articleObject.getJSONObject("article_user");
                String user_id = userObject.getString("user_id");
                String user_name = userObject.getString("user_name");
                String user_img = userObject.getString("user_img");
                String user_tel = userObject.getString("user_tel");
                userEntity.setUserid(Integer.parseInt(user_id));
                userEntity.setUsername(user_name);
                userEntity.setUsertel(user_tel);
                userEntity.setUserpic(user_img);
                articleEntity.setArticleUser(userEntity);
                articleEntityList.add(articleEntity);
            }
            data.setArticleList(articleEntityList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }
}

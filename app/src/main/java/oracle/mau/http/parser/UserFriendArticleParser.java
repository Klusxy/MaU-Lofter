package oracle.mau.http.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import oracle.mau.entity.ArticleEntity;
import oracle.mau.entity.LabelTagEntity;
import oracle.mau.entity.UserEntity;
import oracle.mau.entity.UserFriendArticleEntity;
import oracle.mau.http.bean.BeanData;
import oracle.mau.http.bean.BeanParser;
import oracle.mau.http.data.ArticleData;
import oracle.mau.http.data.UserFriendArticleData;

/**
 * Created by 田帅 on 2017/3/14.
 * home模块  用户好友文章列表解析器
 */

public class UserFriendArticleParser extends BeanParser {
    @Override
    public BeanData parser(String result) {
        UserFriendArticleData data = new UserFriendArticleData();
        try {
            JSONArray arr = new JSONArray(result);
            List<UserFriendArticleEntity> userFriendArticleEntityList = new ArrayList<>();
            for (int i = 0; i < arr.length(); i++) {
                UserFriendArticleEntity userFriendArticleEntity = new UserFriendArticleEntity();
                JSONObject object = arr.getJSONObject(i);
                //文章用户实体
                String userId = object.getString("id");
                userFriendArticleEntity.setUserId(Integer.parseInt(userId));
                String user_name = object.getString("user_name");
                userFriendArticleEntity.setUserName(user_name);
                String user_img = object.getString("user_img");
                userFriendArticleEntity.setUserImg(user_img);
                //文章信息
                List<ArticleEntity> articleEntityList = new ArrayList<>();
                JSONArray articleArr = object.getJSONArray("user_articles");
                for (int j = 0; j < articleArr.length(); j++) {
                    ArticleEntity articleEntity = new ArticleEntity();
                    JSONObject articleObject = articleArr.getJSONObject(j);
                    //文章id
                    String articleId = articleObject.getString("id");
                    articleEntity.setArticleId(Integer.parseInt(articleId));
                    //文章内容
                    String content = articleObject.getString("content");
                    articleEntity.setArticleContent(content);
                    //文章地址
                    String location = articleObject.getString("location");
                    articleEntity.setArticleLocation(location);
                    //文章时间
                    String create_time = articleObject.getString("create_time");
                    articleEntity.setArticleDate(create_time);
                        //文章标签实体
                    LabelTagEntity tagEntity = new LabelTagEntity();
                    JSONObject tagObject = articleObject.getJSONObject("article_tag");
                    //标签id
                    String tagId = tagObject.getString("tag_id");
                    tagEntity.setTagId(Integer.parseInt(tagId));
                    //标签名
                    String tag_name = tagObject.getString("tag_name");
                    tagEntity.setTagTitle(tag_name);
                    articleEntity.setArticleTag(tagEntity);
                        //文章图片数组
                    JSONArray imgArr = articleObject.getJSONArray("imgs");
                    List<String> imgList = new ArrayList<>();
                    for (int k = 0; k < imgArr.length(); k++) {
                        JSONObject imgObject = imgArr.getJSONObject(k);
                        String imgUrl = imgObject.getString("img_url");
                        imgList.add(imgUrl);
                    }
                    articleEntity.setImgList(imgList);
                    articleEntityList.add(articleEntity);
                }
                userFriendArticleEntity.setArticleEntityList(articleEntityList);
                userFriendArticleEntityList.add(userFriendArticleEntity);
            }
            data.setUserFriendArticleEntityList(userFriendArticleEntityList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }
}

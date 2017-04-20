package oracle.mau.http.parser;

import android.icu.text.UFormat;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import oracle.mau.entity.ArticleEntity;
import oracle.mau.entity.UserEntity;
import oracle.mau.http.bean.BeanData;
import oracle.mau.http.bean.BeanParser;
import oracle.mau.http.data.UserData;

/**
 * Created by 田帅 on 2017/3/14.
 * 用户详情解析器
 */

public class UserDetailParser extends BeanParser {
    @Override
    public BeanData parser(String result) {
        UserData data = new UserData();
        try {
            JSONObject userObject = new JSONObject(result);
            UserEntity userEntity = new UserEntity();
            //用户id
            String id = userObject.getString("id");
            userEntity.setUserid(Integer.parseInt(id));
            //用户名
            String user_name = userObject.getString("user_name");
            userEntity.setUsername(user_name);
            //用户电话
            String user_tel = userObject.getString("user_tel");
            userEntity.setUsertel(user_tel);
            //用户头像地址
            String user_img = userObject.getString("user_img");
            userEntity.setUserpic(user_img);

            //用户对应的文章集合
            JSONArray articleArr = userObject.getJSONArray("user_articles");
            List<ArticleEntity> articleEntityList = new ArrayList<>();
            for (int i = 0; i < articleArr.length(); i++) {
                ArticleEntity articleEntity = new ArticleEntity();
                JSONObject articleObject = articleArr.getJSONObject(i);
                String articleId = articleObject.getString("id");
                //文章id
                articleEntity.setArticleId(Integer.parseInt(articleId));
                JSONArray imgArr = articleObject.getJSONArray("imgs");
                //只取一张文章图片
                for (int j = 0; j < 1; j++) {
                    if (imgArr.length()!=0) {
                    JSONObject imgObject = imgArr.getJSONObject(j);
                        String img_url = imgObject.getString("img_url");
                        articleEntity.setArticleImg(img_url);
                    }
                }
                articleEntityList.add(articleEntity);
            }
            userEntity.setArticleEntityList(articleEntityList);
            data.setUserEntity(userEntity);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }
}

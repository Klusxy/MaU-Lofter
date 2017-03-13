package oracle.mau.http.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import oracle.mau.entity.ArticleEntity;
import oracle.mau.entity.CommentEntity;
import oracle.mau.entity.LabelTagEntity;
import oracle.mau.entity.UserEntity;
import oracle.mau.http.bean.BeanData;
import oracle.mau.http.bean.BeanParser;
import oracle.mau.http.data.ArticleData;

/**
 * Created by 田帅 on 2017/3/13.
 */

public class ArticleDetailParser extends BeanParser {
    @Override
    public BeanData parser(String result) {
        ArticleData data = new ArticleData();
        try {
            JSONObject jsonObject = new JSONObject(result);
            ArticleEntity e = new ArticleEntity();
            //文章id
            String article_id = jsonObject.getString("id");
            e.setArticleId(Integer.parseInt(article_id));
            //文章内容
            String article_content = jsonObject.getString("content");
            e.setArticleContent(article_content);
            //文章地址
            String article_location = jsonObject.getString("article_location");
            e.setArticleLocation(article_location);
            //文章图片集合
            List<String> imgList = new ArrayList<>();
            JSONArray imgArr = jsonObject.getJSONArray("article_imgs");
            for (int i = 0 ;i<imgArr.length() ; i++) {
                imgList.add(imgArr.getJSONObject(i).getString("img_url"));
            }
            e.setImgList(imgList);
            //用户实体
            UserEntity userEntity = new UserEntity();
            JSONObject userObject = jsonObject.getJSONObject("user");
            String user_id = userObject.getString("user_id");
            String user_tel = userObject.getString("user_tel");
            String user_pwd = userObject.getString("user_pwd");
            String user_name = userObject.getString("user_name");
            String user_img = userObject.getString("user_img");
            userEntity.setUserid(Integer.parseInt(user_id));
            userEntity.setUsertel(user_tel);
            userEntity.setUserpwd(user_pwd);
            userEntity.setUsername(user_name);
            userEntity.setUserpic(user_img);
            e.setArticleUser(userEntity);
            //标签实体
            LabelTagEntity labelTagEntity = new LabelTagEntity();
            JSONObject tagObject = jsonObject.getJSONObject("article_tag");
            String tag_id = tagObject.getString("tag_id");
            String tag_name = tagObject.getString("tag_name");
            labelTagEntity.setTagId(Integer.parseInt(tag_id));
            labelTagEntity.setTagTitle(tag_name);
            e.setArticleTag(labelTagEntity);
            //评论实体集合
            List<CommentEntity> commentEntityList = new ArrayList<>();
            JSONArray commentArr = jsonObject.getJSONArray("comments");
            for (int i = 0 ;i <commentArr.length() ; i++) {
                CommentEntity commentEntity = new CommentEntity();
                JSONObject commentObject = commentArr.getJSONObject(i);
                String comment_id = commentObject.getString("comment_id");
                String comment_user_id = commentObject.getString("user_id");
                String comment_article_id = commentObject.getString("article_id");
                String create_time = commentObject.getString("create_time");
                String comment_content = commentObject.getString("comment_content");
                commentEntity.setCommentId(Integer.parseInt(comment_id));
                commentEntity.setCommentContent(comment_content);
                commentEntity.setCommentDate(create_time);
                commentEntity.setUserId(Integer.parseInt(comment_user_id));
                commentEntity.setArticleId(Integer.parseInt(comment_article_id));
                commentEntityList.add(commentEntity);
            }
            e.setCommentEntityList(commentEntityList);
            data.setArticleEntity(e);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }
}

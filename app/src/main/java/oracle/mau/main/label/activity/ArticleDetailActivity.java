package oracle.mau.main.label.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;

import java.util.ArrayList;
import java.util.List;

import oracle.mau.R;
import oracle.mau.base.BaseActivity;
import oracle.mau.entity.ArticleEntity;
import oracle.mau.entity.CommentEntity;
import oracle.mau.entity.LabelRecommendDetailEntity;
import oracle.mau.entity.LabelRecommendEntity;
import oracle.mau.http.bean.BeanData;
import oracle.mau.http.common.Callback;
import oracle.mau.http.common.HttpServer;
import oracle.mau.http.constants.URLConstants;
import oracle.mau.http.data.ArticleData;
import oracle.mau.http.parser.ArticleDetailParser;
import oracle.mau.http.parser.ArticleParser;
import oracle.mau.main.label.adapter.ArticleDetailGVAdapter;
import oracle.mau.main.label.adapter.ArticleDetailLVAdapter;
import oracle.mau.utils.ImageUtils;
import oracle.mau.view.GridViewForScrollView;
import oracle.mau.view.ListViewForScrollView;

/**
 * Created by 田帅 on 2017/3/9.
 */

public class ArticleDetailActivity extends BaseActivity {
    private int articleId;
    private ArticleEntity mArticleEntity;
    /**
     * 文章图片网格
     */
    private GridViewForScrollView gv_ad_article_imgs;
    private List<String> imgList;
    /**
     * 文章评论listview
     */
    private ListViewForScrollView lv_ad_article_comments;
    private List<CommentEntity> commentList;

    /**
     * 控件
     */
    private ImageView iv_ad_user_img;
    private TextView tv_ad_user_name;
    private TextView tv_ad_article_content;
    private TextView tv_ad_tag_name;
    private TextView tv_ad_article_location;
    private TextView tv_ad_article_date;

    @Override
    public int getLayoutId() {
        return R.layout.activity_article_detail;
    }

    @Override
    public void initView() {
        gv_ad_article_imgs = (GridViewForScrollView) findViewById(R.id.gv_ad_article_imgs);
        lv_ad_article_comments = (ListViewForScrollView) findViewById(R.id.lv_ad_article_comments);
        iv_ad_user_img = (ImageView) findViewById(R.id.iv_ad_user_img);
        tv_ad_user_name = (TextView) findViewById(R.id.tv_ad_user_name);
        tv_ad_article_content = (TextView) findViewById(R.id.tv_ad_article_content);
        tv_ad_tag_name = (TextView) findViewById(R.id.tv_ad_tag_name);
        tv_ad_article_location = (TextView) findViewById(R.id.tv_ad_article_location);
        tv_ad_article_date = (TextView) findViewById(R.id.tv_ad_article_date);
        /**
         * 获得传入的文章id
         */
        articleId = getIntent().getIntExtra("articleId",-1);
//        articleId = 1;
        /**
         * 请求数据
         */
        requestArticleData();

    }

    private void requestArticleData() {
        String url = URLConstants.BASE_URL + URLConstants.ARTICLE_DETAIL + articleId;
        HttpServer.sendPostRequest(HttpServer.HTTPSERVER_GET, null, new ArticleDetailParser(), url, new Callback() {
            @Override
            public void success(BeanData beanData) {
                ArticleData data = (ArticleData) beanData;
                mArticleEntity = data.getArticleEntity();
                initGridView();
//                initListView();
                updateUI();
            }

            @Override
            public void failure(String error) {

            }
        });
    }

    private void updateUI() {
        ImageUtils.getBitmapUtils(this).display(iv_ad_user_img, mArticleEntity.getArticleUser().getUserpic(), new BitmapLoadCallBack<ImageView>() {
            @Override
            public void onLoadCompleted(ImageView imageView, String s, Bitmap bitmap, BitmapDisplayConfig bitmapDisplayConfig, BitmapLoadFrom bitmapLoadFrom) {
                Bitmap circleBm  = ImageUtils.circleBitmap(bitmap);
                imageView.setImageBitmap(circleBm);
            }

            @Override
            public void onLoadFailed(ImageView imageView, String s, Drawable drawable) {

            }
        });
        tv_ad_user_name.setText(mArticleEntity.getArticleUser().getUsername());
        tv_ad_article_content.setText(mArticleEntity.getArticleContent());
        tv_ad_tag_name.setText(mArticleEntity.getArticleTag().getTagTitle());
        tv_ad_article_location.setText(mArticleEntity.getArticleLocation());
        tv_ad_article_date.setText(mArticleEntity.getArticleDate());
    }

    private void initListView() {
        commentList = new ArrayList<>();
        CommentEntity c = new CommentEntity();
        c.setUserName("评论用户1");
        c.setCommentContent("我在评论");
        commentList.add(c);

        CommentEntity c1 = new CommentEntity();
        c1.setUserName("评论221");
        c1.setCommentContent("我在评论231");
        commentList.add(c1);

        ArticleDetailLVAdapter commentAdapter = new ArticleDetailLVAdapter(this,commentList);
        lv_ad_article_comments.setAdapter(commentAdapter);
    }

    private void initGridView() {
        imgList = mArticleEntity.getImgList();
        if (imgList.size()==1){
            gv_ad_article_imgs.setNumColumns(1);
        }
        if (imgList.size()==2){
            gv_ad_article_imgs.setNumColumns(2);
        }
        if (imgList.size()>=3){
            gv_ad_article_imgs.setNumColumns(3);
        }

        ArticleDetailGVAdapter gvAdapter = new ArticleDetailGVAdapter(this,imgList);
        gv_ad_article_imgs.setAdapter(gvAdapter);
    }


    public static void actionStart(Context context , int articleId){
        Intent intent = new Intent(context,ArticleDetailActivity.class);
        intent.putExtra("articleId",articleId);
        context.startActivity(intent);
    }

}

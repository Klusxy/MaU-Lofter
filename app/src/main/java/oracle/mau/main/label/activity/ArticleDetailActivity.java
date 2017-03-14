package oracle.mau.main.label.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oracle.mau.R;
import oracle.mau.application.MaUApplication;
import oracle.mau.base.BaseActivity;
import oracle.mau.entity.ArticleEntity;
import oracle.mau.entity.CommentEntity;
import oracle.mau.entity.LabelRecommendDetailEntity;
import oracle.mau.entity.LabelRecommendEntity;
import oracle.mau.entity.UserEntity;
import oracle.mau.http.bean.BeanData;
import oracle.mau.http.common.Callback;
import oracle.mau.http.common.HttpServer;
import oracle.mau.http.constants.URLConstants;
import oracle.mau.http.data.ArticleData;
import oracle.mau.http.data.CommonData;
import oracle.mau.http.parser.ArticleDetailParser;
import oracle.mau.http.parser.ArticleParser;
import oracle.mau.http.parser.CommonParser;
import oracle.mau.main.account.activity.AccountDetailActivity;
import oracle.mau.main.label.adapter.ArticleDetailGVAdapter;
import oracle.mau.main.label.adapter.ArticleDetailLVAdapter;
import oracle.mau.utils.GetTheUser;
import oracle.mau.utils.ImageUtils;
import oracle.mau.utils.JudgeUtils;
import oracle.mau.utils.KeyBoardUtils;
import oracle.mau.view.GridViewForScrollView;
import oracle.mau.view.ListViewForScrollView;

/**
 * Created by 田帅 on 2017/3/9.
 */

public class ArticleDetailActivity extends BaseActivity implements View.OnClickListener , AdapterView.OnItemClickListener{
    private int articleId;
    private ArticleEntity mArticleEntity;
    private UserEntity mUser;
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
    private ImageView iv_ad_back;
    private ImageView iv_ad_user_img;
    private TextView tv_ad_user_name;
    private TextView tv_ad_article_content;
    private TextView tv_ad_tag_name;
    private TextView tv_ad_article_location;
    private TextView tv_ad_article_date;
    private TextView tv_ad_user_name_top;
    private TextView tv_send_article_comment;
    private EditText et_send_article_comment;
    private AVLoadingIndicatorView avi;
    /**
     * 评论listview适配器
     */
    private ArticleDetailLVAdapter commentAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_article_detail;
    }

    @Override
    public void initView() {
        gv_ad_article_imgs = (GridViewForScrollView) findViewById(R.id.gv_ad_article_imgs);
        lv_ad_article_comments = (ListViewForScrollView) findViewById(R.id.lv_ad_article_comments);
        lv_ad_article_comments.setOnItemClickListener(this);
        iv_ad_user_img = (ImageView) findViewById(R.id.iv_ad_user_img);
        iv_ad_user_img.setOnClickListener(this);
        tv_ad_user_name = (TextView) findViewById(R.id.tv_ad_user_name);
        tv_ad_article_content = (TextView) findViewById(R.id.tv_ad_article_content);
        tv_ad_tag_name = (TextView) findViewById(R.id.tv_ad_tag_name);
        tv_ad_article_location = (TextView) findViewById(R.id.tv_ad_article_location);
        tv_ad_article_date = (TextView) findViewById(R.id.tv_ad_article_date);
        tv_ad_user_name_top = (TextView) findViewById(R.id.tv_ad_user_name_top);
        iv_ad_back = (ImageView) findViewById(R.id.iv_ad_back);
        iv_ad_back.setOnClickListener(this);
        tv_send_article_comment = (TextView) findViewById(R.id.tv_send_article_comment);
        tv_send_article_comment.setOnClickListener(this);
        et_send_article_comment = (EditText) findViewById(R.id.et_send_article_comment);
        avi = (AVLoadingIndicatorView) findViewById(R.id.avi);
        /**
         * 获得传入的文章id
         */
        articleId = getIntent().getIntExtra("articleId", -1);
        /**
         * 获得当前用户
         */
        mUser = GetTheUser.getUser(this);
        /**
         * 请求数据
         */
        requestArticleData();
    }

    private void requestArticleData() {
        avi.show();
        String url = URLConstants.BASE_URL + URLConstants.ARTICLE_DETAIL + articleId;
        HttpServer.sendPostRequest(HttpServer.HTTPSERVER_GET, null, new ArticleDetailParser(), url, new Callback() {
            @Override
            public void success(BeanData beanData) {
                //隐藏进度条
                avi.hide();
                ArticleData data = (ArticleData) beanData;
                mArticleEntity = data.getArticleEntity();
                if (mArticleEntity!=null) {
                    initGridView();
                    initListView();
                    updateUI();
                }
            }

            @Override
            public void failure(String error) {

            }
        });
    }

    /**
     * 更新主界面
     */
    private void updateUI() {
        ImageUtils.getBitmapUtils(this).display(iv_ad_user_img, mArticleEntity.getArticleUser().getUserpic(), new BitmapLoadCallBack<ImageView>() {
            @Override
            public void onLoadCompleted(ImageView imageView, String s, Bitmap bitmap, BitmapDisplayConfig bitmapDisplayConfig, BitmapLoadFrom bitmapLoadFrom) {
                Bitmap circleBm = ImageUtils.circleBitmap(bitmap);
                imageView.setImageBitmap(circleBm);
            }

            @Override
            public void onLoadFailed(ImageView imageView, String s, Drawable drawable) {

            }
        });
        tv_ad_user_name.setText(mArticleEntity.getArticleUser().getUsername());
        tv_ad_user_name_top.setText(mArticleEntity.getArticleUser().getUsername());
        tv_ad_article_content.setText(mArticleEntity.getArticleContent());
        tv_ad_tag_name.setText(mArticleEntity.getArticleTag().getTagTitle());
        tv_ad_article_location.setText(mArticleEntity.getArticleLocation());
        tv_ad_article_date.setText(mArticleEntity.getArticleDate());
    }

    /**
     * 初始化评论列表
     */
    private void initListView() {
        commentList = mArticleEntity.getCommentEntityList();
        commentAdapter =new ArticleDetailLVAdapter(this, commentList);
        lv_ad_article_comments.setAdapter(commentAdapter);
    }

    /**
     * 初始化文章图片网格
     */
    private void initGridView() {
        imgList = mArticleEntity.getImgList();
        if (imgList.size() == 1) {
            gv_ad_article_imgs.setNumColumns(1);
        }
        if (imgList.size() == 2) {
            gv_ad_article_imgs.setNumColumns(2);
        }
        if (imgList.size() >= 3) {
            gv_ad_article_imgs.setNumColumns(3);
        }

        ArticleDetailGVAdapter gvAdapter = new ArticleDetailGVAdapter(this, imgList);
        gv_ad_article_imgs.setAdapter(gvAdapter);
    }


    /**
     * 启动界面的静态方法
     *
     * @param context
     * @param articleId
     */
    public static void actionStart(Context context, int articleId) {
        Intent intent = new Intent(context, ArticleDetailActivity.class);
        intent.putExtra("articleId", articleId);
        context.startActivity(intent);
    }

    /**
     * 点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //返回按钮
            case R.id.iv_ad_back:
                finish();
                break;
            //发表评论按钮
            case R.id.tv_send_article_comment:
                sendComment();
                break;
            //头像按钮
            case R.id.iv_ad_user_img:
                AccountDetailActivity.actionStart(this,mArticleEntity.getArticleUser().getUserid());
                break;
        }
    }

    /**
     * 发表评论
     */
    private void sendComment() {
        /**
         * 判断输入框是否为空
         * 得到app中用户id
         * 回调函数中刷新listview
         */
        if (JudgeUtils.isEmpty(et_send_article_comment.getText().toString())) {
            toast("输入框不能为空");
        } else {
            avi.show();

            Map<String, Object> map = new HashMap<>();
            map.put("article_id", mArticleEntity.getArticleId() + "");
            map.put("comment_content", et_send_article_comment.getText().toString());
            map.put("user_id", mUser.getUserid() + "");
            HttpServer.sendPostRequest(HttpServer.HTTPSERVER_POST, map, new CommonParser(), URLConstants.BASE_URL + URLConstants.SEND_ARTICLE_COMMENT, new Callback() {
                @Override
                public void success(BeanData beanData) {
                    //隐藏进度条
                    avi.hide();
                    CommonData data = (CommonData) beanData;
                    toast(data.getMessage());
                    updateCommentUI();
                }

                @Override
                public void failure(String error) {

                }
            });
        }
    }

    /**
     * 更新评论部分UI
     */
    private void updateCommentUI() {
        /**
         * 更新listview
         */
        updateCommentLV();
        et_send_article_comment.setText("");
        KeyBoardUtils.closeKeybord(et_send_article_comment, this);
    }

    /**
     * 更新listview
     */
    private void updateCommentLV() {
        CommentEntity updateComment = new CommentEntity();
        updateComment.setUserId(mUser.getUserid());
        updateComment.setCommentContent(et_send_article_comment.getText().toString());
        updateComment.setUserName(mUser.getUsername());
        /**
         * 追加的评论显示在第一行
         */
        commentList.add(0,updateComment);
        if (commentAdapter!=null) {
            commentAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AccountDetailActivity.actionStart(this,commentList.get(position).getUserId());
    }
}

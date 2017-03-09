package oracle.mau.main.label.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import oracle.mau.R;
import oracle.mau.base.BaseActivity;
import oracle.mau.entity.CommentEntity;
import oracle.mau.entity.LabelRecommendDetailEntity;
import oracle.mau.entity.LabelRecommendEntity;
import oracle.mau.main.label.adapter.ArticleDetailGVAdapter;
import oracle.mau.main.label.adapter.ArticleDetailLVAdapter;
import oracle.mau.view.GridViewForScrollView;
import oracle.mau.view.ListViewForScrollView;

/**
 * Created by 田帅 on 2017/3/9.
 */

public class ArticleDetailActivity extends BaseActivity {

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

    @Override
    public int getLayoutId() {
        return R.layout.activity_article_detail;
    }

    @Override
    public void initView() {
        gv_ad_article_imgs = (GridViewForScrollView) findViewById(R.id.gv_ad_article_imgs);
        lv_ad_article_comments = (ListViewForScrollView) findViewById(R.id.lv_ad_article_comments);
        initGridView();
        initListView();

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
        imgList = new ArrayList<>();
        imgList.add("http://img06.tooopen.com/images/20161123/tooopen_sy_187628854311.jpg");
        imgList.add("http://img06.tooopen.com/images/20161123/tooopen_sy_187628854311.jpg");
        imgList.add("http://img06.tooopen.com/images/20161123/tooopen_sy_187628854311.jpg");
        imgList.add("http://img06.tooopen.com/images/20161123/tooopen_sy_187628854311.jpg");
        imgList.add("http://img06.tooopen.com/images/20161123/tooopen_sy_187628854311.jpg");
        imgList.add("http://img06.tooopen.com/images/20161123/tooopen_sy_187628854311.jpg");
        imgList.add("http://img06.tooopen.com/images/20161123/tooopen_sy_187628854311.jpg");
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
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("entity",entity);
//        intent.putExtras(bundle);
        intent.putExtra("articleId",articleId);
        context.startActivity(intent);
    }

}

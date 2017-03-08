package oracle.mau.main.label.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.ArrayList;
import java.util.List;

import oracle.mau.R;
import oracle.mau.base.BaseFragment;
import oracle.mau.entity.LabelRecommendEntity;
import oracle.mau.entity.LabelTagEntity;
import oracle.mau.entity.UserEntity;
import oracle.mau.main.label.activity.RecommendDetailActivity;
import oracle.mau.main.label.adapter.ImageCarouselVPAdapter;
import oracle.mau.main.label.adapter.LabelMainRecommendUserGVAdapter;
import oracle.mau.main.label.adapter.LabelMainReommendLabelLVAdapter;
import oracle.mau.main.label.adapter.TagGalleryVPAdapter;
import oracle.mau.main.label.view.TouchViewPager;
import oracle.mau.utils.ExtraUtils;
import oracle.mau.utils.ScreenUtils;
import oracle.mau.view.GridViewForScrollView;
import oracle.mau.view.ListViewForScrollView;

/**
 * Created by 田帅 on 2017/2/28.
 */

public class LabelMainFragment extends BaseFragment implements OnRefreshListener<ScrollView> {
    /**
     * 顶部自动轮播viewpager
     */
    private ViewPager vp_label_main;
    /**
     * 放imageView的集合
     */
    private List<ImageView> imageViewList = new ArrayList<>();
    /**
     * 自动轮播
     */
    private final int LABEL_TOP_VP_AUTO_UPDATE = 100002;
    /**
     * 标签推荐listview
     */
    private ListViewForScrollView lv_label_main_label_recommend;
    private List<LabelRecommendEntity> lrList;
    /**
     * 用户推荐gridview
     */
    private GridViewForScrollView gv_label_main_user_recommend;
    private List<UserEntity> userList;


    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                //下拉刷新
                case LABEL_MAIN_PULL_UPDATE:
                    toast(msg.obj.toString());
                    /**
                     * 得到数据之后，通知xxx刷新或其他操作
                     */

                    /**
                     * 最后掉调用该方法缩回头部（主线程中）
                     */
                    mPullRefreshScrollView.onRefreshComplete();

                    break;
                //顶部自动轮播
                case LABEL_TOP_VP_AUTO_UPDATE:
                    vp_label_main.setCurrentItem(vp_label_main.getCurrentItem() + 1);//收到消息，指向下一个页面
                    handler.sendEmptyMessageDelayed(LABEL_TOP_VP_AUTO_UPDATE, 4000);//2S后在发送一条消息，由于在handleMessage()方法中，造成死循环。
                    break;
            }
        }
    };

    /**
     * 标签画廊数据
     */
    private TouchViewPager vp_label_tag;
    private List<LabelTagEntity> tagList = new ArrayList<>();


    /**
     * 下拉刷新
     */
    private PullToRefreshScrollView mPullRefreshScrollView;
    private final int LABEL_MAIN_PULL_UPDATE = 100001;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_label_main;
    }

    @Override
    protected void initView() {
        vp_label_main = (ViewPager) rootView.findViewById(R.id.vp_label_main);
        vp_label_tag = (TouchViewPager) rootView.findViewById(R.id.vp_label_tag);
        lv_label_main_label_recommend = (ListViewForScrollView) rootView.findViewById(R.id.lv_label_main_label_recommend);
        gv_label_main_user_recommend = (GridViewForScrollView) rootView.findViewById(R.id.gv_label_main_user_recommend);
        /**
         * 初始化下拉刷新、设置监听(去获取数据)
         */
        mPullRefreshScrollView = (PullToRefreshScrollView) rootView.findViewById(R.id.ptr_label_main_scrollview);
        mPullRefreshScrollView.setOnRefreshListener(this);
        //初始化顶部自动轮播数据
        initImageCarouselData();
        //初始化顶部自动轮播
        initImageCarousel();
        //初始化标签画廊数据
        initTagGalleryData();
        //初始化标签画廊
        initTagGallery();
        //初始化标签推荐listview数据
        initLabelRecommendLVData();
        //初始化标签推荐listview
        initLabelRecommendLV();
        //初始化达人推荐gridview数据
        initUserRecommendGVData();
        //初始化达人推荐gridview
        initUserRecommendGV();
    }

    /**
     * 初始化达人推荐gridview
     */
    private void initUserRecommendGV() {

    }

    /**
     * 初始化达人推荐gridview数据
     */
    private void initUserRecommendGVData() {
        userList = new ArrayList<>();
        UserEntity user = new UserEntity();
        user.setUsername("云深步知处");
        userList.add(user);
        userList.add(user);
        userList.add(user);
        userList.add(user);
        userList.add(user);
        userList.add(user);
        int[] userImgs = {R.mipmap.mh2, R.mipmap.mh2, R.mipmap.mh2, R.mipmap.mh2, R.mipmap.mh2, R.mipmap.mh2};
        LabelMainRecommendUserGVAdapter userAdapter = new LabelMainRecommendUserGVAdapter(mContext, userList, userImgs);
        gv_label_main_user_recommend.setAdapter(userAdapter);
    }

    /**
     * 初始化标签推荐listview
     */
    private void initLabelRecommendLV() {

    }

    /**
     * 初始化标签推荐listview数据
     */
    private void initLabelRecommendLVData() {
        lrList = new ArrayList<>();
        LabelRecommendEntity lr1 = new LabelRecommendEntity();
        lr1.setLrTitle("始于人像摄影");
        lr1.setLrParticipationNum(12875);
        lrList.add(lr1);

        LabelRecommendEntity lr2 = new LabelRecommendEntity();
        lr2.setLrTitle("彩色");
        lr2.setLrParticipationNum(34875);
        lrList.add(lr2);

        LabelRecommendEntity lr3 = new LabelRecommendEntity();
        lr3.setLrTitle("关于春天的特别回忆");
        lr3.setLrParticipationNum(66875);
        lrList.add(lr3);

        LabelRecommendEntity lr4 = new LabelRecommendEntity();
        lr4.setLrTitle("假装喵星人");
        lr4.setLrParticipationNum(232875);
        lrList.add(lr4);

        int[] bgs = {R.mipmap.mh1, R.mipmap.mh2, R.mipmap.mh3, R.mipmap.mh4};
        int[] lrImgs1 = {R.mipmap.renxiang1, R.mipmap.renxiang2, R.mipmap.renxiang3};
        int[] lrImgs2 = {R.mipmap.caise1, R.mipmap.caise2, R.mipmap.caise3};
        int[] lrImgs3 = {R.mipmap.chuntian1, R.mipmap.chuntian2, R.mipmap.chuntian3};
        int[] lrImgs4 = {R.mipmap.miaoxingren1, R.mipmap.miaoxingren2, R.mipmap.miaoxingren3};
        List<int[]> imgsList = new ArrayList<>();
        imgsList.add(lrImgs1);
        imgsList.add(lrImgs2);
        imgsList.add(lrImgs3);
        imgsList.add(lrImgs4);
        LabelMainReommendLabelLVAdapter adapter = new LabelMainReommendLabelLVAdapter(mContext, lrList, bgs, imgsList);
        lv_label_main_label_recommend.setAdapter(adapter);
    }


    /**
     * 初始化画廊数据
     */
    private void initTagGalleryData() {
        int imgs1[] = {R.mipmap.g1, R.mipmap.g2, R.mipmap.g3, R.mipmap.g4};
        LabelTagEntity labelTagEntity1 = new LabelTagEntity();
        labelTagEntity1.setTagTitle("摄影");
        labelTagEntity1.setImgs(imgs1);

        LabelTagEntity labelTagEntity2 = new LabelTagEntity();
        labelTagEntity2.setTagTitle("女神");
        labelTagEntity2.setImgs(imgs1);

        LabelTagEntity labelTagEntity3 = new LabelTagEntity();
        labelTagEntity3.setTagTitle("小清新");
        labelTagEntity3.setImgs(imgs1);

        LabelTagEntity labelTagEntity4 = new LabelTagEntity();
        labelTagEntity4.setTagTitle("运动");
        labelTagEntity4.setImgs(imgs1);

        LabelTagEntity labelTagEntity5 = new LabelTagEntity();
        labelTagEntity5.setTagTitle("电影");
        labelTagEntity5.setImgs(imgs1);

        LabelTagEntity labelTagEntity6 = new LabelTagEntity();
        labelTagEntity6.setTagTitle("明星");
        labelTagEntity6.setImgs(imgs1);

        tagList.add(labelTagEntity1);
        tagList.add(labelTagEntity2);
        tagList.add(labelTagEntity3);
        tagList.add(labelTagEntity4);
        tagList.add(labelTagEntity5);
        tagList.add(labelTagEntity6);
        /**
         * 给自定义vp赋值数据，点击时候传过去
         */
        vp_label_tag.setTagList(tagList);
    }

    /**
     * 初始化画廊
     */
    private void initTagGallery() {
        int mScreenWidth = ScreenUtils.getScreenWidth(mContext);
        vp_label_tag.setPageMargin(-mScreenWidth / 2);
        vp_label_tag.setOffscreenPageLimit(tagList.size());
        /**
         * 缩放
         */
        vp_label_tag.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                if (Math.abs(position) == 1) {
                    position = 1;
                }
                final float normalizedposition = Math.abs(Math.abs(position) - 1);
                page.setScaleX(normalizedposition / 2 + 0.5f);
                page.setScaleY(normalizedposition / 2 + 0.5f);
            }
        });
        TagGalleryVPAdapter galleryAdapter = new TagGalleryVPAdapter(tagList, mContext);
        /**
         * 动态设置vp的宽高
         */
        int screenWidth = ScreenUtils.getScreenWidth(mContext);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(screenWidth, screenWidth * 3 / 4);
        lp.addRule(RelativeLayout.BELOW, R.id.vp_label_main);
        lp.setMargins(0, 30, 0, 0);
        vp_label_tag.setLayoutParams(lp);
        vp_label_tag.setAdapter(galleryAdapter);
        vp_label_tag.setCurrentItem(1000);
    }

    /**
     * 初始化顶部自动轮播数据
     */
    private void initImageCarouselData() {
        imageViewList.clear();
        ImageView iva = new ImageView(mContext);
        iva.setBackgroundResource(R.mipmap.g1);
        iva.setScaleType(ImageView.ScaleType.FIT_XY);

        ImageView ivb = new ImageView(mContext);
        ivb.setBackgroundResource(R.mipmap.g2);
        ivb.setScaleType(ImageView.ScaleType.FIT_XY);

        ImageView ivc = new ImageView(mContext);
        ivc.setBackgroundResource(R.mipmap.g3);
        ivc.setScaleType(ImageView.ScaleType.FIT_XY);

        ImageView ivd = new ImageView(mContext);
        ivd.setBackgroundResource(R.mipmap.g4);
        ivd.setScaleType(ImageView.ScaleType.FIT_XY);

        ImageView ive = new ImageView(mContext);
        ive.setBackgroundResource(R.mipmap.g6);
        ive.setScaleType(ImageView.ScaleType.FIT_XY);

        imageViewList.add(iva);
        imageViewList.add(ivb);
        imageViewList.add(ivc);
        imageViewList.add(ivd);
        imageViewList.add(ive);
    }

    /**
     * 初始化顶部自动轮播
     */
    private void initImageCarousel() {
        ImageCarouselVPAdapter imageCarouselVPAdapter = new ImageCarouselVPAdapter(imageViewList);
        vp_label_main.setAdapter(imageCarouselVPAdapter);
        vp_label_main.setCurrentItem(1000);//当前页是第1000页
    }

    /**
     * fragment可见可交互的时候就开始发送消息，开启循环
     */
    @Override
    public void onResume() {
        super.onResume();
        handler.sendEmptyMessageDelayed(LABEL_TOP_VP_AUTO_UPDATE, 4000);
    }

    /**
     * 当Fragment不可见的时候让handler停止发送消息
     * 防止内存泄露
     */
    @Override
    public void onStop() {
        super.onStop();
        handler.removeMessages(LABEL_TOP_VP_AUTO_UPDATE);
    }

    /**
     * 下拉刷新的监听方法
     * 用于去获取数据
     *
     * @param refreshView
     */
    @Override
    public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    //模拟获取数据花费4秒
                    sleep(4000);
                    //得到数据后不能直接在子线程中让下拉刷新头部缩回，通过handler机制告诉主线程缩回下拉刷新头部
                    Message msg = handler.obtainMessage(LABEL_MAIN_PULL_UPDATE, "刷新成功");
                    handler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}

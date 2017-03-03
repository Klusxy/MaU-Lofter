package oracle.mau.main.label.fragment;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.ArrayList;
import java.util.List;

import oracle.mau.R;
import oracle.mau.base.BaseFragment;
import oracle.mau.entity.LabelTagEntity;
import oracle.mau.main.label.adapter.ImageCarouselVPAdapter;
import oracle.mau.main.label.adapter.TagGalleryVPAdapter;
import oracle.mau.utils.ScreenUtils;

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
    private ViewPager vp_label_tag;
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
        vp_label_tag = (ViewPager) rootView.findViewById(R.id.vp_label_tag);
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
    }


    /**
     * 初始化画廊数据
     */
    private void initTagGalleryData() {
        int imgs1[] = {R.mipmap.g1, R.mipmap.g2, R.mipmap.g3, R.mipmap.g4};
        LabelTagEntity labelTagEntity = new LabelTagEntity();
        labelTagEntity.setTagTitle("摄影");
        labelTagEntity.setImgs(imgs1);

        tagList.add(labelTagEntity);
        tagList.add(labelTagEntity);
        tagList.add(labelTagEntity);
        tagList.add(labelTagEntity);
        tagList.add(labelTagEntity);

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

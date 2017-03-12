package oracle.mau.main.label.fragment;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import oracle.mau.R;
import oracle.mau.base.BaseFragment;
import oracle.mau.entity.LabelTagEntity;
import oracle.mau.entity.UserEntity;
import oracle.mau.http.bean.BeanData;
import oracle.mau.http.common.Callback;
import oracle.mau.http.common.HttpServer;
import oracle.mau.http.constants.URLConstants;
import oracle.mau.http.data.LabelTagData;
import oracle.mau.http.data.HotUserData;
import oracle.mau.http.parser.LabelTagParser;
import oracle.mau.http.parser.HotUserParser;
import oracle.mau.main.account.activity.AccountDetailActivity;
import oracle.mau.main.label.adapter.LabelMainRecommendUserGVAdapter;
import oracle.mau.main.label.adapter.TagGalleryVPAdapter;
import oracle.mau.main.label.view.TouchViewPager;
import oracle.mau.utils.ScreenUtils;
import oracle.mau.view.GridViewForScrollView;

/**
 * Created by 田帅 on 2017/2/28.
 */

public class LabelMainFragment extends BaseFragment implements OnRefreshListener<ScrollView> , AdapterView.OnItemClickListener{

    /**
     * 用户推荐gridview
     */
    private GridViewForScrollView gv_label_main_user_recommend;
    private List<UserEntity> userList;
    /**
     * 进度条
     */
    private AVLoadingIndicatorView avi;

    /**
     * 标签画廊数据
     */
    private TouchViewPager vp_label_tag;
    private List<LabelTagEntity> tagList;
    /**
     * 下拉刷新
     */
    private PullToRefreshScrollView mPullRefreshScrollView;
    //标记是否是下拉刷新
    private int updateFlag = 0;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_label_main;
    }

    @Override
    protected void initView() {
        avi = (AVLoadingIndicatorView) rootView.findViewById(R.id.avi);
        vp_label_tag = (TouchViewPager) rootView.findViewById(R.id.vp_label_tag);
        gv_label_main_user_recommend = (GridViewForScrollView) rootView.findViewById(R.id.gv_label_main_user_recommend);
        gv_label_main_user_recommend.setOnItemClickListener(this);
        /**
         * 初始化下拉刷新、设置监听(去获取数据)
         */
        mPullRefreshScrollView = (PullToRefreshScrollView) rootView.findViewById(R.id.ptr_label_main_scrollview);
        mPullRefreshScrollView.setOnRefreshListener(this);
        //显示进度条
        avi.show();
        //初始化达人推荐gridview数据
        initUserRecommendGVData();
        //初始化标签画廊数据
        initTagGalleryData();
    }

    /**
     * 初始化达人推荐gridview
     */
    private void initUserRecommendGV() {
        LabelMainRecommendUserGVAdapter userAdapter = new LabelMainRecommendUserGVAdapter(mContext, userList);
        gv_label_main_user_recommend.setAdapter(userAdapter);
    }

    /**
     * 初始化达人推荐gridview数据
     */
    private void initUserRecommendGVData() {
        HotUserParser parser = new HotUserParser();
        HttpServer.sendPostRequest(HttpServer.HTTPSERVER_GET, null, parser, URLConstants.BASE_URL + URLConstants.HOT_USER, new Callback() {
            @Override
            public void success(BeanData beanData) {
                HotUserData uData = (HotUserData) beanData;
                userList = uData.getUserList();
                initUserRecommendGV();
                if (updateFlag > 0) {
                    /**
                     * 最后掉调用该方法缩回头部（主线程中）
                     */
                    mPullRefreshScrollView.onRefreshComplete();
                }
                //隐藏进度条
                avi.hide();
            }

            @Override
            public void failure(String error) {

            }
        });
    }


    /**
     * 初始化画廊数据
     */
    private void initTagGalleryData() {
        LabelTagParser parser = new LabelTagParser();
        HttpServer.sendPostRequest(HttpServer.HTTPSERVER_GET, null, parser, URLConstants.BASE_URL + URLConstants.TAG_GALLERY, new Callback() {
            @Override
            public void success(BeanData beanData) {
                LabelTagData data = (LabelTagData) beanData;
                tagList = data.getList();
                if (tagList != null) {
                    initTagGallery();
                }
            }

            @Override
            public void failure(String error) {

            }
        });
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
        lp.addRule(RelativeLayout.BELOW, R.id.rl_label_main_user_recommend);
        lp.setMargins(0, 30, 0, 0);
        vp_label_tag.setLayoutParams(lp);
        vp_label_tag.setAdapter(galleryAdapter);
        vp_label_tag.setCurrentItem(1000);
    }

    /**
     * 下拉刷新的监听方法
     * 用于去获取数据
     *
     * @param refreshView
     */
    @Override
    public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
        updateFlag++;
        initUserRecommendGVData();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.gv_label_main_user_recommend :
                AccountDetailActivity.actionStart(mContext,userList.get(position).getUserid());
                break;
        }
    }
}

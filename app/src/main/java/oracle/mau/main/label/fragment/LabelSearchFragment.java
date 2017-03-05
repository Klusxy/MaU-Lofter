package oracle.mau.main.label.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import oracle.mau.R;
import oracle.mau.base.BaseFragment;
import oracle.mau.main.label.adapter.SearchLabelVPAdapter;

/**
 * Created by 田帅 on 2017/2/28.
 */

public class LabelSearchFragment extends BaseFragment implements ViewPager.OnPageChangeListener{
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private SearchLabelVPAdapter mAdapter;
    //viewpager数据    fragment列表  标题列表
    private List<Fragment> mFragmentList = new ArrayList<>();
    private List<String> mTitleList = new ArrayList<>();
    //标记当前页卡位置，默认为第一个页卡
    private int mPosition = 0;
    /**
     * 广播
     */
    public static String REQUEST_SEARCH_DATA_BROAD = "searchBroad";
    private RequestDataBroadcast rdb = new RequestDataBroadcast();


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_label_search;
    }

    @Override
    protected void initView() {
        mTabLayout = (TabLayout) rootView.findViewById(R.id.tl_label_search);
        mViewPager = (ViewPager) rootView.findViewById(R.id.vp_label_search);
        initList();
        initTabLayout();
    }

    private void initTabLayout() {
        /**
         * 注册广播
         */
        mContext.registerReceiver(rdb,new IntentFilter(REQUEST_SEARCH_DATA_BROAD));
        mAdapter = new SearchLabelVPAdapter(getChildFragmentManager(), mFragmentList, mTitleList);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(0)));//添加tab选项卡
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(2)));
        mTabLayout.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来。
        mViewPager.addOnPageChangeListener(this);
    }

    /**
     * 在页面消失的时候反注册广播,防止内存泄露
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        mContext.unregisterReceiver(rdb);
    }

    private void initList() {
        mTitleList.add("标签");
        mTitleList.add("用户");
        mTitleList.add("文章");
        mFragmentList.add(new LabelSearchDetailFragment());
        mFragmentList.add(new LabelSearchDetailFragment());
        mFragmentList.add(new LabelSearchDetailFragment());
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mPosition = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private class RequestDataBroadcast extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            /**
            * 发送广播，通知请求数据
            */
            String content = intent.getStringExtra("contnet");
            switch (mPosition) {
                case 0:
                    toast("第一层碎片接收到请求标签信息"+content);
                    break;
                case 1:
                    toast("第一层碎片接收到请求用户信息"+content);
                    break;
                case 2:
                    toast("第一层碎片接收到请求文章信息"+content);
                    break;
            }
            //请求完之后发送广播通知更新UI
            updateUI();
        }
    }

    private void updateUI() {
        /**
         * 发送广播，通知请求数据
         */
        Intent intent = new Intent(LabelSearchDetailFragment.UPDATE_SEARCH_UI_BROAD);
        intent.putExtra("position",mPosition);
        mContext.sendBroadcast(intent);
    }
}

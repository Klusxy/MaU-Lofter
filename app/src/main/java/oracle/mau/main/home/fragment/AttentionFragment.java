package oracle.mau.main.home.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ScrollView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.ArrayList;
import java.util.List;

import oracle.mau.R;
import oracle.mau.entity.LabelRecommendDetailEntity;
import oracle.mau.main.label.activity.ArticleDetailActivity;
import oracle.mau.main.label.adapter.RecommendDetailLVAdapter;
import oracle.mau.view.ListViewForScrollView;

/**
 * Created by Administrator on 2017/3/1.
 */

public class AttentionFragment extends Fragment implements AdapterView.OnItemClickListener,PullToRefreshBase.OnRefreshListener<ScrollView> {
    RecommendDetailLVAdapter adapter;
    ListViewForScrollView lv;
    /**
     * 数据源，复用Label中布局适配器
     */
    List<LabelRecommendDetailEntity> list=new ArrayList<LabelRecommendDetailEntity>();
    /**
     * 下拉刷新
     */

    private PullToRefreshScrollView mPullRefreshScrollView;
    private final int HOME_PULL_UPDATE = 100001;

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            mPullRefreshScrollView.onRefreshComplete();
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     View view=inflater.inflate(R.layout.fragment_homeattention,null);
        lv=(ListViewForScrollView)view.findViewById(R.id.home_lv);
        adapter=new RecommendDetailLVAdapter(getActivity(),list);
        lv.setAdapter(adapter);
        /**
         * 初始化下拉刷新、设置监听(去获取数据)
         */
        mPullRefreshScrollView = (PullToRefreshScrollView) view.findViewById(R.id.ptr_home_attention);
        mPullRefreshScrollView.smoothScrollTo(0, 0);//将ScrollView滚动至最顶端（自定义的listview影响下的效果）
        mPullRefreshScrollView.setOnRefreshListener(this);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getActivity(),ArticleDetailActivity.class);
                startActivity(intent);
            }
        });




        return view;
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
                    Message msg = handler.obtainMessage(HOME_PULL_UPDATE, "刷新成功");
                    handler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}

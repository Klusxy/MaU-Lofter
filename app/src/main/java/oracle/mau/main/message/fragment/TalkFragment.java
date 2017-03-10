package oracle.mau.main.message.fragment;

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
import oracle.mau.entity.LabelRecommendEntity;
import oracle.mau.main.label.adapter.LabelMainReommendLabelLVAdapter;
import oracle.mau.main.message.adapter.NewsListAdapter;
import oracle.mau.view.ListViewForScrollView;

/**
 * Created by shadow on 2017/3/2.
 */

public class TalkFragment extends Fragment  implements PullToRefreshBase.OnRefreshListener<ScrollView> {
//    private ListViewForScrollView listView;

    /**
     * 标签推荐listview
     */
    private ListViewForScrollView lv_label_main_label_recommend;
    private List<LabelRecommendEntity> lrList;
    /**
     * 下拉刷新
     */

    private final int LABEL_MAIN_PULL_UPDATE = 100001;
    private PullToRefreshScrollView mPullRefreshScrollView;
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            mPullRefreshScrollView.onRefreshComplete();
        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_msg_talk,null);
//        listView=(ListViewForScrollView) view.findViewById(R.id.msg_talk_listview);
        lv_label_main_label_recommend = (ListViewForScrollView) view.findViewById(R.id.lv_label_main_label_recommend);


//        listView.setOnItemClickListener(this);
        /**
         * 初始化下拉刷新、设置监听(去获取数据)
         */
        mPullRefreshScrollView = (PullToRefreshScrollView) view.findViewById(R.id.ptr_label_main_scrollview);
        mPullRefreshScrollView.smoothScrollTo(0, 0);//将ScrollView滚动至最顶端（自定义的listview影响下的效果）
        mPullRefreshScrollView.setOnRefreshListener(this);
        //初始化标签推荐listview数据
        initLabelRecommendLVData();
        return  view;
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
        LabelMainReommendLabelLVAdapter adapter = new LabelMainReommendLabelLVAdapter(getContext(), lrList, bgs, imgsList);
        lv_label_main_label_recommend.setAdapter(adapter);
    }

}

package oracle.mau.main.message.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ScrollView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import oracle.mau.R;
import oracle.mau.main.message.adapter.NewsListAdapter;
import oracle.mau.view.ListViewForScrollView;

/**
 * Created by shadow on 2017/3/2.
 */

public class NewsFragment extends Fragment implements AdapterView.OnItemClickListener,PullToRefreshBase.OnRefreshListener<ScrollView> {
    private ListViewForScrollView listView;
    /*
    listview的数据源
     */
    private int [] msgpic={R.drawable.message_new_fans,R.drawable.message_fav,R.drawable.message_new_notices,R.drawable.message_sys_notices};
    private String[] msgtext={"新的粉丝","喜欢","评论","通知"};

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
        View view=inflater.inflate(R.layout.fragment_msg_message,null);

        listView=(ListViewForScrollView) view.findViewById(R.id.msg_listview);
        NewsListAdapter newslistAdapter=new NewsListAdapter(getActivity(),msgtext,msgpic);
        listView.setAdapter(newslistAdapter);
        listView.setOnItemClickListener(this);
        /**
         * 初始化下拉刷新、设置监听(去获取数据)
         */
        mPullRefreshScrollView = (PullToRefreshScrollView) view.findViewById(R.id.ptr_label_main_scrollview);
        mPullRefreshScrollView.smoothScrollTo(0, 0);//将ScrollView滚动至最顶端（自定义的listview影响下的效果）
        mPullRefreshScrollView.setOnRefreshListener(this);
        return  view;
    }

   /*
   点击listview 的item 调用此方法
    */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

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

package oracle.mau.main.home.activity;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.ArrayList;
import java.util.List;

import oracle.mau.R;
import oracle.mau.base.BaseActivity;
import oracle.mau.entity.HomeEntity;
import oracle.mau.main.home.adapter.HomeAddAttentionAdapter;
import oracle.mau.view.ListViewForScrollView;


/**
 * Created by Administrator on 2017/3/1.
 */
public class HomeAddAttentionActivity extends BaseActivity implements AdapterView.OnItemClickListener,PullToRefreshBase.OnRefreshListener<ScrollView>  {
    ImageView home_back;
    ListViewForScrollView lv;
    HomeAddAttentionAdapter adapter;
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
    @Override
    public int getLayoutId() {
        return R.layout.activity_home_addattention;
    }

    @Override
    public void initView() {
        home_back=(ImageView) findViewById(R.id.home_back);
        home_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        lv=(ListViewForScrollView) findViewById(R.id.home_addattention_lv);
        adapter=new HomeAddAttentionAdapter(HomeAddAttentionActivity.this);
        lv.setAdapter(adapter);
        addData();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                toast("添加关注页面的listview的每个item");

            }
        });
        /**
         * 初始化下拉刷新、设置监听(去获取数据)
         */
        mPullRefreshScrollView = (PullToRefreshScrollView) findViewById(R.id.ptr_home_addattention);
        mPullRefreshScrollView.smoothScrollTo(0, 0);//将ScrollView滚动至最顶端（自定义的listview影响下的效果）
        mPullRefreshScrollView.setOnRefreshListener(this);

    }
    // 自定义数据加载的方法
    public void addData() {
        List<HomeEntity> list=new ArrayList<HomeEntity>();
        for (int i=0;i<20;i++){
            //假数据
            HomeEntity user=new HomeEntity();
            user.setUsername("测试用户名"+ i);
            user.setLikeNum("被喜欢"+1000+i+"次");
//            user.setPic(R.mipmap.mimi);//推荐用户头像
            list.add(user);
        }
        adapter.addData(list);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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
}

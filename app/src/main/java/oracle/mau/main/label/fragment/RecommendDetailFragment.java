package oracle.mau.main.label.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import oracle.mau.R;
import oracle.mau.base.BaseFragment;
import oracle.mau.entity.LabelRecommendDetailEntity;
import oracle.mau.entity.LabelRecommendEntity;
import oracle.mau.entity.LabelTagEntity;
import oracle.mau.entity.LabelTagNoListEntity;
import oracle.mau.main.label.activity.ArticleDetailActivity;
import oracle.mau.main.label.adapter.RecommendDetailLVAdapter;

/**
 * Created by 田帅 on 2017/3/7.
 */

public class RecommendDetailFragment extends BaseFragment implements PullToRefreshBase.OnRefreshListener , AdapterView.OnItemClickListener{

    private LabelTagNoListEntity labelTagEntity;
    /**
     * 带下拉刷新的listview
     */
    private PullToRefreshListView ptr_lv_recommend_detail;
    private final int LABEL_MAIN_PULL_UPDATE = 100001;
    private RecommendDetailLVAdapter mAdapter;
    /**
     * 数据源
     */
    private List<LabelRecommendDetailEntity> list;

    private TextView tttttttt;


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
                    ptr_lv_recommend_detail.onRefreshComplete();

                    break;
            }
        }
    };

    /**
     * 创建对象方法
     * @param labelTagEntity
     * @return
     */
    public static RecommendDetailFragment newInstance(LabelTagNoListEntity labelTagEntity) {
        RecommendDetailFragment f = new RecommendDetailFragment();
        Bundle b = new Bundle();
        b.putSerializable("tag", labelTagEntity);
        f.setArguments(b);
        return f;
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_recommend_detail;
    }

    @Override
    protected void initView() {
        /**
         * 获取当前对应的标签实体
         */
        Bundle bundle = getArguments();
        labelTagEntity = (LabelTagNoListEntity) bundle.getSerializable("tag");
        tttttttt = (TextView) rootView.findViewById(R.id.tttttttt);
        /**
         * listview
         */
        ptr_lv_recommend_detail = (PullToRefreshListView) rootView.findViewById(R.id.ptr_lv_recommend_detail);
        ptr_lv_recommend_detail.setOnRefreshListener(this);
        /**
         * 请求数据
         */
        requestDate();
    }

    private void requestDate() {
        /**
         * 请求之后
         */
        list = new ArrayList<>();

        mAdapter = new RecommendDetailLVAdapter(mContext,list);
        ptr_lv_recommend_detail.setAdapter(mAdapter);
        ptr_lv_recommend_detail.setOnItemClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        tttttttt.setText(labelTagEntity.getTagTitle());
        Log.d("asdasda",labelTagEntity.getTagTitle()+"fragment");
    }

    @Override
    public void onRefresh(PullToRefreshBase refreshView) {
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(mContext,ArticleDetailActivity.class);
        startActivity(intent);
//        ArticleDetailActivity.actionStart(mContext,list.get(position));
    }
}

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
import oracle.mau.entity.ArticleEntity;
import oracle.mau.entity.LabelRecommendDetailEntity;
import oracle.mau.entity.LabelRecommendEntity;
import oracle.mau.entity.LabelTagEntity;
import oracle.mau.entity.LabelTagNoListEntity;
import oracle.mau.http.bean.BeanData;
import oracle.mau.http.common.Callback;
import oracle.mau.http.common.HttpServer;
import oracle.mau.http.constants.URLConstants;
import oracle.mau.http.data.ArticleData;
import oracle.mau.http.parser.ArticleListParser;
import oracle.mau.main.label.activity.ArticleDetailActivity;
import oracle.mau.main.label.adapter.RecommendDetailLVAdapter;

/**
 * Created by 田帅 on 2017/3/7.
 */

public class RecommendDetailFragment extends BaseFragment implements PullToRefreshBase.OnRefreshListener , AdapterView.OnItemClickListener{

    private LabelTagNoListEntity labelTagEntity;
    private int tagId;
    /**
     * 带下拉刷新的listview
     */
    private PullToRefreshListView ptr_lv_recommend_detail;
    private RecommendDetailLVAdapter mAdapter;
    private int updateDown = 0;
    /**
     * 数据源
     */
    private List<ArticleEntity> list;


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
        tagId = labelTagEntity.getTagId();
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
        String url = URLConstants.BASE_URL + URLConstants.RECOMMEND_DETAIL + tagId;
        HttpServer.sendPostRequest(HttpServer.HTTPSERVER_GET, null, new ArticleListParser(), url, new Callback() {
            @Override
            public void success(BeanData beanData) {
                ArticleData data = (ArticleData) beanData;
                list = data.getArticleList();
                initLV();
                if (updateDown != 0){
                    //收起头部
                    ptr_lv_recommend_detail.onRefreshComplete();
                }
            }

            @Override
            public void failure(String error) {

            }
        });




    }

    private void initLV() {
        mAdapter = new RecommendDetailLVAdapter(mContext,list);
        ptr_lv_recommend_detail.setAdapter(mAdapter);
        ptr_lv_recommend_detail.setOnItemClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onRefresh(PullToRefreshBase refreshView) {
        updateDown++;
        requestDate();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ArticleDetailActivity.actionStart(mContext,list.get(position).getArticleId());
    }
}

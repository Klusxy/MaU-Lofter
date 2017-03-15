package oracle.mau.main.label.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import oracle.mau.R;
import oracle.mau.base.BaseFragment;
import oracle.mau.entity.LabelTagEntity;
import oracle.mau.entity.UserEntity;
import oracle.mau.http.bean.BeanData;
import oracle.mau.http.bean.BeanParser;
import oracle.mau.http.common.Callback;
import oracle.mau.http.common.HttpServer;
import oracle.mau.http.constants.URLConstants;
import oracle.mau.http.data.LabelTagData;
import oracle.mau.http.parser.TagParser;
import oracle.mau.main.label.activity.RecommendDetailActivity;
import oracle.mau.main.label.adapter.SearchLabelVPAdapter;
import oracle.mau.main.label.adapter.SearchTagAdapter;
import oracle.mau.main.label.constants.BroConstants;
import oracle.mau.utils.DensityUtils;
import oracle.mau.utils.KeyBoardUtils;

/**
 * Created by 田帅 on 2017/3/13.
 */

public class LabelSearchTagDetailFragment extends BaseFragment implements AdapterView.OnItemClickListener{

    /**
     * 广播
     */
    private UpdateUIBrocast uub = new UpdateUIBrocast();
    /**
     * 控件
     */
    private RelativeLayout rl_search_detail_tag_top;
    private TextView tv_search_detail_tag_flag;
    private ListView lv_search_detail_tag;
    private AVLoadingIndicatorView avi;
    /**
     * 数据源
     */
    private List<LabelTagEntity> tagList;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), RecommendDetailActivity.class);
        intent.putExtra("tag_id",tagList.get(position).getTagId());
        /**
         * 跳转之前
         * 发送广播告知主碎片关闭软键盘
         */
        Intent broIntent = new Intent(LabelFragment.LABEL_MAIN_CLOSE_KEY);
        getActivity().sendBroadcast(broIntent);
        startActivity(intent);
    }

    private class UpdateUIBrocast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            /**
             * 收到广播，通知请求数据
             */
            String content = intent.getStringExtra("content");
            //接收到广播之后请求数据
            tv_search_detail_tag_flag.setText("正在搜索 “" + content + "”");
            requestSearchContent(content);
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mContext.unregisterReceiver(uub);
    }
    /**
     * 请求数据
     * @param content 用户输入的内容
     */
    private void requestSearchContent(String content) {
        /**
         * 搜索时，显示出avi布局，隐藏 listview
         * 显示avi（在没得到结果时会隐藏avi，现在在显示出来）
         */
        avi.setVisibility(View.VISIBLE);
        rl_search_detail_tag_top.setVisibility(View.VISIBLE);
        lv_search_detail_tag.setVisibility(View.GONE);

        String url = URLConstants.BASE_URL + URLConstants.SEARCH_CONTENT + 1 + "/" + content;
        BeanParser parser = new TagParser();
        HttpServer.sendPostRequest(HttpServer.HTTPSERVER_GET, null, parser, url, new Callback() {
            @Override
            public void success(BeanData beanData) {
                LabelTagData data = (LabelTagData) beanData;
                tagList = data.getList();
                /**
                 * 得到数据之后，设置liewview
                 */
                updateUI();
            }

            @Override
            public void failure(String error) {

            }
        });

    }
    private void updateUI() {
        /**
         * 先判断list是否含有数据，如果有数据，则隐藏进度条
         * 否则隐藏avi，设置text
         */
        if (tagList == null || tagList.size()==0){
            avi.hide();
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(DensityUtils.dp2px(mContext,80),0,0,0);
            lp.addRule(RelativeLayout.CENTER_VERTICAL);
            tv_search_detail_tag_flag.setLayoutParams(lp);
            tv_search_detail_tag_flag.setText("没有找到相关结果");
        }else {
            rl_search_detail_tag_top.setVisibility(View.GONE);
            //用户碎片更新
            SearchTagAdapter mAdapter = new SearchTagAdapter(mContext, tagList);
            lv_search_detail_tag.setAdapter(mAdapter);
            lv_search_detail_tag.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_label_search_tag_detail;
    }

    @Override
    protected void initView() {
        tv_search_detail_tag_flag = (TextView) rootView.findViewById(R.id.tv_search_detail_tag_flag);
        rl_search_detail_tag_top = (RelativeLayout) rootView.findViewById(R.id.rl_search_detail_tag_top);
        lv_search_detail_tag = (ListView) rootView.findViewById(R.id.lv_search_detail_tag);
        lv_search_detail_tag.setOnItemClickListener(this);
        avi = (AVLoadingIndicatorView) rootView.findViewById(R.id.avi);
        //注册广播
        mContext.registerReceiver(uub, new IntentFilter(BroConstants.BRO_UPDATE_TAG));
    }
}

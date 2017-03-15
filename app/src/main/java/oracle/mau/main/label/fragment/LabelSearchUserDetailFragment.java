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
import oracle.mau.entity.UserEntity;
import oracle.mau.http.bean.BeanData;
import oracle.mau.http.bean.BeanParser;
import oracle.mau.http.common.Callback;
import oracle.mau.http.common.HttpServer;
import oracle.mau.http.constants.URLConstants;
import oracle.mau.http.data.HotUserData;
import oracle.mau.http.parser.HotUserParser;
import oracle.mau.main.account.activity.AccountDetailActivity;
import oracle.mau.main.label.adapter.SearchUserAdapter;
import oracle.mau.main.label.constants.BroConstants;
import oracle.mau.utils.DensityUtils;

/**
 * Created by 田帅 on 2017/3/5.
 */

public class LabelSearchUserDetailFragment extends BaseFragment implements AdapterView.OnItemClickListener{
    /**
     * 广播
     */
    private UpdateUIBrocast uub = new UpdateUIBrocast();
    /**
     * 控件
     */
    private RelativeLayout rl_flsd_top;
    private TextView tv_flsd_flag;
    private ListView lv_label_search_detail;
    private AVLoadingIndicatorView avi;
    /**
     * 数据源
     */
    private List<UserEntity> userList;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_label_search_user_detail;
    }

    @Override
    protected void initView() {
        rl_flsd_top = (RelativeLayout) rootView.findViewById(R.id.rl_search_detail_user_top);
        tv_flsd_flag = (TextView) rootView.findViewById(R.id.tv_search_detail_user_flag);
        lv_label_search_detail = (ListView) rootView.findViewById(R.id.lv_search_detail_user);
        lv_label_search_detail.setOnItemClickListener(this);
        avi = (AVLoadingIndicatorView) rootView.findViewById(R.id.avi);
        //注册广播
        mContext.registerReceiver(uub, new IntentFilter(BroConstants.BRO_UPDATE_USER));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mContext.unregisterReceiver(uub);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        /**
         * 跳转之前
         * 发送广播告知主碎片关闭软键盘
         */
        Intent broIntent = new Intent(LabelFragment.LABEL_MAIN_CLOSE_KEY);
        getActivity().sendBroadcast(broIntent);
        AccountDetailActivity.actionStart(mContext,userList.get(position).getUserid());
    }

    private class UpdateUIBrocast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            /**
             * 收到广播，通知请求数据
             */
            String content = intent.getStringExtra("content");
            //接收到广播之后请求数据
            tv_flsd_flag.setText("正在搜索 “" + content + "”");
            requestSearchContent(content);
        }
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
        rl_flsd_top.setVisibility(View.VISIBLE);
        lv_label_search_detail.setVisibility(View.GONE);

        String url = URLConstants.BASE_URL + URLConstants.SEARCH_CONTENT + 2 + "/" + content;
        BeanParser parser = new HotUserParser();
        HttpServer.sendPostRequest(HttpServer.HTTPSERVER_GET, null, parser, url, new Callback() {
            @Override
            public void success(BeanData beanData) {
                HotUserData data = (HotUserData) beanData;
                userList = data.getUserList();
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
        if (userList == null || userList.size()==0){
            avi.hide();
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(DensityUtils.dp2px(mContext,80),0,0,0);
            lp.addRule(RelativeLayout.CENTER_VERTICAL);
            tv_flsd_flag.setLayoutParams(lp);
            tv_flsd_flag.setText("没有找到相关结果");
        }else {
            rl_flsd_top.setVisibility(View.GONE);
            //用户碎片更新
            SearchUserAdapter mAdapter = new SearchUserAdapter(mContext, userList);
            lv_label_search_detail.setAdapter(mAdapter);
            lv_label_search_detail.setVisibility(View.VISIBLE);
        }
    }

}

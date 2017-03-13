package oracle.mau.main.label.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
import oracle.mau.main.label.adapter.SearchUserAdapter;

/**
 * Created by 田帅 on 2017/3/5.
 */

public class LabelSearchDetailFragment extends BaseFragment {
    /**
     * 广播
     */
    public static String UPDATE_SEARCH_UI_BROAD = "updateBroad";
    private UpdateUIBrocast uub = new UpdateUIBrocast();
    /**
     * 控件
     */
    private RelativeLayout rl_flsd_top;
    private TextView tv_flsd_flag;
    private ListView lv_label_search_detail;

    /**
     * 数据源
     */
    //用户
    private List<UserEntity> userList;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_label_search_detail;
    }

    @Override
    protected void initView() {
        rl_flsd_top = (RelativeLayout) rootView.findViewById(R.id.rl_flsd_top);
        tv_flsd_flag = (TextView) rootView.findViewById(R.id.tv_flsd_flag);
        lv_label_search_detail = (ListView) rootView.findViewById(R.id.lv_label_search_detail);
        //注册广播
        mContext.registerReceiver(uub,new IntentFilter(UPDATE_SEARCH_UI_BROAD));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mContext.unregisterReceiver(uub);
    }

    private class UpdateUIBrocast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            /**
             * 收到广播，通知请求数据
             * 0为标签  1为用户  2为专题
             */
            int position = intent.getIntExtra("position",0);
            String content = intent.getStringExtra("content");
            //接收到广播之后请求数据
            tv_flsd_flag.setText("正在搜索 “"+content+"”");
            requestSearchContent(position,content);
        }
    }

    /**
     * 请求数据
     * @param position  当前页卡的位置
     * @param content   用户输入的内容
     */
    private void requestSearchContent(final int position, String content) {
        rl_flsd_top.setVisibility(View.VISIBLE);
        /**
         * 让 position +1 得到请求所需的类型
         */
        int type = position+1;
        String url = URLConstants.BASE_URL + URLConstants.SEARCH_CONTENT + type + "/" + content;
        BeanParser parser = null;
        switch (type) {
            case 1:

                break;
            case 2:
                parser = new HotUserParser();
                HttpServer.sendPostRequest(HttpServer.HTTPSERVER_GET, null, parser, url, new Callback() {
                    @Override
                    public void success(BeanData beanData) {
                        HotUserData data = (HotUserData) beanData;
                        userList = data.getUserList();
                        /**
                         * 得到数据之后，隐藏进度条，设置liewview
                         */
                        rl_flsd_top.setVisibility(View.GONE);

                        updateUI(position);
                    }

                    @Override
                    public void failure(String error) {

                    }
                });
                break;
            case 3:

                break;
        }

    }

    private void updateUI(int position) {
        switch (position) {
            case 0:
                //标签碎片更新

                break;
            case 1:
                //用户碎片更新
                SearchUserAdapter mAdapter = new SearchUserAdapter(mContext,userList);
                lv_label_search_detail.setAdapter(mAdapter);
                break;
            case 2:
                //文章碎片更新

                break;
        }
    }

}

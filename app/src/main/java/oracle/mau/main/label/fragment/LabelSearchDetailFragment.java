package oracle.mau.main.label.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import oracle.mau.R;
import oracle.mau.base.BaseFragment;

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

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_label_search_detail;
    }

    @Override
    protected void initView() {
        rl_flsd_top = (RelativeLayout) rootView.findViewById(R.id.rl_flsd_top);
        tv_flsd_flag = (TextView) rootView.findViewById(R.id.tv_flsd_flag);
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
             * 收到广播，显示隐藏控件，通知请求数据
             */
            rl_flsd_top.setVisibility(View.VISIBLE);
            int position = intent.getIntExtra("position",0);
            String content = intent.getStringExtra("content");
            //接收到广播之后请求数据
            tv_flsd_flag.setText("正在搜索 “"+content+"”");
            // 更新UI
            updateUI(position);
        }
    }

    private void updateUI(int position) {
        switch (position) {
            case 0:
                //标签碎片更新

                break;
            case 1:
                //用户碎片更新

                break;
            case 2:
                //文章碎片更新

                break;
        }
    }

}

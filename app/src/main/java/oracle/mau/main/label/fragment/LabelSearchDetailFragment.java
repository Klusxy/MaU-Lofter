package oracle.mau.main.label.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_label_search_detail;
    }

    @Override
    protected void initView() {
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
             * 发送广播，通知请求数据
             */
            int position = intent.getIntExtra("position",0);
            //接收到广播之后更新UI
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

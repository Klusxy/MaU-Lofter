package oracle.mau.main.home.fragment;

import oracle.mau.R;
import oracle.mau.base.BaseFragment;

/**
 * Created by Administrator on 2017/3/2.
 */

public class SubscriptionFragment extends BaseFragment {
    int type;
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_homesubscription;
    }

    @Override
    protected void initView() {
        type=getArguments().getInt("type",-1);
        toast(type+"--------");
    }
}

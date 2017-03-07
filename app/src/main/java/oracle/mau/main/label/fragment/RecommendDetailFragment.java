package oracle.mau.main.label.fragment;

import android.os.Bundle;

import oracle.mau.R;
import oracle.mau.base.BaseFragment;

/**
 * Created by 田帅 on 2017/3/7.
 */

public class RecommendDetailFragment extends BaseFragment {
    private static final String ARG_POSITION = "position";

    private int position;
    public static RecommendDetailFragment newInstance(int position) {
        RecommendDetailFragment f = new RecommendDetailFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_recommend_detail;
    }

    @Override
    protected void initView() {

    }
}

package oracle.mau.main.label.fragment;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import oracle.mau.R;
import oracle.mau.base.BaseFragment;
import oracle.mau.entity.LabelTagEntity;

/**
 * Created by 田帅 on 2017/3/7.
 */

public class RecommendDetailFragment extends BaseFragment {

    private LabelTagEntity labelTagEntity;
    private TextView tttttttt;

    public static RecommendDetailFragment newInstance(LabelTagEntity labelTagEntity) {
        RecommendDetailFragment f = new RecommendDetailFragment();
        Bundle b = new Bundle();
        b.putSerializable("tag",labelTagEntity);
        f.setArguments(b);
        return f;
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_recommend_detail;
    }

    @Override
    protected void initView() {
        Bundle bundle = getArguments();
        labelTagEntity = (LabelTagEntity) bundle.getSerializable("tag");
        tttttttt = (TextView) rootView.findViewById(R.id.tttttttt);
    }

    @Override
    public void onStart() {
        super.onStart();
        tttttttt.setText(labelTagEntity.getTagTitle());
        Log.d("asdasda",labelTagEntity.getTagTitle()+"fragment");
    }
}

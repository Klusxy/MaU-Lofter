package oracle.mau.main.label.activity;

import oracle.mau.R;
import oracle.mau.base.BaseActivity;
import oracle.mau.entity.LabelTagEntity;

/**
 * Created by 田帅 on 2017/3/6.
 */

public class RecommendDetailActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_recommend_detail;
    }

    @Override
    public void initView() {
        LabelTagEntity tagEntity = (LabelTagEntity) getIntent().getExtras().getSerializable("tag");
        toast(tagEntity.getTagTitle());
    }
}

package oracle.mau.main.label.activity;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import oracle.mau.R;
import oracle.mau.base.BaseActivity;
import oracle.mau.entity.LabelTagEntity;
import oracle.mau.main.label.adapter.RDCategoryItemVPAdapter;
import oracle.mau.view.CategoryTabStrip;

/**
 * Created by 田帅 on 2017/3/6.
 */

public class RecommendDetailActivity extends BaseActivity implements View.OnClickListener{
    private ImageView iv_rd_back;

    /**
     * 选项卡
     */
    private CategoryTabStrip tabs;
    private ViewPager pager;
    private RDCategoryItemVPAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_recommend_detail;
    }

    @Override
    public void initView() {
        /**
         * 得到传过来的标签实体
         */
        LabelTagEntity tagEntity = (LabelTagEntity) getIntent().getExtras().getSerializable("tag");
        iv_rd_back = (ImageView) findViewById(R.id.iv_rd_back);
        iv_rd_back.setOnClickListener(this);

        tabs = (CategoryTabStrip) findViewById(R.id.cs_recommend_detail);
        pager = (ViewPager) findViewById(R.id.vp_recommend_detail);
        adapter = new RDCategoryItemVPAdapter(getSupportFragmentManager(),this);

        pager.setAdapter(adapter);

        tabs.setViewPager(pager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_rd_back:
                finish();
                break;
        }
    }
}

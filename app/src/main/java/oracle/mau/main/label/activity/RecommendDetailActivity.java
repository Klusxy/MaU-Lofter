package oracle.mau.main.label.activity;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

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
    private CategoryTabStrip mCategoryTabStrip;
    private ViewPager mViewPager;
    private RDCategoryItemVPAdapter mAdapter;

    /**
     * 选择频道   调整顺序layout
     */
    private RelativeLayout rl_rd_category_select_channel_layout;

    /**
     * 显示隐藏 选择xxxx布局的imageview
     */
    private ImageView iv_rd_expand;
    private int expandFlag = 0;  //显示隐藏的标记
    private Animation mShowAnim ;
    private Animation mHiddenAnim ;
    private Animation mRotateDownToUpAnim;
    private Animation mRotateUpToDowmAnim;


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
        rl_rd_category_select_channel_layout = (RelativeLayout) findViewById(R.id.rl_rd_category_select_channel_layout);
        iv_rd_expand = (ImageView) findViewById(R.id.iv_rd_expand);
        iv_rd_expand.setOnClickListener(this);
        //初始化选项卡和viewpager
        initTabCategoryWithVP();

    }

    /**
     * 初始化选项卡和viewpager
     */
    private void initTabCategoryWithVP() {
        //初始化动画
        initAnimations();
        mCategoryTabStrip = (CategoryTabStrip) findViewById(R.id.cs_recommend_detail);
        mViewPager = (ViewPager) findViewById(R.id.vp_recommend_detail);
        mAdapter = new RDCategoryItemVPAdapter(getSupportFragmentManager(),this);
        mViewPager.setAdapter(mAdapter);
        mCategoryTabStrip.setViewPager(mViewPager);
    }

    private void initAnimations()
    {
        mShowAnim = AnimationUtils.loadAnimation(this, R.anim.category_item_show_anim);
        mHiddenAnim = AnimationUtils.loadAnimation(this, R.anim.category_item_hidden_anim);
        mRotateDownToUpAnim = AnimationUtils.loadAnimation(this,R.anim.category_right_expand_rotate_down_to_up_anim);
        mRotateUpToDowmAnim = AnimationUtils.loadAnimation(this,R.anim.category_right_expand_rotate_up_to_down_anim);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /**
             * 返回按钮
             */
            case R.id.iv_rd_back:
                finish();
                break;
            /**
             * 显示隐藏 选择xxxx布局的imageview
             */
            case R.id.iv_rd_expand:

                if (expandFlag == 0) {
                    /**
                     * 添加显示隐藏动画效果
                     */
                    mCategoryTabStrip.setVisibility(View.GONE);
                    mCategoryTabStrip.startAnimation(mHiddenAnim);
                    rl_rd_category_select_channel_layout.setVisibility(View.VISIBLE);
                    rl_rd_category_select_channel_layout.startAnimation(mShowAnim);
                    /**
                     * 添加按钮的旋转动画效果
                     */
                    iv_rd_expand.startAnimation(mRotateDownToUpAnim);
                    expandFlag = 1;
                }else {
                    /**
                     * 添加显示隐藏动画效果
                     */
                    mCategoryTabStrip.setVisibility(View.VISIBLE);
                    mCategoryTabStrip.startAnimation(mShowAnim);
                    rl_rd_category_select_channel_layout.setVisibility(View.GONE);
                    rl_rd_category_select_channel_layout.startAnimation(mHiddenAnim);
                    expandFlag = 0;
                    /**
                     * 添加按钮的旋转动画效果
                     */
                    iv_rd_expand.startAnimation(mRotateUpToDowmAnim);
                }
                break;
        }
    }
}

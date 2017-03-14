package oracle.mau.main.label.activity;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import oracle.mau.R;
import oracle.mau.base.BaseActivity;
import oracle.mau.entity.LabelTagEntity;
import oracle.mau.entity.LabelTagNoListEntity;
import oracle.mau.main.label.adapter.RDCategoryItemVPAdapter;
import oracle.mau.main.label.adapter.RDDragListViewAdapter;
import oracle.mau.main.label.fragment.RecommendDetailFragment;
import oracle.mau.main.label.pop.SelectChannelPop;
import oracle.mau.view.CategoryTabStrip;

/**
 * Created by 田帅 on 2017/3/6.
 */

public class RecommendDetailActivity extends BaseActivity implements View.OnClickListener {
    private List<LabelTagNoListEntity> tagList;
    private int position;

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
     * 调整顺序按钮
     */
    private TextView tv_rd_adjust_position;
    private int adjustFlag = 1;
    private TextView tv_rd_adjust_position_flag;

    /**
     * 显示隐藏 选择xxxx布局的imageview
     */
    private ImageView iv_rd_expand;
    private int expandFlag = 0;  //显示隐藏的标记
    private Animation mShowAnim;
    private Animation mHiddenAnim;
    private Animation mRotateDownToUpAnim;
    private Animation mRotateUpToDownAnim;
    private SelectChannelPop selectChannelPop;

    @Override
    public int getLayoutId() {
        return R.layout.activity_recommend_detail;
    }

    @Override
    public void initView() {
        /**
         * 得到所有的标签集合
         */
        this.tagList = (List<LabelTagNoListEntity>) getIntent().getSerializableExtra("all");
        for (LabelTagNoListEntity ll : tagList) {
            Log.d("asdasda", ll.getTagTitle() + "  1111  \n");
        }
        /**
         * 得到传过来的标签实体
         */
//        LabelTagEntity tagEntity = (LabelTagEntity) getIntent().getExtras().getSerializable("tag");
        /**
         * 得到position
         */
        position = getIntent().getIntExtra("position", -1);

        iv_rd_back = (ImageView) findViewById(R.id.iv_rd_back);
        iv_rd_back.setOnClickListener(this);
        rl_rd_category_select_channel_layout = (RelativeLayout) findViewById(R.id.rl_rd_category_select_channel_layout);
        iv_rd_expand = (ImageView) findViewById(R.id.iv_rd_expand);
        iv_rd_expand.setOnClickListener(this);
        tv_rd_adjust_position = (TextView) findViewById(R.id.tv_rd_adjust_position);
        tv_rd_adjust_position.setOnClickListener(this);
        tv_rd_adjust_position_flag = (TextView) findViewById(R.id.tv_rd_adjust_position_flag);
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
        mAdapter = new RDCategoryItemVPAdapter(getSupportFragmentManager(), this, tagList);
        mViewPager.setAdapter(mAdapter);
        mCategoryTabStrip.setViewPager(mViewPager);
        mViewPager.setCurrentItem(position);
    }

    private void initAnimations() {
        mShowAnim = AnimationUtils.loadAnimation(this, R.anim.category_item_show_anim);
        mHiddenAnim = AnimationUtils.loadAnimation(this, R.anim.category_item_hidden_anim);
        mRotateDownToUpAnim = AnimationUtils.loadAnimation(this, R.anim.category_right_expand_rotate_down_to_up_anim);
        mRotateUpToDownAnim = AnimationUtils.loadAnimation(this, R.anim.category_right_expand_rotate_up_to_down_anim);
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
                     * 显示pop
                     */
                    showPop();
                } else {
                    /**
                     * 隐藏pop
                     */
                    hiddenPop();
                }
                break;
            /**
             * 调整顺序按钮
             */
            case R.id.tv_rd_adjust_position:
                /**
                 * 显示拖拽按钮
                 */
                if (adjustFlag % 2 == 1) {
                    tv_rd_adjust_position.setText("完成");
                    tv_rd_adjust_position_flag.setText("按住右边的按钮拖动排序");
                    for (int i = 0; i < tagList.size(); i++) {
                        View convertView = selectChannelPop.getmDragSortListView().getChildAt(i);
                        ImageView iv_dsl_drag_selected = (ImageView) convertView.findViewById(R.id.iv_dsl_drag_selected);
                        ImageView iv_dsl_drag_sort = (ImageView) convertView.findViewById(R.id.iv_dsl_drag_sort);
                        iv_dsl_drag_sort.setVisibility(View.VISIBLE);
                        iv_dsl_drag_selected.setVisibility(View.GONE);
                    }
                } else {
                    tv_rd_adjust_position.setText("调整顺序");
                    tv_rd_adjust_position_flag.setText("选择频道");
                    for (int i = 0; i < tagList.size(); i++) {
                        View convertView = selectChannelPop.getmDragSortListView().getChildAt(i);
                        ImageView iv_dsl_drag_selected = (ImageView) convertView.findViewById(R.id.iv_dsl_drag_selected);
                        ImageView iv_dsl_drag_sort = (ImageView) convertView.findViewById(R.id.iv_dsl_drag_sort);
                        iv_dsl_drag_sort.setVisibility(View.GONE);
                        LabelTagNoListEntity ll = (LabelTagNoListEntity) selectChannelPop.getmAdapter().getItem(i);
                        if (i == ll.getSelectPosition()) {
                            iv_dsl_drag_selected.setVisibility(View.VISIBLE);
                        }
                    }
                }
                adjustFlag++;
                break;
        }
    }

    private void showPop() {
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
        /**
         * 弹出pop
         */
        selectChannelPop = new SelectChannelPop(this, new SelectChannelPop.ItemClickListener() {
            @Override
            public void onItemClick(String tagTitle, int posi) {
                hiddenPop();
                /**
                 * 改变选中的postion
                 */
                for (LabelTagNoListEntity ll : tagList) {
                    ll.setSelectPosition(posi);
                    ll.setDrag(false);
                }
                /**
                 * 设置当前滚动条的位置
                 * 拖拽之后，tagList也会变，  notifiChange不会刷新，所以先制空，在重创建
                 */
                mAdapter = null;
                mAdapter = new RDCategoryItemVPAdapter(RecommendDetailActivity.this.getSupportFragmentManager(), mContext, tagList);
                mViewPager.setAdapter(mAdapter);
                mCategoryTabStrip.setViewPager(mViewPager);
                position = posi;
                mViewPager.setCurrentItem(posi);
            }
        }, tagList, position);
        selectChannelPop.showAsDropDown(iv_rd_expand);

//        selectChannelPop.showAtLocation(iv_rd_expand,Gravity.BOTTOM,0,0);
    }

    private void hiddenPop() {
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
        iv_rd_expand.startAnimation(mRotateUpToDownAnim);
        /**
         * 隐藏pop
         */
        selectChannelPop.dismiss();
        tv_rd_adjust_position.setText("调整顺序");
        tv_rd_adjust_position_flag.setText("选择频道");
        adjustFlag++;
    }
}

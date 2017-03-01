package oracle.mau.main.label.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import oracle.mau.R;
import oracle.mau.base.BaseFragment;
import oracle.mau.utils.KeyBoardUtils;
import oracle.mau.utils.ScreenUtils;

/**
 * Created by 田帅 on 2017/2/20.
 */

public class LabelFragment extends BaseFragment implements View.OnClickListener, TextWatcher {

    /**
     * 设置点击是否搜集的标记
     */
    private int searchFlag = 1;
    //圆角矩形背景的相对布局
    private RelativeLayout rl_label_top;
    //搜索框
    private EditText et_label_search;
    //取消按钮
    private TextView tv_label_cancel;
    //添加关注按钮
    private ImageView iv_label_add_attention;

    //搜索框的tv  为了实现动画效果
    private TextView tv_label_search_text;
    // 包含搜索icon和tv的相对布局  为了实现动画效果
    private RelativeLayout rl_label_top_left;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_label;
    }

    @Override
    protected void initView() {
        et_label_search = (EditText) rootView.findViewById(R.id.et_label_search);
        rl_label_top = (RelativeLayout) rootView.findViewById(R.id.rl_label_top_left_bg);
        rl_label_top.setOnClickListener(this);
        replaceFragment(new LabelMainFragment(), R.id.fl_label_fg);
        tv_label_cancel = (TextView) rootView.findViewById(R.id.tv_label_cancel);
        tv_label_cancel.setOnClickListener(this);
        tv_label_search_text = (TextView) rootView.findViewById(R.id.tv_label_search_text);
        et_label_search.addTextChangedListener(this);
        rl_label_top_left = (RelativeLayout) rootView.findViewById(R.id.rl_label_top_left);
        /**
         * 动态设置包含搜索icon和tv的相对布局宽度和规则  为了实现动画效果
         */
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ScreenUtils.getScreenWidth(mContext) / 2, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        lp.addRule(RelativeLayout.CENTER_VERTICAL);
        rl_label_top_left.setLayoutParams(lp);
        iv_label_add_attention = (ImageView) rootView.findViewById(R.id.iv_label_add_attention);
        iv_label_add_attention.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /**
             * 切换到主碎片按钮
             */
            case R.id.tv_label_cancel:
                //替换碎片，将取消按钮设为不可见，标记+1
                replaceFragment(new LabelMainFragment(), R.id.fl_label_fg);
                tv_label_cancel.setVisibility(View.GONE);
                et_label_search.setVisibility(View.GONE);
                iv_label_add_attention.setVisibility(View.VISIBLE);
                et_label_search.setText("");
                startToRightAnim();
                //弹回软键盘
                KeyBoardUtils.closeKeybord(et_label_search, mContext);
                searchFlag++;
                break;
            /**
             * 切换到搜索碎片按钮
             */
            case R.id.rl_label_top_left_bg:
                if (searchFlag % 2 == 1) {
                    //表示点击切换到搜索碎片
                    replaceFragment(new LabelSearchFragment(), R.id.fl_label_fg);
                    tv_label_cancel.setVisibility(View.VISIBLE);
                    et_label_search.setVisibility(View.VISIBLE);
                    iv_label_add_attention.setVisibility(View.GONE);
                    /**
                     * 平移动画
                     */
                    startToLeftAnim();
                    //弹起软键盘
                    KeyBoardUtils.openKeybord(et_label_search, mContext);
                    searchFlag++;
                }
                break;
            /**
             * 添加关注按钮
             */
            case R.id.iv_label_add_attention:
                //判断是否登陆
                //跳转添加关注页面
                toast("啊啊啊啊");
                break;
        }
    }

    /**
     * 顶部文字和图片水平向右移动动画
     */
    private void startToRightAnim() {
        AnimationSet animationSet = new AnimationSet(true);
        TranslateAnimation translateAnimation = new TranslateAnimation(
                //X轴初始位置
                Animation.RELATIVE_TO_SELF, -0.42f,
                //X轴移动的结束位置
                Animation.RELATIVE_TO_SELF, 0.0f,
                //y轴开始位置
                Animation.RELATIVE_TO_SELF, 0.0f,
                //y轴移动后的结束位置
                Animation.RELATIVE_TO_SELF, 0.0f);
        //0.4秒完成动画
        translateAnimation.setDuration(400);
        //如果fillAfter的值为真的话，动画结束后，控件停留在执行后的状态
        animationSet.setFillAfter(true);
        //将AlphaAnimation这个已经设置好的动画添加到 AnimationSet中
        animationSet.addAnimation(translateAnimation);
        //启动动画
        rl_label_top_left.startAnimation(animationSet);
    }

    /**
     * 顶部文字和图片水平向左移动动画
     */
    private void startToLeftAnim() {

        /*??????????????????????????????????????????????????????????????
        //相对布局整体控件的宽度
        int tv_width = ScreenUtils.getScreenWidth(mContext)/2;
        //移动自身一半到达屏幕最左端  0.5倍
        //但是需要留出一定的margin   距离左边17dp
        //总共需要移动的px
        int moveX = tv_width - DensityUtils.dp2px(mContext,8);
        //算出比例
        float topPro =  (float) moveX/((float) (tv_width*2));
        //0.04  8dp
        */

        AnimationSet animationSet = new AnimationSet(true);
        TranslateAnimation translateAnimation = new TranslateAnimation(
                //X轴初始位置
                Animation.RELATIVE_TO_SELF, 0.0f,
                //X轴移动的结束位置
                Animation.RELATIVE_TO_SELF, -0.42f,
                //y轴开始位置
                Animation.RELATIVE_TO_SELF, 0.0f,
                //y轴移动后的结束位置
                Animation.RELATIVE_TO_SELF, 0.0f
        );
        //0.4秒完成动画
        translateAnimation.setDuration(400);
        //如果fillAfter的值为真的话，动画结束后，控件停留在执行后的状态
        animationSet.setFillAfter(true);
        //将AlphaAnimation这个已经设置好的动画添加到 AnimationSet中
        animationSet.addAnimation(translateAnimation);
        //启动动画
        rl_label_top_left.startAnimation(animationSet);
    }

    /**
     * @param fg 需要替换的碎片
     * @param id 需要替换的位置
     */
    private void
    replaceFragment(Fragment fg, int id) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(id, fg);
        ft.commit();
    }

    /**
     * edittext的监听事件
     * @param s
     * @param start
     * @param count
     * @param after
     */
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!"".equals(et_label_search.getText().toString())) {
            tv_label_search_text.setText("");
        } else {
            tv_label_search_text.setText("搜索标签、用户、标题");
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}

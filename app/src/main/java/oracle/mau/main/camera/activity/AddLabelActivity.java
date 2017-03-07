package oracle.mau.main.camera.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import oracle.mau.R;
import oracle.mau.base.BaseActivity;
import oracle.mau.entity.LabelTagEntity;
import oracle.mau.main.camera.utils.DensityUtils;
import oracle.mau.view.WarpLinearLayout;

/**
 * Created by 田帅 on 2017/3/3.
 */

public class AddLabelActivity extends BaseActivity implements View.OnClickListener {
    //完成按钮
    private TextView tv_al_complete;
    private List<LabelTagEntity> labelsList ;
    private List<LabelTagEntity> resultList;
    private List<TextView> labelViewList;
    private WarpLinearLayout wll_al_labels;
    //重置按钮
    private Button btn_al_reset;
    public final static int ADDLABEL_BACKCODE = 10004;

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_label;
    }

    @Override
    public void initView() {
        tv_al_complete = (TextView) findViewById(R.id.tv_al_complete);
        tv_al_complete.setOnClickListener(this);
        wll_al_labels = (WarpLinearLayout) findViewById(R.id.wll_al_labels);
        btn_al_reset = (Button) findViewById(R.id.btn_al_reset);
        btn_al_reset.setOnClickListener(this);
        //初始化标签信息集合
        initLabelsList();
        //初始化回传信息，默认都在
        initResultInfo();
        //初始化线性布局中的内容
        initLabels();
    }

    /**
     * 初始化标签信息集合
     */
    private void initLabelsList() {
        labelsList = new ArrayList<>();
        LabelTagEntity e1 = new LabelTagEntity();
        e1.setTagId(1);
        e1.setTagTitle("摄影天堂");
        labelsList.add(e1);

        LabelTagEntity e2 = new LabelTagEntity();
        e2.setTagId(2);
        e2.setTagTitle("电影");
        labelsList.add(e2);

        LabelTagEntity e3 = new LabelTagEntity();
        e3.setTagId(3);
        e3.setTagTitle("说走就走的旅行");
        labelsList.add(e3);

        LabelTagEntity e4 = new LabelTagEntity();
        e4.setTagId(4);
        e4.setTagTitle("独一无二的设计");
        labelsList.add(e4);

        LabelTagEntity e5 = new LabelTagEntity();
        e5.setTagId(5);
        e5.setTagTitle("女神");
        labelsList.add(e5);

        LabelTagEntity e6 = new LabelTagEntity();
        e6.setTagId(6);
        e6.setTagTitle("运动圈");
        labelsList.add(e6);

        LabelTagEntity e7 = new LabelTagEntity();
        e7.setTagId(7);
        e7.setTagTitle("娱乐");
        labelsList.add(e7);

        LabelTagEntity e8 = new LabelTagEntity();
        e8.setTagId(8);
        e8.setTagTitle("穿搭");
        labelsList.add(e8);
    }

    private void initResultInfo() {
        if (resultList == null) {
            resultList = new ArrayList<>();
        }else {
            resultList.clear();
        }
        for (int i = 0; i < labelsList.size(); i++) {
            resultList.add(labelsList.get(i));
        }
    }

    /**
     * 初始化线性布局
     */
    private void initLabels() {
        labelViewList = new ArrayList<>();
        for (int i = 0; i < labelsList.size(); i++) {
            TextView mTextView = new TextView(this);
            //给textview设置文字
            mTextView.setText(labelsList.get(i).getTagTitle());
            //给textview设置背景（带边框的圆角矩形）
            mTextView.setBackgroundResource(R.drawable.camera_add_label_tv_solid_oval_bg);
            //设置textview的外边距和内边距
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(DensityUtils.dp2px(this, 10), DensityUtils.dp2px(this, 10), DensityUtils.dp2px(this, 10), DensityUtils.dp2px(this, 10));
            mTextView.setPadding(DensityUtils.dp2px(this, 10), DensityUtils.dp2px(this, 5), DensityUtils.dp2px(this, 10), DensityUtils.dp2px(this, 5));
            mTextView.setLayoutParams(lp);
            //将textview添加入集合中，为了重置按钮遍历
            labelViewList.add(mTextView);
            //将textview添加到线性布局中
            wll_al_labels.addView(mTextView);
            mTextView.setTag(i);

            //textview的点击事件
            mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LabelTagEntity e = new LabelTagEntity();
                    e.setTagId(-1);
                    e.setTagTitle("");
                    resultList.set((int) v.getTag(),e);
                    v.setVisibility(View.GONE);
                }
            });

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /**
             * 完成按钮
             */
            case R.id.tv_al_complete:

                List<LabelTagEntity> list = new ArrayList<>();
                //将标签的信息回传
                for (LabelTagEntity e : resultList) {

                    if (e.getTagId()!=-1) {
                        list.add(e);
                    }
                }
                if (list.size() < 1) {
                    toast("标签不能为空");
                }else if (list.size() > 1) {
                    toast("只能选择一个标签");
                }else {
                    int labelTypeId = list.get(0).getTagId();
                    String label = list.get(0).getTagTitle();
                    Intent intent = new Intent();
                    intent.putExtra("labelTypeId",labelTypeId);
                    intent.putExtra("label",label);
                    setResult(ADDLABEL_BACKCODE,intent);
                    finish();
                }

                break;
            case R.id.btn_al_reset:
                for (TextView tv : labelViewList) {
                    tv.setVisibility(View.VISIBLE);
                    //将所有内容重新加入回传信息集合中
                    initResultInfo();
                }
                break;
        }
    }
}

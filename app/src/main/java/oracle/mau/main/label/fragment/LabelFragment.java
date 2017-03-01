package oracle.mau.main.label.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import oracle.mau.R;
import oracle.mau.base.BaseFragment;
import oracle.mau.utils.KeyBoardUtils;

/**
 * Created by 田帅 on 2017/2/20.
 */

public class LabelFragment extends BaseFragment implements View.OnClickListener{

    /**
     * 设置点击是否搜集的标记
     */
    private int searchFlag = 1;

    private EditText et_label_search;
    private TextView tv_label_cancle;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_label;
    }

    @Override
    protected void initView() {
        et_label_search = (EditText) rootView.findViewById(R.id.et_label_search);
        et_label_search.setOnClickListener(this);
        replaceFragment(new LabelMainFragment(),R.id.fl_label_fg);
        tv_label_cancle = (TextView) rootView.findViewById(R.id.tv_label_cancle);
        tv_label_cancle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.et_label_search:
                if (searchFlag%2==1) {
                    //表示点击切换到搜索碎片
                    replaceFragment(new LabelSearchFragment(),R.id.fl_label_fg);
                    tv_label_cancle.setVisibility(View.VISIBLE);
                    searchFlag++;
                }
                break;
            case R.id.tv_label_cancle:
                //替换碎片，将取消按钮设为不可见，标记+1
                replaceFragment(new LabelMainFragment(),R.id.fl_label_fg);
                tv_label_cancle.setVisibility(View.GONE);
                searchFlag++;
                //弹回软键盘
                KeyBoardUtils.closeKeybord(et_label_search,mContext);
                break;
        }
    }

    /**
     *
     * @param fg   需要替换的碎片
     * @param id  需要替换的位置
     */
    private void
    replaceFragment(Fragment fg, int id) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(id,fg);
        ft.commit();
    }
}

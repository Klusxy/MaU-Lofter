package oracle.mau.main.message;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import oracle.mau.R;
import oracle.mau.base.BaseFragment;
import oracle.mau.main.message.adapter.MsgFragmentPageAdapter;
import oracle.mau.main.message.fragment.DynamicFragment;
import oracle.mau.main.message.fragment.NewsFragment;
import oracle.mau.main.message.fragment.TalkFragment;

/**
 * Created by 田帅 on 2017/2/20.
 */

public class MessageFragment  extends  Fragment implements View.OnClickListener{
    private static ViewPager viewPager;
    private ArrayList<Fragment> list;
    private static TextView textView1;
    private TextView textView2;
    private TextView textView3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_message,null);

        viewPager = (ViewPager) view.findViewById(R.id.ViewPager1);

        textView1 = (TextView) view.findViewById(R.id.textView1);
        textView1.setSelected(true);
        textView2 = (TextView) view.findViewById(R.id.textView2);
        textView3 = (TextView) view.findViewById(R.id.textView3);
        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);

        //默认选中第一个界面
        viewPager.setCurrentItem(0);
        textView1.setSelected(true);
        viewPager.setOnPageChangeListener(listener1);

        list = new ArrayList<Fragment>();
        for (int i = 0; i < 3; i++) {
            if(i==0){
                list.add(new DynamicFragment());
            }
            if(i==1){
                list.add(new NewsFragment());
            }
            if(i==2){
                list.add(new TalkFragment());
            }

        }
        viewPager.setAdapter(new MsgFragmentPageAdapter(getChildFragmentManager(),
                list));


        return view;
    }

    public ViewPager.OnPageChangeListener listener1=new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int arg0) {
            if (arg0 == 0) {
                textView1.setSelected(true);
                textView2.setSelected(false);
                textView3.setSelected(false);
            }
            if (arg0 == 1) {
                textView1.setSelected(false);
                textView2.setSelected(true);
                textView3.setSelected(false);
            }
            if (arg0 == 2) {
                textView1.setSelected(false);
                textView2.setSelected(false);
                textView3.setSelected(true);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }


    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textView1:
                viewPager.setCurrentItem(0);
                break;
            case R.id.textView2:
                viewPager.setCurrentItem(1);
                break;
            case R.id.textView3:
                viewPager.setCurrentItem(2);
                break;

            default:
                break;
        }
    }
}



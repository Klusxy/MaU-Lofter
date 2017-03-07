package oracle.mau.main.message;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
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
import oracle.mau.main.message.mqtt.PushService;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by 田帅 on 2017/2/20.
 */

public class MessageFragment  extends Fragment {
    private TabLayout tab;
    private ViewPager viewpager;
    private MsgFragmentPageAdapter adapter;
    public static final String[] tabTitle = new String[]{"动态", "消息", "聊天"};
    @Nullable

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_message,null);

        tab = (TabLayout) view.findViewById(R.id.tab);
        viewpager = (ViewPager)view. findViewById(R.id.viewpager1);

        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < tabTitle.length; i++) {
            if(i==0) {
                fragments.add(new DynamicFragment());
            }
            if(i==1) {
                fragments.add(new NewsFragment());
            }
            if(i==2) {
                fragments.add(new TalkFragment());
            }

        }
        adapter = new MsgFragmentPageAdapter(getChildFragmentManager(), fragments);

        viewpager.setAdapter(adapter);

        tab.setupWithViewPager(viewpager);

        tab.setTabMode(TabLayout.MODE_FIXED);
        tab.setTabGravity(TabLayout.GRAVITY_FILL);
        //连接mqtt服务器



        return view;
    }





/*
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

        }*/

      /*  @Override
        public void onPageScrollStateChanged(int state) {

        }*/


    };


   /* @Override
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
*/



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
    public static final String[] tabTitle = new String[]{ "专题"};
    @Nullable

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_message,null);

        tab = (TabLayout) view.findViewById(R.id.tab);
        viewpager = (ViewPager)view. findViewById(R.id.viewpager1);

        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < tabTitle.length; i++) {
            if(i==0) {
               // fragments.add(new DynamicFragment());
                fragments.add(new TalkFragment());
            }

           /* if(i==1) {
                fragments.add(new TalkFragment());
            }*/

        }
        adapter = new MsgFragmentPageAdapter(getChildFragmentManager(), fragments);

        viewpager.setAdapter(adapter);

        tab.setupWithViewPager(viewpager);

       /* tab.setTabMode(TabLayout.MODE_FIXED);
        tab.setTabGravity(TabLayout.GRAVITY_FILL);*/




        return view;
    }



    };





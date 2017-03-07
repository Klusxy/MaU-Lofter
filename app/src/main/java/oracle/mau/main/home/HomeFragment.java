package oracle.mau.main.home;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import oracle.mau.R;
import oracle.mau.base.BaseFragment;
import oracle.mau.main.home.activity.HomeAddAttentionActivity;
import oracle.mau.main.home.adapter.HomeFragmentPagerAdapter;
import oracle.mau.main.home.fragment.AttentionFragment;
import oracle.mau.main.home.fragment.SubscriptionFragment;
/**
 * Created by 田帅 on 2017/2/20.
 *    首页
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {
    ViewPager vp;
    TabLayout tab;
    HomeFragmentPagerAdapter adapter;
    ImageView addAttention;
    public static final String[] tabTitle = new String[]{"关注", "订阅"};
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {

        vp=(ViewPager)rootView.findViewById(R.id.home_vp);
        tab = (TabLayout) rootView.findViewById(R.id.tab);
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < tabTitle.length; i++) {
            if(i==0) {

                fragments.add(new AttentionFragment());
            }
            if(i==1) {

                fragments.add(new SubscriptionFragment());
            }

        }
        //
        adapter=new HomeFragmentPagerAdapter(getChildFragmentManager(),fragments);

        vp.setAdapter(adapter);

        tab.setupWithViewPager(vp);

        tab.setTabMode(TabLayout.MODE_FIXED);
        tab.setTabGravity(TabLayout.GRAVITY_FILL);
        addAttention=(ImageView) rootView.findViewById(R.id.home_add);
        addAttention.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_add:
                Intent i=new Intent(getActivity(), HomeAddAttentionActivity.class);
                startActivity(i);
                break;
                default:
                break;
        }

    }
}

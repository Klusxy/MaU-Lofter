package oracle.mau.main.home.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.List;
import oracle.mau.main.home.HomeFragment;

/**
 * Created by Administrator on 2017/3/1.
 * 首页最上方tab：PagerSlidingTabStrip的设置
 */

public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;

    public HomeFragmentPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;

    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return HomeFragment.tabTitle[position];

    }
}

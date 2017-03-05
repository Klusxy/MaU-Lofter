package oracle.mau.main.message.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import oracle.mau.main.message.MessageFragment;

/**
 * Created by shadow on 2017/3/2.
 */

public class MsgFragmentPageAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;

    public MsgFragmentPageAdapter(FragmentManager fm, List<Fragment> list) {
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
        return MessageFragment.tabTitle[position];

    }
}

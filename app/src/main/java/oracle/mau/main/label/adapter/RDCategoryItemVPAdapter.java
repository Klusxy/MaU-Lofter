package oracle.mau.main.label.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import oracle.mau.R;
import oracle.mau.entity.LabelTagEntity;
import oracle.mau.entity.LabelTagNoListEntity;
import oracle.mau.main.label.fragment.RecommendDetailFragment;

/**
 * Created by 田帅 on 2017/3/7.
 */

public class RDCategoryItemVPAdapter extends FragmentStatePagerAdapter {
    private Context context;
    private List<LabelTagNoListEntity> list;

    public RDCategoryItemVPAdapter(FragmentManager fm, Context context,List<LabelTagNoListEntity> list) {
        super(fm);
        this.context = context;
        this.list = list;

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position).getTagTitle();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Fragment getItem(int position) {
        return RecommendDetailFragment.newInstance(list.get(position));
    }

}


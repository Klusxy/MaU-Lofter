package oracle.mau.main.label.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import oracle.mau.R;
import oracle.mau.main.label.fragment.RecommendDetailFragment;

/**
 * Created by 田帅 on 2017/3/7.
 */

public class RDCategoryItemVPAdapter extends FragmentPagerAdapter {
    private Context context;
    private final List<String> catalogs = new ArrayList<String>();

    public RDCategoryItemVPAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        catalogs.add(context.getString(R.string.category_hot));
        catalogs.add("\u672c\u5730");
        catalogs.add(context.getString(R.string.category_video));
        catalogs.add(context.getString(R.string.category_society));
        catalogs.add(context.getString(R.string.category_entertainment));
        catalogs.add(context.getString(R.string.category_tech));
        catalogs.add(context.getString(R.string.category_finance));
        catalogs.add(context.getString(R.string.category_military));
        catalogs.add(context.getString(R.string.category_world));
        catalogs.add(context.getString(R.string.category_image_ppmm));
        catalogs.add(context.getString(R.string.category_health));
        catalogs.add(context.getString(R.string.category_government));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return catalogs.get(position);
    }

    @Override
    public int getCount() {
        return catalogs.size();
    }

    @Override
    public Fragment getItem(int position) {
        return RecommendDetailFragment.newInstance(position);
    }

}


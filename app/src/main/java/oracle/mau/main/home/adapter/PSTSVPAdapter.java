package oracle.mau.main.home.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/1.
 * 首页最上方tab：PagerSlidingTabStrip的设置
 */

public class PSTSVPAdapter  extends FragmentPagerAdapter {
    ArrayList<String> liststr=new ArrayList<String>();
    ArrayList<Fragment> listfrag=new ArrayList<Fragment>();

    public void addData(ArrayList<String> liststr){
        this.liststr.addAll(liststr);
    }
   public void addFragmentData(ArrayList<Fragment> listfrag){
       this.listfrag.addAll(listfrag);
   }

    public PSTSVPAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return listfrag.get(position);
    }

    @Override
    public int getCount() {
        return listfrag.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return liststr.get(position);
    }
}

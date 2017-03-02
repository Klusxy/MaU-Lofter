package oracle.mau.main.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;

import oracle.mau.R;
import oracle.mau.base.BaseFragment;
import oracle.mau.main.home.activity.HomeAddAttentionActivity;
import oracle.mau.main.home.adapter.PSTSVPAdapter;

/**
 * Created by 田帅 on 2017/2/20.
 *    首页
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener {
    ViewPager vp;
    PagerSlidingTabStrip psts;
    PSTSVPAdapter adapter;
    ImageView addAttention;
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {

        vp=(ViewPager)rootView.findViewById(R.id.home_vp);
        psts=(PagerSlidingTabStrip)rootView.findViewById(R.id.home_psts);
        psts.setUnderlineColorResource(R.color.green);
        psts.setIndicatorHeight(R.dimen.main_top_height);
        psts.setIndicatorColorResource(R.color.white);
        psts.setShouldExpand(true);
        psts.setIndicatorHeight(R.dimen.home_psts_height);
        psts.setBackgroundColor(getResources().getColor(R.color.white));
        psts.setDividerPadding(R.dimen.home_psts_width);
        adapter=new PSTSVPAdapter(getFragmentManager());
        vp.setAdapter(adapter);
        LoadData();
        psts.setViewPager(vp);
        addAttention=(ImageView) rootView.findViewById(R.id.home_add);
        addAttention.setOnClickListener(this);
    }
    public void LoadData(){
        ArrayList<String> list=new ArrayList<String>();
        list.add("             关注          ");
        list.add("    订阅    ");

        ArrayList<Fragment> listFrag=new ArrayList<Fragment>();
        Bundle b=null;
        AttentionFragment attention=new AttentionFragment();
        b= new Bundle();
        b.putInt("type",(1));
        attention.setArguments(b);
        listFrag.add(attention);

        SubscriptionFragment subscription=new SubscriptionFragment();
        b.putInt("type",(1));
        subscription.setArguments(b);
        listFrag.add(subscription);
        adapter.addFragmentData(listFrag);
        adapter.addData(list);
        adapter.notifyDataSetChanged();
        vp.setOffscreenPageLimit(2);

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

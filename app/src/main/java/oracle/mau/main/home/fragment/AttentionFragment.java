package oracle.mau.main.home.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import oracle.mau.R;
import oracle.mau.base.BaseFragment;
import oracle.mau.entity.HomeEntity;
import oracle.mau.main.home.adapter.HomeAttentionFragmentAdapter;


/**
 * Created by Administrator on 2017/3/1.
 */

public class AttentionFragment extends BaseFragment{
   // int type;
    ListView lv;
    HomeAttentionFragmentAdapter adapter;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_homeattention;
    }

    @Override
    protected void initView() {


//        type=getArguments().getInt("type",-1);
//        toast(type+"--------");
        lv=(ListView)rootView.findViewById(R.id.home_lv);
        adapter=new HomeAttentionFragmentAdapter(getActivity());
        lv.setAdapter(adapter);
        addData();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                toast("首页每个item的点击事件");
            }
        });

    }
    // 自定义数据加载的方法
    public void addData() {
        List<HomeEntity> list=new ArrayList<HomeEntity>();
        for (int i=0;i<20;i++){
            //假数据
            HomeEntity user=new HomeEntity();
            user.setUsername("用户名"+ i);
            user.setCommandname("推荐用户名"+ i);
            user.setContent("文章内容"+ i);
            user.setTime("2分钟前");
            user.setSign("搭配  今天穿什么 stylelife");
            user.setPic(R.mipmap.ic_launcher);
            user.setHot("2  热度");
            list.add(user);
        }
        adapter.addData(list);
        adapter.notifyDataSetChanged();
    }
}

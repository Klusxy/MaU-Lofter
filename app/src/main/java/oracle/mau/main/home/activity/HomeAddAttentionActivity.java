package oracle.mau.main.home.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import oracle.mau.R;
import oracle.mau.base.BaseActivity;
import oracle.mau.main.home.adapter.HomeAddAttentionAdapter;
import oracle.mau.main.home.entity.User;

/**
 * Created by Administrator on 2017/3/1.
 */

public class HomeAddAttentionActivity extends BaseActivity{
    ImageView home_back;
    ListView lv;
    HomeAddAttentionAdapter adapter;
    @Override
    public int getLayoutId() {
        return R.layout.activity_home_addattention;
    }

    @Override
    public void initView() {
        home_back=(ImageView) findViewById(R.id.home_back);
        home_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        lv=(ListView) findViewById(R.id.home_addattention_lv);
        adapter=new HomeAddAttentionAdapter(HomeAddAttentionActivity.this);
        lv.setAdapter(adapter);
        addData();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                toast("添加关注页面的listview的每个item");

            }
        });

    }
    // 自定义数据加载的方法
    public void addData() {
        List<User> list=new ArrayList<User>();
        for (int i=0;i<20;i++){
            //假数据
            User user=new User();
            user.setUsername("发布用户名"+ i);
            user.setLikeNum("被喜欢"+1000+i+"次");
            user.setPic(R.mipmap.ic_launcher);//推荐用户头像
            list.add(user);
        }
        adapter.addData(list);
        adapter.notifyDataSetChanged();
    }
    //关注不喜欢按钮的样式

}

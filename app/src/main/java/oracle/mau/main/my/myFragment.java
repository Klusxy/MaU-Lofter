package oracle.mau.main.my;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import oracle.mau.R;
import oracle.mau.base.BaseFragment;

/**
 * Created by shadow on 2017/3/10.
 */

public class myFragment extends BaseFragment implements AdapterView.OnItemClickListener{
    private ImageView imguser;
    private TextView username;
    private ListView listView;
    /*
    listview的数据源
     */
    private int [] msgpic={R.drawable.message_new_fans,R.drawable.message_fav,R.drawable.message_new_notices,R.drawable.message_sys_notices};
    private String[] msgtext={"我的文章","我的订阅","我关注的人"};

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView() {
        imguser=(ImageView)rootView.findViewById(R.id.my_img_userimg);
        username=(TextView)rootView.findViewById(R.id.my_text_username);
        listView=(ListView) rootView.findViewById(R.id.my_list);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i){

        }
    }
}

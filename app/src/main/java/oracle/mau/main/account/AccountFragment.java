package oracle.mau.main.account;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import oracle.mau.R;
import oracle.mau.base.BaseFragment;
import oracle.mau.main.account.activity.AboutUs;
import oracle.mau.main.account.activity.AccountDetailActivity;
import oracle.mau.main.account.adapter.AccountListAdapter;
import oracle.mau.utils.ImageUtils;

/**
 * Created by 田帅 on 2017/2/20.
 */

public class AccountFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private ImageView imguser;
    private TextView username;
    private ListView listView;
    /*
    listview的数据源
     */
    private int [] msgpic={R.drawable.message_new_fans,R.drawable.message_fav,R.drawable.message_new_notices,R.drawable.message_sys_notices,R.mipmap.aboutus,R.mipmap.gohome};
    private String[] msgtext={"我关注的人","清除缓存","反馈信息","关于我们","退出登录"};
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_account;
    }

    @Override
    protected void initView() {
        imguser=(ImageView)rootView.findViewById(R.id.my_img_userimg);
        username=(TextView)rootView.findViewById(R.id.my_text_username);
        listView=(ListView) rootView.findViewById(R.id.my_list);
        AccountListAdapter accountListAdapter=new AccountListAdapter(getActivity(),msgtext,msgpic);
        listView.setAdapter(accountListAdapter);
        listView.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if(i==0){

        }
        if(i==1){

        }
        if(i==2){

        }
        if(i==3){
            Intent intentAboutUs=new Intent(getActivity(), AboutUs.class);
            startActivity(intentAboutUs);
        }
    }
}

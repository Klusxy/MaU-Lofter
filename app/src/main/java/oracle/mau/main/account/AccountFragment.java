package oracle.mau.main.account;

import android.accounts.Account;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;

import java.io.File;

import oracle.mau.R;
import oracle.mau.base.BaseFragment;
import oracle.mau.entity.UserEntity;
import oracle.mau.main.account.activity.AboutUs;
import oracle.mau.main.account.activity.AccountDetailActivity;
import oracle.mau.main.account.activity.AttentionPeople;
import oracle.mau.main.account.activity.BackInfo;
import oracle.mau.main.account.adapter.AccountListAdapter;
import oracle.mau.main.loginAndregister.FirstActivity;
import oracle.mau.main.loginAndregister.LoginActivity;
import oracle.mau.utils.GetTheUser;
import oracle.mau.utils.ImageUtils;

/**
 * Created by 田帅 on 2017/2/20.
 */

public class AccountFragment extends BaseFragment implements AdapterView.OnItemClickListener,View.OnClickListener {
    private ImageView imguser;
    private TextView username;
    private ListView listView;
    private Bitmap circleBitmap;
    private RelativeLayout rela;
   // private Button btnClose;

    /*
    listview的数据源
     */
    private int [] msgpic={R.drawable.message_new_fans,R.drawable.message_fav,R.drawable.message_new_notices,R.drawable.message_sys_notices,R.mipmap.gohome};
    private String[] msgtext={"我关注的人","清除缓存","反馈信息","关于我们","退出登录"};
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_account;
    }

    @Override
    protected void initView() {
        rela=(RelativeLayout) rootView.findViewById(R.id.my_self);
        rela.setOnClickListener(this);
        imguser=(ImageView)rootView.findViewById(R.id.my_img_userimg);
        username=(TextView)rootView.findViewById(R.id.my_text_username);
        listView=(ListView) rootView.findViewById(R.id.my_list);
        /*btnClose=(Button)rootView.findViewById(R.id.btn_close);
        btnClose.setOnClickListener(this);*/
        AccountListAdapter accountListAdapter=new AccountListAdapter(getActivity(),msgtext,msgpic);
        listView.setAdapter(accountListAdapter);
        listView.setOnItemClickListener(this);
       UserEntity user= GetTheUser.getUser(getActivity());
        username.setText(user.getUsername());
        ImageUtils.getBitmapUtils(getActivity()).display(imguser,user.getUserpic(),new BitmapLoadCallBack<View>() {

            @Override
            public void onLoadCompleted(View view, String s, Bitmap bitmap, BitmapDisplayConfig bitmapDisplayConfig, BitmapLoadFrom bitmapLoadFrom) {
                circleBitmap=ImageUtils.circleBitmap(bitmap);
                imguser.setImageBitmap(circleBitmap);
                imguser.invalidate();
            }

            @Override
            public void onLoadFailed(View view, String s, Drawable drawable) {

            }


        });

    }


    /** * 删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理 * * @param directory */
    private static void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File item : directory.listFiles()) {
                item.delete();
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if(i==0){
            Intent intentAttentionPeo=new Intent(getActivity(), AttentionPeople.class);
            startActivity(intentAttentionPeo);
        }
        if(i==1){

            File appDir = new File(Environment.getExternalStorageDirectory(),
                    "/storage/emulated/0/MaU");
            deleteFilesByDirectory(appDir);
            toast("清除成功");

        }
        if(i==2){
            Intent intentHelp=new Intent(getActivity(), BackInfo.class);
            startActivity(intentHelp);
        }
        if(i==3){
            Intent intentAboutUs=new Intent(getActivity(), AboutUs.class);
            startActivity(intentAboutUs);
        }
        if(i==4){
            Intent intentFirst=new Intent(getActivity(), LoginActivity.class);
            getActivity().startActivity(intentFirst);
            getActivity().finish();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.my_self:
                UserEntity user=GetTheUser.getUser(getActivity());
                AccountDetailActivity.actionStart(getActivity(),user.getUserid());
                break;
            /*case R.id.btn_close:
                Intent intentFirst=new Intent(getActivity(), LoginActivity.class);
                getActivity().startActivity(intentFirst);
                getActivity().finish();
                break;*/
        }
    }
}

package oracle.mau.main.loginAndregister;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.HashMap;
import java.util.Map;

import oracle.mau.R;
import oracle.mau.base.BaseActivity;
import oracle.mau.http.bean.BeanData;
import oracle.mau.http.common.Callback;
import oracle.mau.http.common.HttpServer;
import oracle.mau.http.constants.URLConstants;
import oracle.mau.http.data.HotUserData;
import oracle.mau.http.parser.HotUserParser;

/**
 * Created by shadow on 2017/2/28.
 */

public class UserInfoActivity extends BaseActivity implements View.OnClickListener{
    private ImageButton userimg;
    private EditText editname;
    private EditText editpwd;
    private Button btnOk;
    private String path;
    private String userTel;
    @Override
    public int getLayoutId() {
        return R.layout.activity_userinfo;
    }

    @Override
    public void initView() {
        Intent intent=getIntent();
       userTel= intent.getStringExtra("usertel");
        userimg=(ImageButton)findViewById(R.id.imageButton1);
        userimg.setOnClickListener(this);
        editname=(EditText) findViewById(R.id.edit_userName);
        editpwd=(EditText) findViewById(R.id.edit_usersex);
        btnOk=(Button) findViewById(R.id.btn_ok);
        btnOk.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageButton1:
                showDialog();
                break;
            case R.id.btn_ok:
                sendMessage();
                break;

        }
    }

    /**
     * 弹出对话框，询问是否打开相册
     */
    public void showDialog() {
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setTitle("头像设置");
        ab.setPositiveButton("相册", new DialogInterface.OnClickListener() {


            public void onClick(DialogInterface arg0, int arg1) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 3);
            }
        });

        ab.setNegativeButton("取消", new DialogInterface.OnClickListener() {


            public void onClick(DialogInterface arg0, int arg1) {

            }
        });

        ab.show();
    }


    /**
     * 用于接收用户选择完的头像
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 3) {

            //Bitmap bb=	BitmapFactory.decodeFile("file:///storage/emulated/0/DCIM/Camera/IMG_20170210_170034.jpg");
            if (resultCode == RESULT_OK) {

				/* 代码区 */

                Uri uri = data.getData();
                Cursor c = getContentResolver().query(uri, null,
                        null, null, null);
                if(c!=null){
                    if (c.moveToNext()) {
                        path = c.getString(1);

                        //btn_pic.setImageURI(Uri.parse(path));
                        userimg.setImageBitmap(BitmapFactory.decodeFile(path));

                    }
                }
                else{
                    String sub=data.getDataString();
                    path=sub.split("///")[1];
                    userimg.setImageBitmap(BitmapFactory.decodeFile(path));
                }

            }
        } else {
            System.out.println("请求码错误");
        }
    }


    /**
     * 点击完成按钮后向服务器发送用户的注册信息
     */
    public void sendMessage(){
        Map<String, Object> params=new HashMap<String, Object>();
        String userName=editname.getText().toString();
        String userPwd=editpwd.getText().toString();
        params.put("user_name", userName);
        params.put("user_pwd", userPwd);
        params.put("user_img",path);
        params.put("user_tel",userTel);


        HttpServer.sendPostRequest(HttpServer.HTTPSERVER_POST,params, new HotUserParser(), URLConstants.BASE_URL+URLConstants.USERRESGISTER, new Callback() {




            @Override
            public void success(BeanData beanData) {
                HotUserData uData=(HotUserData)beanData;

/*
注册成功后跳转到登录界面
 */
                toast("注册SAAA成功");
                Intent intentLog=new Intent(UserInfoActivity.this,LoginActivity.class);
                startActivity(intentLog);
                finish();


            }

            @Override
            public void failure(String error) {

                toast(error);
            }
        });



    }



}

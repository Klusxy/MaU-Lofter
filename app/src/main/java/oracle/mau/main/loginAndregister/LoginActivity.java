package oracle.mau.main.loginAndregister;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;
import oracle.mau.R;
import oracle.mau.application.MaUApplication;
import oracle.mau.base.BaseActivity;
import oracle.mau.entity.UserEntity;
import oracle.mau.http.bean.BeanData;
import oracle.mau.http.common.Callback;
import oracle.mau.http.common.HttpServer;
import oracle.mau.http.constants.URLConstants;
import oracle.mau.http.data.HotUserData;
import oracle.mau.http.data.UserData;
import oracle.mau.http.parser.HotUserParser;
import oracle.mau.http.parser.UserParser;
import oracle.mau.main.MainActivity;
import oracle.mau.utils.ImageUtils;
import oracle.mau.utils.JudgeUtils;
import oracle.mau.view.JellyInterpolator;

/**
 * Created by shadow on 2017/2/27.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private EditText editTel;
    private EditText editpwd;
    private TextView btnLogin;
    private TextView btnRegister;
    private ImageView iv_login_top_logo;

    private View progress;

    private View mInputLayout;

    private float mWidth, mHeight;

    private LinearLayout mName, mPsw;

    private String userTel;
    private String userPwd;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        editTel = (EditText) findViewById(R.id.edit_login_tel);
        editpwd = (EditText) findViewById(R.id.edit_login_pwd);

        btnLogin = (TextView) findViewById(R.id.button);
        btnLogin.setOnClickListener(this);
        btnRegister = (TextView) findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(this);

        progress = findViewById(R.id.layout_progress);
        mInputLayout = findViewById(R.id.input_layout);
        mName = (LinearLayout) findViewById(R.id.input_layout_name);
        mPsw = (LinearLayout) findViewById(R.id.input_layout_psw);

        iv_login_top_logo = (ImageView) findViewById(R.id.iv_login_top_logo);
        Bitmap bm = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        Bitmap otherBm = ImageUtils.toRoundCornerImage(bm,5);
        iv_login_top_logo.setImageBitmap(otherBm);
        /**
         * 刚进来时获取sp中内容，判断是否是已有用户
         */
        //先得到对象
        SharedPreferences sp = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        //得到所有的  ？表示所有类型
//                Map<String,?> map = sp2.getAll();
        //得到指定的  第二个参数为默认值
        userTel = sp.getString("user_tel","");
        userPwd = sp.getString("user_pwd","");
        if ("".equals(userTel) || "".equals(userPwd)) {

        }else {
            editTel.setText(userTel);
            editpwd.setText(userPwd);
//            sendMessage(userTel,userPwd);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                /**
                 * 判断是否为空
                 */
                if (JudgeUtils.isEmpty(editTel.getText().toString())){
                    toast("请输入手机号");
                }else if (JudgeUtils.isEmpty(editpwd.getText().toString())){
                    toast("请输入密码");
                }else {
                    userTel = editTel.getText().toString();
                    userPwd = editpwd.getText().toString();
                    sendMessage(userTel,userPwd);
                }
                break;
            case R.id.btn_register:
                SMSSDK.initSDK(LoginActivity.this, "1bb329bbf8c6e", "c9279b59e737ccd1291e9a477e798cfa");
                //打开注册页面
                RegisterPage registerPage = new RegisterPage();
                registerPage.setRegisterCallback(new EventHandler() {
                    public void afterEvent(int event, int result, Object data) {
                        // 解析注册结果
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            // @SuppressWarnings("unchecked")
                            /*HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
                            String country = (String) phoneMap.get("country");
                            String phone = (String) phoneMap.get("phone");
                            // 提交用户信息
                            registerUser(country, phone);*/
                            HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
                            Intent intentUserinfo=new Intent(LoginActivity.this,UserInfoActivity.class);
                            intentUserinfo.putExtra("usertel",(String) phoneMap.get("phone"));
                            startActivity(intentUserinfo);
                        }
                    }
                });
                registerPage.show(this);
                //finish();
                //finish();
                break;

            default:
                break;
        }

    }


    public void sendMessage(String userTel , String userPwd) {
        Map<String, Object> params = new HashMap<String, Object>();
//        String userTel = editTel.getText().toString();
//        String userPwd = editpwd.getText().toString();
        params.put("user_name", userTel);
        params.put("password", userPwd);

        HttpServer.sendPostRequest(HttpServer.HTTPSERVER_POST, params, new UserParser(), URLConstants.BASE_URL + URLConstants.USERLOGIN, new Callback() {


            @Override
            public void success(BeanData beanData) {
                UserData uData = (UserData) beanData;
                UserEntity userEntity = uData.getUserEntity();
                /**
                 * 存入app中
                 */
                MaUApplication app = (MaUApplication) getApplication();
                app.setUser(userEntity);
                /**
                 * 存入sp做持久化自动登陆
                 */
                //得到sp对象
                //第一个参数类似表名   面  第二个参数mode是一个权限  一般是私有的（只能本程序访问）
                SharedPreferences sp = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
                //1、让sp处于可编辑状态
                SharedPreferences.Editor editor = sp.edit();
                //2、存值
                editor.putString("user_tel",userEntity.getUsertel());
                editor.putString("user_pwd",userEntity.getUserpwd());
                //3、提交。   返回值是是否提交成功
//                boolean b = editor.commit(); //一起整到内存和磁盘中
                //apply也是提交  提交到内存中   异步  速度快  没有返回值
                //先将数据提交到内存，然后把数据存到磁盘中
                editor.apply();

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }


            @Override
            public void failure(String error) {
                toast(error);
            }
        });


    }
}

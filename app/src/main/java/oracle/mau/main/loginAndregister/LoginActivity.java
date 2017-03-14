package oracle.mau.main.loginAndregister;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
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
import oracle.mau.http.bean.BeanData;
import oracle.mau.http.common.Callback;
import oracle.mau.http.common.HttpServer;
import oracle.mau.http.constants.URLConstants;
import oracle.mau.http.data.HotUserData;
import oracle.mau.http.data.UserData;
import oracle.mau.http.parser.HotUserParser;
import oracle.mau.http.parser.UserParser;
import oracle.mau.main.MainActivity;
import oracle.mau.view.JellyInterpolator;

/**
 * Created by shadow on 2017/2/27.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private EditText editTel;
    private EditText editpwd;
    private TextView btnLogin;
    private TextView btnRegister;

    private View progress;

    private View mInputLayout;

    private float mWidth, mHeight;

    private LinearLayout mName, mPsw;

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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                sendMessage();
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


    public void sendMessage() {
        Map<String, Object> params = new HashMap<String, Object>();
        String userTel = editTel.getText().toString();
        String userPwd = editpwd.getText().toString();
        params.put("user_name", userTel);
        params.put("password", userPwd);

        HttpServer.sendPostRequest(HttpServer.HTTPSERVER_POST, params, new UserParser(), URLConstants.BASE_URL + URLConstants.USERLOGIN, new Callback() {


            @Override
            public void success(BeanData beanData) {
                UserData uData = (UserData) beanData;
                MaUApplication app = (MaUApplication) getApplication();
                app.setUser(uData.getUserEntity());
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

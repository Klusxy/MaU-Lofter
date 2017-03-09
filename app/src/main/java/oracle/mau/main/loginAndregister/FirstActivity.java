package oracle.mau.main.loginAndregister;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;
import oracle.mau.R;
import oracle.mau.base.BaseActivity;

/**
 * Created by shadow on 2017/3/8.
 */

public class FirstActivity extends BaseActivity implements View.OnClickListener{
    private Button btnLogin;
    private Button btnRegister;

    @Override
    public int getLayoutId() {
        return R.layout.activity_first;
    }

    @Override
    public void initView() {
        btnLogin=(Button)findViewById(R.id.btn_first_login);
        btnLogin.setOnClickListener(this);
        btnRegister=(Button)findViewById(R.id.btn_first_register);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_first_login:
                Intent intentLogin=new Intent(FirstActivity.this,LoginActivity.class);
                startActivity(intentLogin);
                finish();
                break;
            case R.id.btn_first_register:
                SMSSDK.initSDK(FirstActivity.this, "1bb329bbf8c6e", "c9279b59e737ccd1291e9a477e798cfa");
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
                            Intent intentUserinfo=new Intent(FirstActivity.this,UserInfoActivity.class);
                           intentUserinfo.putExtra("usertel",(String) phoneMap.get("phone"));
                           startActivity(intentUserinfo);
                        }
                    }
                });
               registerPage.show(this);
                //finish();
                //finish();
                break;
        }
    }
}

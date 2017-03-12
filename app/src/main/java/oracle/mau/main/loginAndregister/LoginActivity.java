package oracle.mau.main.loginAndregister;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

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

/**
 * Created by shadow on 2017/2/27.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private EditText editTel;
    private EditText editpwd;
    private Button btnLogin;
    private Button btnRegister;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        editTel = (EditText) findViewById(R.id.edit_login_tel);
        editpwd = (EditText) findViewById(R.id.edit_login_pwd);

        btnLogin = (Button) findViewById(R.id.button);
        btnLogin.setOnClickListener(this);
        btnRegister = (Button) findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:


                sendMessage();
                break;
            case R.id.btn_register:
                Intent intentRegister = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intentRegister);

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

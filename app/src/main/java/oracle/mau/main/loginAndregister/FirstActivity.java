package oracle.mau.main.loginAndregister;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

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
        btnRegister.setOnClickListener(thisgit);
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
                Intent intentRegister=new Intent(FirstActivity.this,RegisterActivity.class
                );
                startActivity(intentRegister);
                finish();
                break;
        }
    }
}

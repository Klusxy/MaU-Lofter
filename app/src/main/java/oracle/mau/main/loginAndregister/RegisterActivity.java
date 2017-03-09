package oracle.mau.main.loginAndregister;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mob.tools.MobUIShell;

import oracle.mau.R;
import oracle.mau.base.BaseActivity;

/**
 * Created by shadow on 2017/2/28.
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener{
    private EditText editUsertel;
    private Button btn_next;
    private String usertel;
    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {
        editUsertel=(EditText) findViewById(R.id.user_tel);
        btn_next=(Button) findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

            Intent intentConfimation=new Intent(RegisterActivity.this,ConfirmationActivity.class);
            usertel=editUsertel.getText().toString();
        intentConfimation.putExtra("usertel",usertel)  ;
            startActivity(intentConfimation);

       /* Intent intentConfimation=new Intent(RegisterActivity.this,MobUIShell.class);
        startActivity(intentConfimation);*/

    }
}

package oracle.mau.main.loginAndregister;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import oracle.mau.R;
import oracle.mau.base.BaseActivity;

/**
 * Created by shadow on 2017/2/28.
 */

public class ConfirmationActivity extends BaseActivity implements View.OnClickListener{
    private EditText editNum;
    private TextView textTel;
    private Button btn_again;
    private Button btn_next;
    @Override
    public int getLayoutId() {
        return R.layout.activity_confirmation;
    }

    @Override
    public void initView() {
        editNum=(EditText) findViewById(R.id.edit_num);
        textTel=(TextView) findViewById(R.id.text_phone);
        btn_again=(Button)findViewById(R.id.btn_getnum);
        btn_next=(Button)findViewById(R.id.btn_confirmation_next);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_getnum:
                break;
            case R.id.btn_confirmation_next:
                break;
        }
    }
}

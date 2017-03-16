package oracle.mau.main.account.activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import oracle.mau.R;
import oracle.mau.base.BaseActivity;

/**
 * Created by shadow on 2017/3/16.
 */

public class BackInfo extends BaseActivity implements View.OnClickListener{
    private ImageView imgback;
    private Button btnInfo;
    @Override
    public int getLayoutId() {
        return R.layout.activity_backinfo;
    }

    @Override
    public void initView() {
    imgback=(ImageView) findViewById(R.id.help_back);
        imgback.setOnClickListener(this);
        btnInfo=(Button)findViewById(R.id.btn_backinfo);
        btnInfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_backinfo:
                toast("反馈成功");
                break;
            case R.id.help_back:
                finish();
                break;
        }

    }
}

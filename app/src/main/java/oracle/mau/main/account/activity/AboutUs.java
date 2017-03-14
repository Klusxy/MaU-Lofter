package oracle.mau.main.account.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import oracle.mau.R;
import oracle.mau.base.BaseActivity;

/**
 * Created by shadow on 2017/3/14.
 */

public class AboutUs extends BaseActivity implements View.OnClickListener{
    private ImageView Iv_back;
    @Override
    public int getLayoutId() {
        return R.layout.activity_aboutus;
    }

    @Override
    public void initView() {
        Iv_back=(ImageView) findViewById(R.id.iv_ad_back);
        Iv_back.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        finish();
    }
}

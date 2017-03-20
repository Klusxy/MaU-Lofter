package oracle.mau.main.account.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import oracle.mau.R;
import oracle.mau.base.BaseActivity;
import oracle.mau.utils.ImageUtils;

/**
 * Created by shadow on 2017/3/14.
 */

public class AboutUs extends BaseActivity implements View.OnClickListener{
    private ImageView Iv_back;
    private ImageView logo;
    @Override
    public int getLayoutId() {
        return R.layout.activity_aboutus;
    }

    @Override
    public void initView() {
        Iv_back=(ImageView) findViewById(R.id.iv_ad_back);
        Iv_back.setOnClickListener(this);
        logo=(ImageView) findViewById(R.id.mau_logo);
        Bitmap circle=ImageUtils.circleBitmap(BitmapFactory.decodeResource(this.getResources(),R.mipmap.ic_launcher));
        logo.setImageBitmap(circle);
    }


    @Override
    public void onClick(View view) {
        finish();
    }
}
